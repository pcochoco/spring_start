package hello.springs.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
//자동완성 : ctrl shift space
@Component
public class MemoryMemberRepository implements MemberRepository{

    //동시 접근을 위한 concurrent hashmap을 씀
    private static Map<Long, Member> store=new HashMap<>();//id, Member
    @Override //interface에 선언된 함수의 상속
    public void save(Member member){
        store.put(member.getId(),member); // id를 바탕으로 회원 정보 저장
        //name만이 아니라 Grade도 저장하므로
    }
    @Override
    public Member findById(Long memberId){
        return store.get(memberId); //store이라는 Map에서 get

    }
}
