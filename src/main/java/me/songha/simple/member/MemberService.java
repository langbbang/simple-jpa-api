package me.songha.simple.member;

import lombok.RequiredArgsConstructor;
import me.songha.simple.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDto getMemberDto(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return MemberDto.builder()
                .member(member)
                .build();
    }

    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true) // todo :: for test code, used flush cache
    public Member updateNickname(Long id, String nickname) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.updateNickname(nickname);
        return member;
    }

    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }
}
