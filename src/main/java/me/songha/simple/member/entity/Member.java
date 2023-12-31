package me.songha.simple.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.songha.simple.member.dto.MemberResponse;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEMBER")
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Size(min = 2, max = 16) // 2~16 사이의 문자열 갯수가 아니면 exception
    @Column(length = 32) // varchar(32)
    private String nickname;

    @CreatedDate
    @Column(updatable = false)
    @NotNull
    private ZonedDateTime createAt;

    @LastModifiedDate
    private ZonedDateTime updateAt;

    @Builder
    public Member(Email email, String nickname, String rawPassword) {
        this.email = email;
        this.nickname = nickname;
        this.password = Password.builder().value(rawPassword).build();
    }

    public Member updateMemberNickname(@NotNull String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Member updatePassword(@NotNull String newPassword, @NotNull String oldPassword) {
        this.password.updatePassword(newPassword, oldPassword);
        return this;
    }

    public MemberResponse toMemberResponse() {
        return MemberResponse.builder()
                .member(this)
                .build();
    }
}