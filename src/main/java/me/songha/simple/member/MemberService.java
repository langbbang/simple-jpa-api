package me.songha.simple.member;

import lombok.RequiredArgsConstructor;
import me.songha.simple.member.dto.MemberRequest;
import me.songha.simple.member.dto.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new).toMemberResponse();
    }

    public MemberResponse create(MemberRequest memberRequest) {
        return memberRepository.save(memberRequest.toEntity()).toMemberResponse();
    }

    public MemberResponse updateNickname(Long id, String nickname) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new)
                .updateNickname(nickname) // dirty check
                .toMemberResponse();
    }

}
