package hello.springs.order;
//해당하는 member의 id에 따라 discount의 유무를 확인 후 주문
public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
