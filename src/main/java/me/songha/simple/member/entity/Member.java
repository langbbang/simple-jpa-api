package me.songha.simple.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.songha.simple.common.entity.DateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

//    @Embedded
//    private Password password;

    @Size(min = 2, max = 16) // 2~16 사이의 문자열 갯수가 아니면 exception
    @Column(length = 32) // varchar(32)
    private String nickname;

    @Embedded
    private DateTime dateTime;

    @Builder
    public Member(Email email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}