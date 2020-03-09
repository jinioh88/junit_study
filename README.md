'자바와 Junit을 활용한 실용주의 단위 테스트'를 읽고 개인적인 정리공간입니다. 자세한 사항은 책을 참고하시기 바랍니다.

## 1장 첫번째 JUnit 테스트 만들기
테스트 준비, 실행, 단언
- 테스트에서 어떤 것을 하기 위해서는 먼저 테스트 상태를 설정하는 준비 단계의 일들을 해야 한다. 
- 테스트를 준비한 후 검증하려는 메스드를 실행한다.
- 마지막으로 기대하는 결과를 단언한다. 

## 2장 JUnit 진짜로 써보기
어떤 테스트를 작성할 수 있는지 결정
- 종속적인 조건들은 테스트 하나로 묶을 수 있다.
- 새로 작성한 코드에 테스트 코드를 작성할 때 가장 신경 쓰는 부분이 어딘지 알고 있어야 한다.

@Before 메서드로 테스트 초기화
- 테스트 코드가 중복된 로직을 가지고 있다면 @Before 메서드로 이동하자.
- 각 JUnit 테스트를 실행할 때마다 @Before 애너테이션으로 표시된 메서드를 먼저 실행한다. 
- JUnit은 테스트마다 새로운 인스턴스를 생성해 모든 테스트를 독립적으로 만든다. 

## 3장 JUnit 단언 깊게 파기
JUnit 단언
- JUnit에서 단언은 테스트에 넣을 수 있는 정적 메서드 호출이다. 
- 각 단언은 조건이 참인지 검증하는 방법이다. 
- 단언한 조건이 참이 아니면 테스트는 그 자리에서 멈추고 실패를 보고한다. 
- assertTrue
  - 단언은 JUnit 테스트에서 광범위하게 사용되기 떄문에 대부분 군더더기를 줄이고자 정적 임포트를 사용한다. 
- assertThat은 명확한 값을 비교
  - assertThat() 정적 메서드는 햄크레스트 단언의 예이다.
  - 햄크레스트 단언의 첫 번쨰 인자는 실제 표현식이고, 두 번쨰 인자는 매처 이다. 
- 중요한 햄크레스트 매처 살펴보기
  - JUnit에 포함되어 있는 햄크레스트 CoreMatchers 클래스는 바로 매처를 시작할 수 있는 매처 모음을 제공한다. 
  - 더 많은 햄크레스트 매처를 도입할수록 테스트 코드의 표현력이 깊어진다. 
  - 자바 컬랙션 객체를 비교할 때는 equalTo() 메서드를 사용한다. 
  - 경우에 따라 is 장식자를 추가해 매처 표현의 가독성을 더 높일 수 있다. 우리의 뇌는 is 같은 단어가 없어도 인식할 수 있기 떄문에 장식자는 뺴고 equalTo 매처를 사용하는 것이 더 좋다.
  - 어떤것을 부정하는 단언을 만든다면 not 매처를 사용한다.
  - null 값이나 null이 아닌경우 nullValue(), notNullValue()로 검사할 수 있다. null이 아닌 값을 자주 검사하는 것은 설계 문제거나 지나치게 걱정하는 것이다. 이런 검사는 불필요하고 가치가 없다. 
- 부동소수점 수를 두개 비교
  - 컴퓨터는 모든 부동소수점 수를 표현할 수 없다.
  - 두 개의 float 혹은 double 양을 비교할 땐 두 수가 벌어질 수 있는 공차 또는 허용 오차를 지정해야 한다. 
  - closeTo() 정적 메서드로 비교 할 수 있다. 

예외를 기대하는 세가지 방법
- 단순한 방식: 애너테이션 사용
  - JUnit의 @Test 애너테이션은 기대한 예외를 지정할 수 있는 인자를 제공한다. 
- 옛 방식: try/catch와 fail
  - 발생한 예외 처리 방법으로 try/catch 블록을 활용할 수도 있다. 
  - 옛 방식은 예외가 발생한 후 어떤 상태를 검사할 때 유용하다. 예를 들어 메시지를 확인하려는 경우가 그렇다.
- 새로운 방식: ExpectedException 규칙
  - JUnit은 바로 사용할 수 있는 소수의 유용한 규칙을 제공한다.
  - 특히 ExpectedException규칙은 단순 방식과 옛 방식의 좋은 점만 모았다.
  - ExpectedException 규칙을 사용하려면 테스트 클래스에 ExpectedException 인스턴스를 public으로 선언하고 @Rule 애너테이션을 붙이여한다.

