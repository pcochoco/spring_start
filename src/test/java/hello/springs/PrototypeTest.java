package hello.springs;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.junit.Assert.assertThat;

public class PrototypeTest {
    @Test
    public void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototype bean1");
        PrototypeBean pb1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototype bean2");
        PrototypeBean pb2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + pb1);
        System.out.println("bean2 = " + pb2);
        assertThat(pb1).isSameAs(pb2);

    }

    @Scope("prototype")
    static class PrototypeBean{
        public void init(){
            System.out.println("start");
        }

        //소멸콜백 호출되지 않음-> 계속 새로운 객체 생성 후 의존관계 주입까지만 관리하기 때문
        //직접 클라이언트의 호출 필요

        public void destroy(){
            System.out.println("destroy");
        }
    }
}
