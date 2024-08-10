package hello.springs.singleton_with_prototype;

import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeProviderTest {
    @Test
    void providerTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonWithPrototypeTest.ClientBean.class, PrototypeTest.PrototypeBean.class);

        ClientBean cb1 = ac.getBean(ClientBean.class);
        int cnt1 = cb1.logic();
        Assertions.assertThat(cnt1).isEqualTo(1);

        ClientBean cb2 = ac.getBean(ClientBean.class);
        int cnt2 = cb2.logic();
        Assertions.assertThat(cnt2).isEqualTo(1);
        //새로운 prototype bean이 생성되므로
    }

    static class ClientBean{
        @Autowired
        private ObjectProvider<SingletonWithPrototypeTest.PrototypeBean> pbProvider;
        //스프링 컨테이너를 통해 해당 빈을 찾아 반환(DL)
        //ObjectFactory 상속, 별도 라이브러리 필요없음
        public int logic(){
            SingletonWithPrototypeTest.PrototypeBean pb1 = pbProvider.getObject();
            pb1.addCnt();
            int cnt = pb1.getCnt();
            return cnt;
        }
    }

    static class ClientBean1{
        @Autowired
        private ApplicationContext ac;
        public int logic(){
            SingletonWithPrototypeTest.PrototypeBean pb1 = ac.getBean(SingletonWithPrototypeTest.PrototypeBean.class);
            pb1.addCnt();
            int cnt = pb1.getCnt();
            return cnt;
        }
    }
    /*
    항상 새로운 빈이 생성됨 -> DL : 의존관계 조회(탐색)
    ApplicationContext 전체를 주입받으면 스프링 컨테이너에 종속적인 코드가 됨
    */

    static class ClientBean2{
        @Autowired
        private Provider<SingletonWithPrototypeTest.PrototypeBean> provider;
        //자바 표준의 provider -> 다른 컨테이너를 함께 쓰는 경우 활용
        public int logic(){
            SingletonWithPrototypeTest.PrototypeBean pb1 = provider.get();
            pb1.addCnt();
            int cnt = pb1.getCnt();
            return cnt;
        }
    }
}
