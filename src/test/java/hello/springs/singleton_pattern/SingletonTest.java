package hello.springs.singleton_pattern;

import hello.springs.AppConfig;
import hello.springs.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
//comparing instances of singleton
public class SingletonTest {
    @Test
    @DisplayName("DI container without spring")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService2).isNotSameAs(memberService1);
    }
}
