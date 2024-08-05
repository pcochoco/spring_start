package hello.springs.singleton;
/*
스프링 컨테이너의 기본 빈 등록 방식이 스프링 레지스트리를 활용하는 것
무상태 설계 - 의존, 수정 필드 불가 읽기용
-> 상태를 유지하는 필드가 있으면 다른 클라이언트의 호출에 의해 값 변경 가능 문제
 */
public class SingletonService {
    //1 instance made only
    private static final SingletonService instance = new SingletonService();

    //조회 허용
    public static SingletonService getInstance() {
        return instance;
    }

    //생성 불가
    private SingletonService() {
    }
}
