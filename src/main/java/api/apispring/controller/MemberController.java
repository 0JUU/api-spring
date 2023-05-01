package api.apispring.controller;

import api.apispring.domain.Member;
import api.apispring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private MemberService memberService;

    // @Autowired 사용 방법1 - 필드 주입
//    @Autowired private MemberService memberService;

    // @Autowired 사용 방법2 - 생성자 주입, 최근 권장 방법
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // @Autowired 사용 방법3 - setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    /*
    * DI에는 필드 주입, setter 주입, 생성자 주입 3가지 방법이 있다.
    * 의존관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
    *
    * 실무에서 컨트롤러, 서비스, 레파지토리와 같은 정형화된 코드는 주로 컴포넌트 스캔을 사용한다.
    * 정형화되지 않거나 상황에 따라 구현 클래스를 변경해야할 땐 설정을 통해 Spring Bean으로 등록한다.
     * 정형화되지 않은 경우의 예) 아직 저장소가 정해지지 않은 경우 - MemberRepository(interface) <...... MemoryMemberRepository
     * 기존 코드를 전혀 손대지 않고 저장소를 바꿔치기할 수 있다.
     * 
     * @Bean
     * public MemberRepository memberRepository() {
     *     return new MemoryMemberRepository();
     *     ==> return new DbMemberRepository();로 변경 가능(얘만 손대면 끝남)
     * }
    * */

    @GetMapping("/members/new")  // get은 조회할 때 사용
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")  // post는 데이터 전달할 때 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
