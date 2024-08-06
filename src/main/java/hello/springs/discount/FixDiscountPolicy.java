package hello.springs.discount;

import hello.springs.member.Grade;
import hello.springs.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//멤버 등급에 따른 정액할인제
@Component
@Qualifier("fixDiscountPolicy")//구분자 -> Primary에 우위
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount=1000;
    @Override
    public int discount(Member member, int price){
        if(member.getGrade() == Grade.VIP) //enum type에 대해 == 씀
            return discountFixAmount;
        else{
            return 0;
        }
    }


}
