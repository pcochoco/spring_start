package hello.springs.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request") //request scope : http 요청 들어오고 나갈 때까지 유지, 각 요청별 별도 관리
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //proxy 방식
//적용 대상이 인터페이스면 INTERFACES 아니면 TARGET_CLASS 선택
//MyLogger을 상속받은 가짜 프록시 클래스(CGLIB) -> HTTP request와 상관없이 빈 의존관계 주입 -> 실제 필요시 호출
// => 다형성 : 상속받았으므로 원본인지 상관하지 않고 사용 가능
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }

}
