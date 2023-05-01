package api.apispring.service;

import api.apispring.domain.Member;
import api.apispring.repository.MemberRepository;
import api.apispring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // @Transactional: 테스트 케이스 한정으로 테스트가 끝나면 insert됐던 데이터들을 rollback한다. 서비스에선 롤백안됨.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    // 데이터가 쌓이므로 clear해줘야 한다.
    @Autowired MemberRepository memberRepository;

    @Test
    // 테스트 코드 메소드명은 한글로 적어도 된다. 프로젝트에 포함되지 않기 때문이다.
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring1");  // 이름을 spring으로 바꾸면 당연히 중복회원예외()에서 예외가 발생한다.

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    // 테스트 케이스의 핵심은 예외를 터뜨리는 것.
    @Test
    void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // 예외처리방법1.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 예외처리방법2.
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 예외처리방법3.
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        // then
    }
}