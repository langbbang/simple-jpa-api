package me.songha.simple.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.songha.simple.member.dto.MemberResponse;
import me.songha.simple.member.dto.MemberUpdateRequest;
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

//    @Embedded
//    private Password password;

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
    public Member(Email email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public Member updateMember(MemberUpdateRequest updateRequest) {
        if (updateRequest.getNickname() != null)
            this.nickname = updateRequest.getNickname();
        return this;
    }

    public MemberResponse toMemberResponse() {
        return MemberResponse.builder()
                .member(this)
                .build();
    }
}