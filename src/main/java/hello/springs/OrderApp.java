package hello.springs;

import hello.springs.member.Grade;
import hello.springs.member.Member;
import hello.springs.member.MemberService;
import hello.springs.member.MemberServiceImpl;
import hello.springs.order.Order;
import hello.springs.order.OrderService;
import hello.springs.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//order까지 만들어 테스트해보는 일차적 방안
public class OrderApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        //ApplicationContext = 스프링 컨테이너
        //AppConfig에서 반환한 객체를 스프링 빈으로 모두 등록
        //@Bean의 이름도 메소드 이름으로 사용 (정의 따로 가능)

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);



        Long memberId = 1L;
        Member member = new Member(Grade.VIP, memberId, "memberA" );
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order="+order);
    }
}
