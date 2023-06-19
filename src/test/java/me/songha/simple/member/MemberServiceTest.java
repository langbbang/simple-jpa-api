package me.songha.simple.member;

import me.songha.simple.member.entity.Email;
import me.songha.simple.member.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        System.out.println("set up!!");

        for (int i = 1; i <= 5; i++) {
            Member member = Member.builder()
                    .email(Email.builder().value("ex" + i + "@example.com").build())
                    .nickname("별칭" + i)
                    .build();
            memberService.save(member);
        }
    }

    @Test
    void test() {
        System.out.println("print!!");
    }

    @Test
    void ID로_MEMBER_조회() {
        MemberDto memberDto = memberService.getMemberDto(1L);
        System.out.println(memberDto);

        Assertions.assertNotNull(memberDto);
    }

    @Test
    @Transactional
    void 트랜잭션_범위안에서_ID가_같은_Entity는_같은_객체를_참조하는가() {
        Member member1 = memberService.getMember(1L);
        Member member2 = memberService.getMember(1L);
        /**
         * 1차 캐시의 개념과 연관, 하나의 트랜잭션 범위 안에서 DB row가 같은 값은 같은 객체를 참조한다.
         */
        Assertions.assertEquals(member1, member2); // true

        System.out.println(System.identityHashCode(member1));
        System.out.println(System.identityHashCode(member2));
    }

    @Test
    @Transactional
    void 트랜잭션_범위안에서_ID가_같은_Entity는_같은_객체를_참조하는가_identityHashCode_비교() {
        Member member1 = memberService.getMember(1L);
        Member member2 = memberService.getMember(1L);

        int identityHashCode1 = System.identityHashCode(member1);
        int identityHashCode2 = System.identityHashCode(member2);

        System.out.println(identityHashCode1); // 1684921376 (매번 바뀌지만 1과 2는 같은 값)
        System.out.println(identityHashCode2); // 1684921376

        Assertions.assertEquals(identityHashCode1, identityHashCode2); // true
    }

    @Test
    @Rollback(false)
    void 트랜잭션_readOnly_true_일때_Entity객체를_Update_한다면() {
        /**
         * transactional을 readOnly=true 로 설정하게 되면 영속성 컨텍스트가 flush 되지 않으므로 db와 연동되지 않는다.
         */
        Member member1 = memberService.updateNickname(1L, "업데이트"); // readOnly=true
        Member member2 = memberService.getMember(1L);

        System.out.println(member1);
        System.out.println(member2);

        Assertions.assertNotEquals(member1.getNickname(), member2.getNickname());
    }

    @Test
    void EqualsAndHashCode를_통해_Override했을때_트랜잭션_밖의_같은_id의_Entity객체를_비교한다면() {
        Member member1 = memberService.getMember(1L);
        Member member2 = memberService.getMember(1L);

        System.out.println(member1);
        System.out.println(member2);

        /**
         * member1과 member2는 서로 다른 트랜잭션이므로 이말은 즉슨 다른 영속성 컨텍스트에서 관리되는 엔티티이다.
         * 그래서 오버라이드 하기전에는 다른 객체로 notEqual 이지만,
         * @EqualsAndHashCode 통해 id가 같은 값이면 (DB row가 같은 값이라면) equal로 return 된다.
         */

        Assertions.assertEquals(member1, member2); // true
    }

}