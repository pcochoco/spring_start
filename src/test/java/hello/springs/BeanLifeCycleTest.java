package hello.springs;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//NetworkClient 활용 빈 생명주기 콜백
public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        //ConfigruableApplicationContext : 컨텍스트를 수동으로 종료, refresh 가능
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient clent = ac.getBean(NetworkClient.class);
        ac.close(); //spring container 종료
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean
        public NetworkClient networkClient(){
            //객체 생성 이후 외부에서 수정자 주입
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring");
            return networkClient;
        }
    }

    @Configuration
    static class LifeCycleConfig2 {//configuration class에 static keyword 불가
        //NetworkClient에 대한 Configuration : 객체 생성과 반환
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }


}
