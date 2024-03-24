package hello.springs;
//첫번째 테스트 : 가입한 멤버와 찾은 멤버가 동일한지 출력해 비교

import hello.springs.member.Grade;
import hello.springs.member.Member;
import hello.springs.member.MemberService;
import hello.springs.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //psvm enter 로 단축
    public static void main(String[] args){
        //MemberService memberService=new MemberServiceImpl();
        //순수 자바로 이루어진 테스트 로직 -> 한계가 있음 (일일히 대조)
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig의 환경설정 정보를 가지고 @Bean 붙은 메소드를 Spring 컨테이너로 넣어 관리 (생성한 객체를)
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        //config 안에서 memberService라는 이름(메소드의) 객체를 찾고, 반환 타입은 MemberService






        Member member=new Member(Grade.VIP,1L,"memberA");
        memberService.join(member);

        //기존 서비스에서 찾는 것이므로 new memberService가 아님
        Member findMember=memberService.findMember((1L));
        System.out.println("new member="+member.getName());

        System.out.println("find member="+findMember.getName());
    }
}
