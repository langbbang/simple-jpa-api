package me.songha.simple.member;

import lombok.*;
import me.songha.simple.member.entity.Member;

import java.time.ZonedDateTime;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class MemberDto {
    private Long id;
    private String email;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    @Builder
    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail().getValue();
        this.createAt = member.getCreateAt();
        this.updateAt = member.getUpdateAt();
    }
}
