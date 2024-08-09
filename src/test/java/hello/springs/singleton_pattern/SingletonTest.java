package hello.springs.singleton_pattern;

import hello.springs.AppConfig;
import hello.springs.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//comparing instances of singleton
public class SingletonTest {
    @Test
    @DisplayName("DI container without spring") //다른 참조값, 비교해도 다름
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    //singleton bean
    @Scope("singleton")
    static class SingletonBean(){
        @PostConstruct
        public void init(){
            System.out.println("start");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
        }
    }
}
