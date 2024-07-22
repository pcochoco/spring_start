package hello.springs.member;

import hello.springs.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //dip, ocp 안지킨 코드
    //MemberService memberService = new MemberService();

    MemberService memberService;
    @BeforeEach
    public void beforeEach(){
        //dip, ocp를 지키는 경우
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(Grade.VIP, 1L, "memberA");

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        //Assertions라는 api를 활용
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
