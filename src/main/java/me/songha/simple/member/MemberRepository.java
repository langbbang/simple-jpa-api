package me.songha.simple.member;

import me.songha.simple.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
   Optional<Member> findFirstByEmail(String email);
}