예외 무시
- 검증된 예외를 처리하려고 테스트 코드에 try/catch 블록을 넣지 말자. 대신 발생하는 예외를 다시 던지자.

## 4장 테스트 조직
AAA로 테스트 일관성 유지
- AAA는 준비, 실행, 단언을 말한다. 
- AAA로는 다음 일을 할 수 있다. 
  - 준비
    - 테스트 코드를 실행하기 전에 시스템이 적절한 상태에 있는지 확인한다. 
    - 객체들을 생성하거나 이것과 의사소통하거나 다른 API를 호출하는 것 등이다
  - 실행
    - 테스트 코드를 실행한다.
  - 단언
    - 실행한 코드가 기대한 대로 동작하는지 확인한다. 
  - 사후
    - 때에 따라 이 단계가 필요하다.
    - 테스트를 실행할 때 어떤 자원을 할당했다면 잘 정리되었는지 확인해야 한다. 
    
동작 테스트 VS 메서드 테스트
- 단위테스트를 작성할 때는 먼저 전체적인 시각에서 시작해야 한다. 
- 개별 메서드를 테스트하는 것이 아니라 클래스의 종합적인 동작을 테스트해야 한다. 

테스트와 프로덕션 크드의 관계
- 테스트는 주어진 프로젝트 안에서 프로덕션 코드와 분리해야 한다. 
- 테스트와 프로덕션 코드 분리
  - 테스트 배포 여부를 고려하기보다 테스트를 프로덕션 소스와 같은 프로젝트에 넣을지 결정해야 한다. 
  - 적어도 세 가지 선택지가 있다.
   1. 테스트를 프로덕션 크드와 같은 디렉터리 및 패키지에 넣기
     - 이 정책을 쓰면 실제 배포할 떄 테스트 코드를 걷어 내는 스크립트가 필요하다. 
     - 구현하기 쉽지만 실제 시스템에 이렇게 하지 않는다. 
   2. 테스트를 별도 디렉터리로 분리하지만 프로덕션 코드와 같은 패키지에 넣기
     - 대부분 회사에서 이것을 선택한다. 
     - 각 테스트는 검증하고자 하는 대상 클래스와 동일한 패키지를 갖는다. 
     - 테스트 클래스는 패키지 수준의 접근 권한을 가진다. 
- 내부 데이터 노출 vs 내부 동작 노출
  - 테스트를 위해 내부 데이터를 노출하는 것은 테스트와 프로덕션 코드 사이에 과도한 결합을 초래한다. 
  - 클래스의 private 메서드를 테스트 하고픈 충동이 있을 수 있는데, 하지 않는게 좋다. 
  - 위의 해결책은 private 메서드를 추출해 다른 클래스로 이동하는 것이다. 이렇게 하면 그 클래스의 유용한 public 메서드가 된다. 

집중적인 단일 목적 테스트의 가치
- 다수의 케이스를 별도의 JUnit 테스트 메서드로 분리하자
  - 단언이 실패했을 때 어느 동작에서 문제가 있는지 빠르게 파악할 수 있다. 
  - 실패한 테스트를 해독하는 데 필요한 시간을 줄일 수 있다 
  - 모든 케이스가 실행되었음을 보장할 수 있다. 
  
문서로서의 테스트
- 일관성 있는 이름으로 테스트 문서화 
  - 테스트 케이스를 단일 메서드로 결합할수록 테스트 이름 또한 일반적이고 의미를 잃어 간다. 
  - 좀 더 작은 테스트로 이동할수록 각각은 분명한 행동에 집중한다. 
- 테스트를 의미 있게 만들기
  - 다른 사람이 테스트가 어떤 일을 하는지 파악하기 어려워하면 주석말고도 테스트 이름을 개선해야 한다. 
  
@Before와 @After 더 알기
- @Before 메서드를 활용하면 중복된 코드로 유지 보수 악목에 빠지는걸 막을 수 있다. 
- @Before 메서드는 클래스 내에 있는 모든 테스트 메서드 실행에 앞서 실행된다. 
- 다수의 @Before가 있다면 JUnit은 실행 순서를 보장하지 않느다. 일정한 순서가 필요하다면 단일 @Before메서드로 결합해 실행하자. 
- @After 메서드는 클래스에 있는 각 테스트를 한 후 실행되며, 테스트가 실패하더라도 실행된다. 

BeforeClass와 AfterClass 어노테이션
- 매우 드문 경우에만 테스트 클래스 수준의 초기화인 @BeforeClass가 필요하다. 
- 이것은 클래스에 있는 어떤 테스트를 처음 실행하기 전에 한 번만 실행한다. 

