package hello.springs.member;

//비즈니스 로직, repository method 호출
//interface는 bean 등록 x -> @Component x
//jpa는 interface 기반 repository 구현, interface에 @Repository, extends JPARepository...
public interface MemberService {
    void join(Member member); //MemberRepository에서 member 저장
    Member findMember(Long memberId); //findById

}
