package me.songha.simple.member.dto;

import lombok.Builder;
import lombok.Data;
import me.songha.simple.member.entity.Email;
import me.songha.simple.member.entity.Member;

@Builder
@Data
public class MemberRequest {
    private String email;
    private String nickname;

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .email(Email.builder().value(email).build())
                .build();
    }
}
