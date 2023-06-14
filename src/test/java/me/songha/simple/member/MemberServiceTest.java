package me.songha.simple.member;

import me.songha.simple.member.entity.Email;
import me.songha.simple.member.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @BeforeEach // 매 Test 마다 실행
    void setUp() {
        System.out.println("set up!!");
        for (int i = 1; i <= 5; i++) {
            Member member = Member.builder()
                    .email(Email.builder().value("ex" + i + "@example.com").build())
                    .updateAt(ZonedDateTime.now())
                    .createAt(ZonedDateTime.now())
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
    void 트랜잭션_범위안에서_ID가_같은_ENTITIY는_같은_객체를_참조하는가() {
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
    void 트랜잭션_범위안에서_ID가_같은_ENTITIY는_같은_객체를_참조하는가_identityHashCode_비교() {
        Member member1 = memberService.getMember(1L);
        Member member2 = memberService.getMember(1L);

        int identityHashCode1 = System.identityHashCode(member1);
        int identityHashCode2 = System.identityHashCode(member2);

        System.out.println(identityHashCode1); // 1684921376 (매번 바뀌네요)
        System.out.println(identityHashCode2); // 1684921376

        Assertions.assertEquals(identityHashCode1, identityHashCode2); // true
    }

}