package hello.springs.member;
//구현체가 하나인 경우 Impl을 붙이는 관례
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    // = new MemoryMemberRepository();
    //ctrl shift enter : 세미콜론까지 자동완성
    public MemberServiceImpl(MemberRepository memberRepository){
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
