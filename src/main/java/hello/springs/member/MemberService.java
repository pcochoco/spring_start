package hello.springs.member;

public interface MemberService {
    void join(Member member); //MemberRepository에서 member 저장
    Member findMember(Long memberId); //findById

}
