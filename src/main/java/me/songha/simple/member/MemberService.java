package me.songha.simple.member;

import lombok.RequiredArgsConstructor;
import me.songha.simple.member.dto.MemberRequest;
import me.songha.simple.member.dto.MemberResponse;
import me.songha.simple.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse findMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return MemberResponse.builder()
                .member(member)
                .build();
    }

    public MemberResponse create(MemberRequest memberRequest) {
        return memberRepository.save(memberRequest.toEntity()).toMemberResponse();
    }

    public MemberResponse updateNickname(Long id, String nickname) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.updateNickname(nickname); // dirty check
        return member.toMemberResponse();
    }

}
