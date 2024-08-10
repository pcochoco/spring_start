package hello.springs.singleton_with_prototype;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//싱글톤 안에서 프로토타입 빈을 사용할 경우 의존관계가 한번만 주입되어 계속 같은 프로토타입 빈이 쓰이는 문제 발생
//1. proxy(가짜 객체를 만들어 CGLIB로 필요 시에 진짜 객체 호출
//2. provider로 dl(의존관계 찾음) 기능으로 스프링 컨테이너에서 찾아줄 수 있음


public class SingletonWithPrototypeTest {
    //spring container에서 prototype bean을 직접 요청해보기
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean pb1 = ac.getBean(PrototypeBean.class);
        pb1.addCnt();
        Assertions.assertThat(pb1.getCnt()).isEqualTo(1);
        PrototypeBean pb2 = ac.getBean(PrototypeBean.class);
        pb2.addCnt();
        Assertions.assertThat(pb2.getCnt()).isEqualTo(1);
    }

    //client bean이라는 싱글톤 빈이 prototype bean을 주입받는 경우
    //singleton과 prototype을 같이 사용하는 경우의 문제점
    //사용할 때마다 새로 생성되지 않음
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean cb1 = ac.getBean(ClientBean.class);
        int cnt1 = cb1.logic();
        Assertions.assertThat(cnt1).isEqualTo(1);

        ClientBean cb2 = ac.getBean(ClientBean.class);//singleton bean이므로 같은 객체 사용
        int cnt2 = cb2.logic();
        Assertions.assertThat(cnt2).isEqualTo(2);
        //prototype bean은 하나만 주입되어 계속 사용되기 때문
        //각각 다른 빈이 주입받는 경우 다른 prototype bean이 주입될 수 있음
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int cnt = 0;

        public void addCnt(){
            cnt++;
        }

        public int getCnt(){
            return cnt;
        }

        @PostConstruct
        public void init(){
            System.out.println("start");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
        }
    }

    static class ClientBean{
        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean){
           this.prototypeBean = prototypeBean;
        }

        public int logic(){
            prototypeBean.addCnt();
            int count = prototypeBean.getCnt();
            return count;
        }
    }

}
