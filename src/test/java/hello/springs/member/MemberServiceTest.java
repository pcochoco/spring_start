package hello.springs.member;

import hello.springs.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //MemberService memberService = new MemberService(); 해줬던 것 변형

    MemberService memberService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member=new Member(Grade.VIP, 1L, "memberA");
        //when
        memberService.join(member);
        Member findMember=memberService.findMember(1L);

        //then
        //Assertions라는 api를 활용
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
