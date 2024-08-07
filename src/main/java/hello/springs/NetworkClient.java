package hello.springs;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//PostConstruct, PreDestroy annotation을 통한 콜백 : 객체 사용, 소멸 전 각자 필요한 작업을 해주도록 호출
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    private void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public void disConnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }
}
