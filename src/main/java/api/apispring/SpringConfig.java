package api.apispring;

import api.apispring.repository.MemberRepository;
import api.apispring.repository.MemoryMemberRepository;
import api.apispring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Java Code로 직접 Spring Bean 등록하기
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
