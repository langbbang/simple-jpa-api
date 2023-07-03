package me.songha.simple.member;

import me.songha.simple.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

   @Query("select m from Member m where m.email = :email")
   Optional<Member> findFirstByEmail(@Param("email") String email);
}
