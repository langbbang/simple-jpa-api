package me.songha.simple.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberUpdateRequest {
    @NotNull
    private String email;
    private String nickname;

    @Builder
    public MemberUpdateRequest(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
