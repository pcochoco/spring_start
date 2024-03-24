package hello.springs.order;

import hello.springs.AppConfig;
import hello.springs.member.Grade;
import hello.springs.member.Member;
import hello.springs.member.MemberService;
import hello.springs.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class orderServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    //OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig;
        memberService= appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member( Grade.VIP, memberId, "memberA");
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
