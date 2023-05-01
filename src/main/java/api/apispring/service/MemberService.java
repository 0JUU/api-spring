package api.apispring.service;

import api.apispring.domain.Member;
import api.apispring.repository.MemberRepository;
import api.apispring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
    * 회원 가입
    * */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원은 안됨
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // orElseGet(): 값이 있으면 꺼내고 없으면 안꺼냄
        
        /*
            Optional<>이 있어야 ifPresent()를 사용할 수 있으므로 Optional을 생략하고 ifPresent()를 바로 쓸 수도 있다.

            기존 코드
            Optional<Member> result = memberRepository.findByName(member.getName());
            result.ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });

            ===>>>

            변경 코드
            memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });

            이때, Extract Method 기능을 사용하여 메소드화하면 코드가 간단해진다.
         */
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
