# 스프링 핵심원리 강의 기반
- service, repository
- order을 member에 따라 구분하고 discount policy 적용


[AppConfig의 활용](https://github.com/pcochoco/spring_start/blob/main/src/main/java/hello/springs/AppConfig.java)
: DI(의존관계 주입), IoC(제어권의 역전) -> DIP, OCP 문제 해결 
- spring bean 등록
    - ApplicationContext(Bean Factory) interface : xml, java ...로 구현 
    - @ComponentScan - @Component : 업무 로직
    - @Configruation - @Bean : 기술 지원
 

### 스프링 빈 
- 빈 생명주기 콜백 : interface, method 지정, annotation(권장)
- 빈 스코프
    
    - 싱글톤 : 한번 생성되어 끝까지 유지
    - 프로토타입 : 매번 새로 생성됨, 생성 후 초기화까지 컨테이너가 처리 
    - 동시 사용 문제 -> 싱글톤에 따라 프로토타입 빈이 한번 생성 + 주입 이후 계속 쓰임
    - Provider로 해결(DL)

