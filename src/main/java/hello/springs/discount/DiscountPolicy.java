package hello.springs.discount;

import hello.springs.member.Member;
//F2로 오류난 곳으로 이동

//정률할인정책과 정액할인정책을 구현할 인터페이스
public interface DiscountPolicy {

    int discount(Member member, int price) ;
    //class가 아닌 interface로 선언
    //interface는 함수에 대한 body를 필요로 하지 않음

    //return은 할인 대상 금액
}
