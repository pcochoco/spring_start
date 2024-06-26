package hello.springs.order;

public class Order {

    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice){
        this.memberId=memberId;
        this.itemName=itemName;
        this.itemPrice=itemPrice;
        this.discountPrice=discountPrice;
    }

    public int calculatePrice(){
        return itemPrice - discountPrice;

    }
    public void setMemberId(Long memberId){
        this.memberId=memberId;
    }
    public Long getMemberId(){
        return memberId;
    }
    public int calculatedPrice(){
        return itemPrice-discountPrice;
    }
    public void setItemName(String itemName){
        this.itemName=itemName;
    }
    public String getItemName(){
        return itemName;
    }
    public void setItemPrice(int itemPrice){
        this.itemPrice=itemPrice;
    }
    public int getItemPrice(){
        return itemPrice;
    }
    public void setDiscountPrice(int discountPrice){
        this.discountPrice=discountPrice;
    }
    public int getDiscountPrice(){
        return discountPrice;
    }
    @Override
    public String toString(){
        return "Order{"+
                "memberId="+memberId+
                ",itemName="+itemName+
                ",itemPrice="+itemPrice+
                ",discountPrice="+discountPrice+
                '}';
        //System.out.println("order="+order);시
        //toString의 내용이 출력되어 편리

    }


}
