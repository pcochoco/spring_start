package hello.springs.member;

//repository : db의 data를 가공하는 함수를 담은 클래스
//다른 저장소(db)로 연결될 수 있도록 interface를 가짐
public interface MemberRepository {
    //회원 저장
    //id로 회원 찾음

    void save(Member member);
    Member findById(Long memberId);
}

