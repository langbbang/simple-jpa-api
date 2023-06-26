package me.songha.simple.member;

import me.songha.simple.member.dto.MemberUpdateRequest;
import me.songha.simple.member.entity.Email;
import me.songha.simple.member.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberTestService memberService;

    //    @BeforeEach
    void setUp() {
        System.out.println("set up!!");

        for (int i = 1; i <= 5; i++) {
            Member member = Member.builder()
                    .email(Email.builder().value("ex" + i + "@example.com").build())
                    .nickname("별칭" + i)
                    .build();
            memberService.create(member);
        }
    }

    @Test
    void test() {
        System.out.println("print!!");
    }

    @Test
    void ID로_MEMBER_조회() {
        Member member = memberService.findMemberById(1L);
        System.out.println(member);

        Assertions.assertNotNull(member);
    }

    @Test
    @Transactional
    void 트랜잭션_범위안에서_ID가_같은_Entity는_같은_객체를_참조하는가() {
        Member member1 = memberService.findMemberById(1L);
        Member member2 = memberService.findMemberById(1L);
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
        Member member1 = memberService.findMemberById(1L);
        Member member2 = memberService.findMemberById(1L);

        int identityHashCode1 = System.identityHashCode(member1);
        int identityHashCode2 = System.identityHashCode(member2);

        System.out.println(identityHashCode1); // 1684921376 (매번 바뀌지만 1과 2는 같은 값)
        System.out.println(identityHashCode2); // 1684921376

        Assertions.assertEquals(identityHashCode1, identityHashCode2); // true
    }

    @Test
    @Rollback(false)
    void 트랜잭션_readOnly_true_일때_Entity객체를_Update_한다면() {
        MemberUpdateRequest updateRequest = MemberUpdateRequest.builder()
                .id(1L)
                .nickname("업데이트")
                .build();
        /**
         * transactional을 readOnly=true 로 설정하게 되면 영속성 컨텍스트가 flush 되지 않으므로 db와 연동되지 않는다.
         */
        Member member1 = memberService.updateNickname(updateRequest); // readOnly=true
        Member member2 = memberService.findMemberById(1L);

        System.out.println(member1);
        System.out.println(member2);

        Assertions.assertNotEquals(member1.getNickname(), member2.getNickname());
    }

    @Test
    void EqualsAndHashCode를_통해_Override했을때_트랜잭션_밖의_같은_id의_Entity객체를_비교한다면() {
        Member member1 = memberService.findMemberById(1L);
        Member member2 = memberService.findMemberById(1L);

        System.out.println(member1);
        System.out.println(member2);

        /**
         * member1과 member2는 서로 다른 트랜잭션이므로 이말은 즉슨 다른 영속성 컨텍스트에서 관리되는 엔티티이다.
         * 그래서 오버라이드 하기전에는 다른 객체로 notEqual 이지만,
         * @EqualsAndHashCode 통해 id가 같은 값이면 (DB row가 같은 값이라면) equal로 return 된다.
         */

        Assertions.assertEquals(member1, member2); // true
    }

    @Rollback(false)
    @Transactional
    @Test
    void Entity에_Embedded한_가변객체가_있을때_equals를_override_해주지_않았을_경우에_대한_불일치_문제에서_발생하는_dirty_check() {

        Member member = memberService.findMemberById(1L);

        System.out.println(member);

//        member.updateNickname("별칭1");

        Assertions.assertNotNull(member);


        /**
         * 하이버네이트는 조회 시점의 entity "복사본"을 만들어두고
         * transaction이 끝나는 시점에 "복사본과 원본을 비교"하게 된다.
         *
         * 비교를 하는 방식은 "각 컬럼"이 동일한지 확인을 하게 되며 이때 사용하는 비교 함수는
         *
         * Objects.equals(a, b);
         * ---------------------------------------------------------------
         * public static boolean equals(Object a, Object b) {
         *     return (a == b) || (a != null && a.equals(b));
         * }
         * ---------------------------------------------------------------
         * 이고, 내부 구현 구조를 보면 equals() 함수를 사용하는 것을 확인할 수 있다.
         *
         * 그렇기 때문에 @Embedded를 통해 가변 객체가 포함된 Entity의 경우 Equals 재정의가 필요한 것이다.
         *
         * Entity에 가변(mutable) 객체를 필드로 사용해야할 경우 dirty check가 일어나는 것을 막기 위해
         * equals()를 override 해주어야 한다.
         */

    }

}