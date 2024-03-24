package hello.springs.member;
//file - settings - keymap에서 단축키 검색, 설정
//alt shift 0 = alt + ins : constructor ... 작업
//option - compact middle packages 세팅 해제
public class Member {
    private Grade grade;
    private Long id;
    private String name;

    public Member(Grade grade, Long id, String name){
        this.grade=grade;
        this.id=id;
        this.name=name;

    }
    public Long getId(){

        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public Grade getGrade(){
        return grade;
    }
    public void setGrade(Grade grade){
        this.grade=grade;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}



