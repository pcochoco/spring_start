# 스프링 핵심원리 강의 기반
### 핵심 기능과 class 
1.  service(business logic 캡슐화), repository(db interaction)
2. order을 member에 따라 구분하고 discount policy 적용
    추상화에 의존(역할, 구현 분리) : interface implementation => 구현까지 의존하는 문제점 발생 가능

   
[AppConfig의 활용](https://github.com/pcochoco/spring_start/blob/main/src/main/java/hello/springs/AppConfig.java)
: DI(의존관계 주입), IoC(제어권의 역전) -> DIP, OCP 문제 해결 
- DIP(인터페이스, 추상화 의존) : 구체 클래스 의존, OCP : 코드 변경 후 기능 확장
    ### solid 법칙
  - srp : 단일 책임 클래스
  - ocp : 개방 폐쇄
  - lsp(리스코프) : 상속되더라도 기능 보장
  - isp : 단일 인터페이스 (통합 x)
  - dip : 추상화 의존 
- AppConfig 구성 영역 -> 사용 영역과 분리 
- spring bean 등록
    - ApplicationContext(Bean Factory) interface : xml, java ...로 구현 (BeanFactory의 상속)
    - @ComponentScan - @Component : 업무 로직
      - @ComponentSccan : 빈 자동 등록
      - Autowired(1개면 생략)
      - Component, Controller, Service, Repository, Configuration 대상
      - Filter
    - @Configruation - @Bean : 기술 지원 (bean을 하나씩 써주기 번거로움)
 

### 의존관계 주입
생성자 주입 + 수정자 주입 (필드, 일반 메서드 주입 x)


옵션 처리 : 주입할 스프링 빈이 없더라도 동작해야하는 경우 
- Autowired : 대상이 없으면 호출 x
- Nullable
- Optional : empty 자료형 반환


*롬복 : Autowired 대신 RequiredArgsConstructor


조회 빈이 두개 이상인 경우 : Qualifier > Primary 사용, Autowired 필드명 매칭

### 스프링 빈 
- 빈 생명주기 콜백 : interface, method 지정, annotation(@PostConstruct, @PreDestroy 권장)
      데이터베이스 커넥션 풀로 서버와 연결 시 객체 초기화, 종료 작업 
- 빈 : spring container이 관리하는 객체
- 빈 등록 후 생성자 호출 + 의존관계 주입
- 빈 definition 
- 빈 스코프
  
    
    - 싱글톤 : 한번 생성되어 끝까지 유지, 객체 하나 생성 후 공유 (state x)
      - static 변수, static 메서드 활용
      - 자바 코드에 대해 cglib 바이트코드 조작 라이브러리를 통해 AppConfig 클래스를 상속받은 다른 클래스를 만들어 등록 
    - 프로토타입 : 매번 새로 생성됨, 생성 후 초기화까지 컨테이너가 처리 이후 관리 x, 종료 메서드 x 
    - 동시 사용 문제 -> 싱글톤에 따라 프로토타입 빈이 한번 생성 + 주입 이후 계속 쓰임
    - Provider로 해결(DL : 의존관계 조회, 스프링 컨테이너에서 직접 필요한 의존관계를 찾음)
 

### 웹 스코프 

