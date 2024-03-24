package hello.springs.member;

public interface MemberRepository {
    //회원 저장
    //id로 회원 찾음

    void save(Member member);
    Member findById(Long memberId);
}

