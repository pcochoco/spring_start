package hello.springs.member;

//비즈니스 로직, repository method 호출
public interface MemberService {
    void join(Member member); //MemberRepository에서 member 저장
    Member findMember(Long memberId); //findById

}
