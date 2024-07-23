package hello.springs.member;
//구현체가 하나인 경우 Impl을 붙이는 관례
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository; //생성자 주입용 private, final -> 불변
    // = new MemoryMemberRepository();
    //ctrl shift enter : 세미콜론까지 자동완성

    //생성자 주입 : 1번만 초기화
    public MemberServiceImpl(MemberRepository memberRepository){
        //AppConfig / SpringContainer에 의한 의존관계 주입 = DI = 동적 객체 의존관계 (runtime에 어떤 객체가 사용되는지 알 수 있음)
        //의존관계는 정적, 동적인 경우로 분리해 존재
        //IoC : 제어의 역전

        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member){
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }
}
