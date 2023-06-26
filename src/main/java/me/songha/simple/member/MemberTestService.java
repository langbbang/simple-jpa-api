package me.songha.simple.member;

import lombok.RequiredArgsConstructor;
import me.songha.simple.member.dto.MemberUpdateRequest;
import me.songha.simple.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberTestService {
    private final MemberRepository memberRepository;

    public Member create(Member member) {
        return memberRepository.save(member);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    @Transactional(readOnly = true) // todo :: readOnly=true 옵션은 dirty check test를 위함
    public Member updateNickname(MemberUpdateRequest updateRequest) {
        Member member = memberRepository.findById(1L)
                .orElseThrow(MemberNotFoundException::new);
        member.updateMember(updateRequest);
        return member;
    }

}
