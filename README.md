# 스프링 핵심원리
### 핵심 기능과 class 
1.  [service](https://github.com/pcochoco/spring_start/blob/main/src/main/java/hello/springs/member/MemberService.java)(business logic 캡슐화 + repository 메서드 호출), [repository](https://github.com/pcochoco/spring_start/blob/main/src/main/java/hello/springs/member/MemberRepository.java)(db interaction)
2. order을 member에 따라 구분하고 discount policy 적용

   
    추상화에 의존(역할, 구현 분리용) : interface implementation => 코드가 구현까지 의존하는 문제점 발생 가능(dip, cop 위반)

   
[AppConfig의 활용(DI, IoC container)](https://github.com/pcochoco/spring_start/blob/main/src/main/java/hello/springs/AppConfig.java)
: DI(의존관계 주입), IoC(제어권의 역전) -> DIP, OCP 문제 해결 
- DIP(인터페이스, 추상화 의존) : 구체 클래스 의존, OCP : 코드 변경 후 기능 확장
    ### solid 법칙
  - srp : 단일 책임 클래스
  - ocp : 기능 확장 개방, 코드 변경 폐쇄
  - lsp(리스코프) : 상속되더라도 기능 보장
  - isp : 단일 인터페이스 (통합 x)
  - dip : 추상화 의존 
- AppConfig에 의한 객체의 구성 영역, 사용 영역과 분리  -> @Configruation - @Bean : 기술 지원 빈(bean을 하나씩 써주기 번거로움) 

   
 

### [의존관계 주입](src/main/java/hello/springs/member/MemberServiceImpl.java)(필드, 일반 메서드 주입 x)
생성자 주입(불변, 필수 등록 -> 없으면 에러, 한번 초기화) + 수정자 주입(@Setter)


- spring bean 등록(스프링에서 관리하는 자바 객체)
    - ApplicationContext(Bean Factory) interface(국제화, 환경변수 등 부가 기능) : xml, java ...를 활용 가능, bean definition 참조 등록 
      
    - @ComponentScan(프로젝트 최상위 위치) - @Component : 업무 로직빈
      - @ComponentScan : 빈 자동 등록(@Repository, @Component...에 대해)
      - Autowired(1개면 생략) -> @RequiredArgsConstructor로 생성자까지 생략 가능 
      - Filter

옵션 처리 : 주입할 스프링 빈이 없더라도 동작해야하는 경우 
- Autowired : 대상이 없으면 호출 x
- Nullable : null 반환 
- Optional : empty 자료형 반환


타입에 의한 조회 빈이 두개 이상인 경우 : Qualifier > Primary 사용, Autowired 필드명 매칭

### 스프링 빈 
- 빈 생명주기 콜백 : interface, method 지정, annotation(@PostConstruct, @PreDestroy 권장)
      데이터베이스 커넥션 풀로 서버와 연결 시 객체 초기화, 종료 작업 
- 빈 : spring container이 관리하는 객체
- 빈 등록 후 생성자 호출 + 의존관계 주입
- 빈 definition 
- 빈 스코프


    - 초기화 콜백 -> 객체의 생성과 초기화를 분리 
    - 소멸전 콜백
    - @PostConstruct, @PreDestroy 활용 
    
    - [싱글톤 빈](src/test/java/hello/springs/singleton_pattern/SingletonTest.java) : 한번 생성되어 끝까지 유지, 객체 하나 생성 후 공유([싱글톤 레지스트리](src/main/java/hello/springs/member/MemberServiceImpl.java), 빈 등록 기본 방식)
      - static 변수, static 메서드 활용 (state x)
      - 자바 코드에 대해 cglib 바이트코드 조작 라이브러리를 통해 AppConfig 클래스를 상속받은 다른 클래스를 만들어 등록
          -> @Configuration에 의해 AppConfig에서 여러번 객체를 불러도 하나로 호출됨 
    - [프로토타입](src/test/java/hello/springs/singleton_with_prototype/PrototypeTest.java) : 매번 새로 생성됨, 생성 후 초기화까지 컨테이너가 처리 이후 관리 x, 종료 메서드 x 
    - [동시 사용 문제](src/test/java/hello/springs/singleton_with_prototype/SingletonWithPrototypeTest.java) -> 싱글톤에 따라 프로토타입 빈이 한번 생성 + 주입 이후 계속 쓰임
      [Provider로 해결](src/test/java/hello/springs/singleton_with_prototype/PrototypeProviderTest.java)(DL : 의존관계 조회, 스프링 컨테이너에서 직접 필요한 의존관계를 찾는 기능)
  
 

### 웹 스코프 
웹 환경에서만 동작하는 스코프, 종료 메서드까지 호출


ex) request scope ... 

## request scope 예제 : http 요청 하나가 들어오고 나갈때까지 유지 
- http 요청 끝날 때 소멸
- 초기화 메서드 : 다른 http 요청과 구별을 위한 uuid
- 프록시 : 원래 클래스 상속받은 가짜 객체를 연관관계 주입해 실제 객체 호출을 미룸 (cglib)

=> http 요청이 들어와야 빈이 생성되는 차이를 해결할 수 있음 


  
