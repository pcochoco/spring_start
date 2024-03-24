package hello.springs.discount;

import hello.springs.member.Grade;
import hello.springs.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy=new RateDiscountPolicy();
    @Test
    @DisplayName("VIP 10% 할인")
    void vip_o(){

        //given
        Member member=new Member(Grade.VIP,1L,"memberA");
        //when
        int discount=discountPolicy.discount(member,10000);
        //then
        //alt enter을 Assertions에서 누르면 add on demand static mport for ...
        //assertThat ...으로 생략
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    //성공안되는 사례로도 테스트를 꼭 만들어봐야함
    @Test
    @DisplayName("BASIC 할인 적용 안됨")
    void vip_x(){
        //given
        Member member=new Member(Grade.BASIC,2L,"memberA");
        //when
        int discount=discountPolicy.discount(member,10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}