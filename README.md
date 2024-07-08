# 스프링 핵심원리 강의 기반
###핵심 기능과 class 
1.  service(business logic 캡슐화), repository(db interaction)
2. order을 member에 따라 구분하고 discount policy 적용
    추상화에 의존(역할, 구현 분리) : interface implementation => 구현까지 의존하는 문제점 발생 가능

   
[AppConfig의 활용](https://github.com/pcochoco/spring_start/blob/main/src/main/java/hello/springs/AppConfig.java)
: DI(의존관계 주입), IoC(제어권의 역전) -> DIP, OCP 문제 해결 
- DIP : 구체 클래스 의존, OCP : 코드 변경 후 기능 확장
- AppConfig 구성 영역 -> 사용 영역과 분리 
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

