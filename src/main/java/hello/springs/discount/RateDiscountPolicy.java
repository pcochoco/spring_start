package hello.springs.discount;

import hello.springs.member.Grade;
import hello.springs.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary //타입 조회 시 여러 결과 -> 우선권
@Component
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent=10;
    @Override
    // 커서 맞추고
    //ctrl shift t
    //JUnit 5로 설정
    public int discount(Member member, int price){
        if(member.getGrade()== Grade.VIP){
            //Grade는 private이므로 member.Grade로 부를 수 없음

            return price*discountPercent/100; //퍼센테이지는 정수형으로만 설정했으므로
        }
        else{
            return 0;
        }

    }

}
