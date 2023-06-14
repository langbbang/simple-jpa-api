package me.songha.simple.member;

import lombok.AllArgsConstructor;
import me.songha.simple.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class MemberService {
    private MemberRepository memberRepository;

    // id로 멤버 조회
    @Transactional(readOnly = true)
    public MemberDto getMemberDto(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberException::new);
        return MemberDto.builder()
                .member(member)
                .build();
    }

    @Transactional(readOnly = true)
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberException::new);
    }
}
