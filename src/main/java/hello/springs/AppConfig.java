package hello.springs;

import hello.springs.discount.DiscountPolicy;
import hello.springs.discount.FixDiscountPolicy;
import hello.springs.member.MemberRepository;
import hello.springs.member.MemberService;
import hello.springs.member.MemberServiceImpl;
import hello.springs.member.MemoryMemberRepository;
import hello.springs.order.OrderServiceImpl;
import hello.springs.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정정보 configuration
//객체의 생성과 의존관계 주입에만 관여 -> 구현체는 생성된 객체로 사용만 신경쓸 수 있음
//DI(IoC) container = AppConfig
@Configuration
public class AppConfig {
    @Bean
    //spring container로 등록되도록 하는 어노테이션
    public MemberService memberService(){

        //return new MemberServiceImpl(new MemoryMemberRepository());
        return new MemberServiceImpl(memberRepository()); //MemberRepository도 AppConfig에 추가

        //원래는 MemberServiceImpl안에서
        //MemberRepository memberRepository = new MemoryMemberRepository(); 로 만들어줌 : 구체화된 걸 넣어주는 것
        //MemberServiceImpl에서는 MemberServiceImpl(MemberRepository ...) 로 변경되도록
        //MemberRepository memberRepository만 부르면서 MemoryMemberRepository로 만들어줌
        //MemberServiceImpl은 인터페이스에만 의존
        //AppConfig에서 구체화를 맡음
    }

    @Bean //@ComponentScan 프로젝트 최상단, 각 repository, class 별 annotation에 따라 생략 가능
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();

    }
    @Bean
    public OrderService orderService(){
        //return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        return new OrderServiceImpl(memberRepository(), new FixDiscountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy(); //할인정책을 고정
        //return new RateDiscountPolicy();

    }
}