녹색이 좋다: 테스트를 의미 있게 유지
- 테스트 제외
  - 다수의 실패를 다루는 한 가지 해결책은 문제가 있는 테스트에 집중하고 다른 실패 테스트는 주석처리하는 것이다.
  - JUnit은 주석처리보다 나은 @Ignore 애터이션을 제공한다. @Ignore("don't forget me!")
  - @Ignore의 설명 메세지는 선택사항이다. 
  
## 5장 좋은 테스트의 FIRST 속성
FIRST: 좋은 테스트 조건
- 다음 FIRST 원리를 따르면 테스트 작성자가 흔히 빠지는 위험을 피할 수 있다. 
- 테스트를 먼저 작성하고 코드를 작성하는걸 TDD라 한다. 
- 단위테스트와 TDD의 차이점은 TDD는 테스트를 먼저 작성한다는 것이다. 

[F]IRST: 빠르다
- 빠른 테스트는 코드만 실행하며 소요시간은 수 밀리초 수준이다. 
- 느린 테스트는 데이터베이스, 파일, 네트워크 호출처럼 필요한 외부 자원을 다루는 코드를 호출한다. 
- 가장 먼저 느린 테스트에 대한 의존성을 줄여라.

F[I]RST: 고립시킨다
- 단순히 외부 저장소와 상호작용하게 되면 테스트가 가용성 혹은 접근성 이유로 실패할 가능성이 증가한다.
- 좋은 테스트는 다른 단위 테스트에 의존하지 않느다. 
  - 테스트 코드는 어떤 순서나 시간에 관계없이 실행할 수 있어야 한다. 
- 테스트에 두 번째 단언을 추가할 떄 다음과 같이 스스로 질문해야 한다.
  - "이 단언이 동작을 검증하도록 돕는가. 아니면 내가 새로운 테스트 이름으로 기술할 수 있는 어떤 동작을 대표하는가"

FI[R]ST: 좋은 테스트는 반복 가능해야 한다 
- 반복가능한 테스트는 실행할 떄마다 결과가 같아야 한다. 
- 반복 가능한 테스트를 만들려면 직접 통제 할 수 없는 외부 환경에 있는 항목들과 격리시켜야 한다.

FIR[S]T: 스스로 검증 가능하다
- 테스트에 필요한 어떤 설정 단계증 자동화를 해야 한다. 
- 인텔리제이 같은 IDEA의 Infinitest 같은 도구를 고려해 볼 수 있다. 

FIRS[T]: 적시에 사용한다


## 6장 Right-BICEP: 무엇을 테스트할 것인가?(다시)
Right-BICEP은 무엇을 테스트할지에 대해 쉽게 선별한다.

[Right]-BICEP: 결과가 올바른가?
- 테스트 코드는 무엇보다도 먼저 기대한 결과를 산출하는지 검증할 수 있어야 한다.

Right-[B]ICEP: 경계 조건은 맞는가?
- 클래스가 외부에서 호출하는 API이고 클라이언트를 완전히 믿을 수 없다면 나쁜 데이터에 대한 보호가 필요하다. 
- 하지만 클라이언트가 여러분 팀 소속이라면 앞서 살펴본 보호절들을 제거하고 클라이언트에 알려도 된다.
  - 이것은 완벽히 합법적인 선택이고 코드에서 불필요하게 과도한 인자를 검사하는 군더더기를 줄여준다. 

경계 조건에서는 CORRECT를 기억하라
- CORRECT 약어는 잠재적인 경계조건을 기억하는 데 도움을 준다. 
- [C]onformance(준수): 값이 기대한 양식을 준수하고 있는가?
- [O]rdering(순서): 값의 집합이 적절하게 정렬되거나 정렬되지 않았나?
- [R]ange(범위): 이성적인 최솟값과 최댓값 안에 있는가?
- [R]eference(참조): 코드 자체에서 통제할 수 없는 어떤 외부 참조를 포함하고 있는가?
- [E]xistence(존재): 값이 존재하는가(널이 아니거나, 0이 아니거나, 집합에 존재하는가 등)?
- [C]ardinality(기수): 정확히 충분한 값들이 있는가?
- [T]ime(절대적 혹은 상대작 시간): 모든 것이 순서대로 일어나는가?

Right-B[I]CEP: 역 관계를 검사할 수 있는가?
- 종종 수학 계산에서 역 관계를 적용해 행동을 검사한다. (곱셈으로 나눗셈을 검증하고 뺄셈으로 덧셈을 검증하는 식)
