package api.apispring.repository;

import api.apispring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// 테스트할 클래스명 뒤에 Test를 붙임
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach   // @AfterEach : 메서드 동작이 끝날 때마다 지정된 행위를 실행한다.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // get()으로 바로 꺼내는 것이 좋은 방법은 아니다.

        System.out.println("result = "+ (result == member));
//        Assertions.assertEquals(member, result);
//        Assertions.assertThat(member).isEqualTo(result);
        // Assertions를 alt+enter 후 static import하면 Assertions.assertXXX()가 assertThat(member)으로 변경된다.
        // 그리고 import static org.junit.jupiter.api.Assertions.*; 가 자동 import된다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
