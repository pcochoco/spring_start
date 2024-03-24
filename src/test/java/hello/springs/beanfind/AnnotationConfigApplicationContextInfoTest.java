package hello.springs.beanfind;

import hello.springs.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    //스프링 컨테이너 안에 있는 모든 빈 출력해보기
    //스프링 내부적으로 자체 확장하기 위해 쓰는 빈
    //appConfig도 스프링 빈으로 등록
    //밑의 4개 memberService ... 가 실제 등록한 빈
    void findAllBean(){ //Junit5를 쓸 때부터 설정하지 않아도 됨 public으로
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name="+beanDefinitionName+"object="+bean);

        }

    }
    //application bean : 만든 빈만 출력하도록 함
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object" + bean);
            }
        }


    }
}
