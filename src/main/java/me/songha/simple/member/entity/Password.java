package me.songha.simple.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.songha.simple.member.exception.PasswordFailedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.ZonedDateTime;

/**
 * [[[ 비밀번호 요구사항 ]]]
 * 1. 비밀번호 만료 기본 30일 기간이 있다.
 * 2. 비밀번호 만료 기간이 지나는 것을 알 수 있어야 한다.
 * 3. 비밀번호 5회 이상 실패했을 경우 더 이상 시도를 못하게 해야 한다.
 * 4. 비밀번호가 일치하는 경우 실패 카운트를 초기화 해야한다.
 * 5. 비밀번호 변경 시 만료일이 현재시간 기준 30일로 연장되어야한다.
 */

//@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Password {

    @Size(min = 8, max = 32)
    @Column(name = "password", nullable = false, length = 64)
    private String value;

    @Column(name = "password_expiration_date", nullable = false)
    private ZonedDateTime expirationDate;

    @Column(name = "password_failed_count", nullable = false)
    private int failedCount;

    @Column(name = "password_ttl", nullable = false)
    private long ttl;

    @Builder
    public Password(String value) { // 신규
        this.value = this.encodePassword(value);
        this.ttl = 2_592_000; // 30일
        extendExpiredDate();
    }

    public void updatePassword(String newPassword, String oldPassword) { // 패스워드 변경
        boolean matches = matches(oldPassword);

        if (matches) {
            this.value = encodePassword(newPassword);
            this.failedCount = 0;
            extendExpiredDate();
        }
    }

    public boolean isMatched(String rawPassword) { // 로그인
        if (this.failedCount >= 5) {
            throw new PasswordFailedException();
        }
        boolean matches = matches(rawPassword);
        updateFailCount(matches);
        return matches;
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private boolean matches(String rawPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, value);
    }

    private void updateFailCount(boolean matches) {
        if (matches) failedCount = 0;
        else failedCount++;
    }

    private void extendExpiredDate() {
        this.expirationDate = ZonedDateTime.now().plusSeconds(ttl);
    }

}
