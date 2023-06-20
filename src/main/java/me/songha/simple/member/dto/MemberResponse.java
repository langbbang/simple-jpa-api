package me.songha.simple.member.dto;

import lombok.Builder;
import lombok.Data;
import me.songha.simple.member.entity.Member;

import java.time.ZonedDateTime;

@Data
public class MemberResponse {
    private Long id;
    private String email;
    private String nickname;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    @Builder
    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail().getValue();
        this.nickname = member.getNickname();
        this.createAt = member.getCreateAt();
        this.updateAt = member.getUpdateAt();
    }
}
