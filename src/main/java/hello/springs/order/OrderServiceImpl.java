package hello.springs.order;

import hello.springs.discount.DiscountPolicy;
import hello.springs.discount.FixDiscountPolicy;
import hello.springs.member.Member;
import hello.springs.member.MemberRepository;
import hello.springs.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //OrderService입장에서 할인정책에 관해 몰라도 됨 => 단일 책임 원칙 (변경 x)
    //OrderServiceImpl은 DiscountPolicy interface, FixDiscountPolicy 구체화에 의존
    //=> DIP의 위반

    //정률 할인과 정액 할인을 왔다갔다하는 경우 코드 전체를 변경해야함
    //=> OCP의 위반

    //기존의 코드
    // private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    //private final MemberRepository memberRepository=new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member=memberRepository.findById(memberId); //회원 찾기
        int discountPrice=discountPolicy.discount(member,itemPrice); //회원 정보 바탕 가격 책정
        //grade만 넘겨줘도 됨 -> 멤버 통채로 안넘기고

        return new Order(memberId,itemName,itemPrice,discountPrice);

   }
}
