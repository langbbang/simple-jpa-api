package me.songha.simple.member;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import me.songha.simple.member.dto.MemberCreationRequest;
import me.songha.simple.member.dto.MemberResponse;
import me.songha.simple.member.dto.MemberUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse findMemberById(@NotNull Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new).toMemberResponse();
    }

    public MemberResponse create(@NotNull MemberCreationRequest creationRequest) {
        return memberRepository.save(creationRequest.toEntity()).toMemberResponse();
    }

    @Transactional
    public MemberResponse modify(@NotNull MemberUpdateRequest updateRequest) {
        return memberRepository.findFirstByEmail(updateRequest.getEmail())
                .orElseThrow(MemberNotFoundException::new)
                .updateMember(updateRequest) // dirty checking
                .toMemberResponse();
    }

}
