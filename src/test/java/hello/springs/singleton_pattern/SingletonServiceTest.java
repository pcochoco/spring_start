package hello.springs.singleton_pattern;

import hello.springs.AppConfig;
import hello.springs.singleton.SingletonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.springs.member.MemberService;
public class SingletonServiceTest {
    //객체 공유 - 비교
    @Test
    @DisplayName("singleton pattern 적용 객체")
    public void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("spring container and singleton")
    public void springContainer(){
        //using spring container
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //get bean
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //get another bean
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //차조값 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //객체 비교
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }

}
