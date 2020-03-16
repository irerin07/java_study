# Java Study
이 저장소의 목적은 자바를 공부하면서 남들에게 쉽게 설명할 수 있을때까지 유지, 보수 하면서 만들어 나가는 것입니다.<br/>

## 목차
1. [JVM](https://github.com/irerin07/java_study#01-jvm-%EC%9E%90%EB%B0%94-%EA%B0%80%EC%83%81-%EB%A8%B8%EC%8B%A0)
2. [참조 타입](https://github.com/irerin07/java_study#02-%EC%B0%B8%EC%A1%B0-%ED%83%80%EC%9E%85)
3. [메모리 영역]()

## 01 JVM 자바 가상 머신
자바 프로그램은 완전한 기계어가 아닌, 중간 단계의 바이트 코드라 이를 해석하고 실행해 줄 가상의 운영체제가 필요하다.<br/>
이 역할을 수행하는 것이 JVM.<br/>
바이트 코드는 모든 운영체제에 상관없이 언제나 같은 실행결과를 보장하지만 JVM은 운영체제에 종속적이므로 운영체제에 맞는 JVM을 설치해야한다.<br/>
JVM은 JDK혹은 JRE를 설치하면 자동으로 설치된다.
#### JDK: Java Development Kit
- 자바 프로그램 개발시 필요한 도구(javac, java등)들을 포함
- JDK를 설치하면 JRE도 같이 설치가 된다.
#### JRE: Java Runtime Environment
- 컴파일된 자바 프로그램을 실행시킬 수 있는 자바 환경
- JVM이 자바 프로그램을 동작시킬 때 필요한 라이브러리 파일들과 기타 파일들을 가지고 있다.
- 자바 프로그램을 실행시키기 위해서는 필수로 설치되어야 한다. <br/>
![JVM](./image/JVM.png)

확장자가 .java인 소스 파일을 컴파일러(javac.exe)로 컴파일하여 확장자가 .class인 바이트 코드 파일을 생성한다.<br/>
생성된 바이트 코드 파일은 jvm 구동 명령어(java.exe)를 통해 JVM에서 해석되고 운영체제에 맞게 기계어로 번역이 된다.<br/>
Class Loader가 컴파일 된 자바 바이트 코드를 런타임 데이터 영역에 로드하고, 실행 엔진이 자바 바이트 코드를 실행한다.<br/>
자바의 가장 큰 장점은 한 번 작성하면 어디에서나 실행이 된다(Write Once, Run Everywhere)는 점이지만<br/>
JVM을 한 번 거쳐 기계어로 번역되고 실행되기 때문에 C, C++과 같은 언어들보다 속도가 느리다는 단점이 있다.<br/>
다만 JVM 내부의 최적화된 JIT컴파일러를 통해 속도의 격차는 많이 줄어들고 있다.
#### JIT: Just-In-Time compilation), 동적 번역
- 프로그램을 실제 실행하는 시점에 기계어로 번역하는 컴파일 기법
- Java에서는 바이트코드를 기계어로 번역할 때 사용된다.
- JIT 컴파일러는 같은 코드를 매번 해석하지 않고 실행할 때 컴파일을 하면서 해당 코드를 캐싱한다. 이후엔, 바뀐 부분만 컴파일 하고 나머지는 캐싱된 코드를 사용한다.<br/>

[맨위로](https://github.com/irerin07/java_study#java-study)

## 02 참조 타입
참조타입인 배열, 열거, 클래스, 인터페이스를 이용해서 선언된 변수는 메모리의 주소를 값으로 갖게된다.<br/>
이 주소를 통해 객체를 참조한다는 뜻에서 참조 타입이라고 부른다.<br/>
```
String name = "Mike";
```
String클래스 변수인 name은 힙 영역의 String 객체 주소 값을 가지고 있고 이 주소를 통해 객체를 참조하기때문에 String 클래스 변수를 참조 타입 변수라 한다.

## 03 메모리 영역
![RuntimeDataArea](./image/RuntimeDataAreas.png)<br/>
- java.exe로 JVM이 시작되면 JVM은 운영체제에서 할당받은 메모리 영역을 위와 같은 세부 영역으로 구분해서 사용한다.<br/>
- Pc Register, JVM Stack, Native Method Stack은 스레드 마다 하나씩 생성되고 Heap, Method Area, 런타임 상수 풀은 모든 스레드가 공유해서 사용한다.<br/>
---
### 메소드 영역 Method Area
- 코드에서 사용되는 클래스(~.class)들을 클래스 로더로 읽어 클래스별로 런타임 상수 풀, 필드 데이터, 메소드 데이터, 메소드 코드, 생성자 코드 등을 분류해서 저장한다.
- JVM이 시작할 때 생성된다.
#### 클래스 로더
- 자바는 런타임에 클래스를 처음 참조 시 해당 클래스를 로드하고 링크하는데(동적 로드) 이 동적로드를 담당하는 부분이 JVM의 Class Loader 
- Bootstrap Class Loader: Object 클래스 혹은 기본 Java API등을 로드
- Extension Class Loader: 기본 java API등을 제외한 확장 클래스들을 로드
- System Class Loader: 사용자가 지정한 $CLASSPATH 내의 클래스들을 로드
- User Defined Class Loader: 사용자가 직접 코드상에서 생성해 사용하는 Class Loader
![classloader](./image/클래스로더.png)  
Class Loader가 아직 로드되지 않은 클래스를 찾으면 위와 같은 과정을 거쳐 클래스를 로드하고 링크한뒤 초기화 한다.<br/>
- Loading – 클래스를 파일에서 가져와 JVM의 메모리에 업로드 한다.
- Verifying – 읽어 들인 클래스가 자바 언어 명세(Java Language Specification) 및 JVM명세에 명시된 대로 잘 구성되어 있는지 검사한다.
- Preparing – 클래스가 필요로 하는 메모리를 할당, 클래스에서 정의된 필드, 메서드, 인터페이스들을 나타내는 데이터 구조를 준비한다.
- Resolving – 클래스의 상수 풀 내 모든 심볼릭 레퍼런스를 다이렉트 레퍼런스로 변경한다.
   * 심볼릭 레퍼런스 -  객체를 가져오기 위해 사용되는 문자열
- Initializing – 클래스 변수들을 적절한 값으로 초기화. Static Initializer들을 수행하고 static 필드들을 설정된 값으로 초기화 한다.  
---
### 힙영역
- 객체와 배열이 생성되는 영역.
- 이곳에 생성된 객체와 배열은 JVM의 스택 영역의 변수나 다른 객체의 필드에서 참조한다.
- 참조하는 변수나 필드가 없다면 객체로서의 의미가 없기 때문에 JVM은 이를 Garbage Collector를 실행시켜 힙 영역에서 자동으로 제거한다.
 
### JVM Stack 영역
- 각 스레드마다 하나씩 존재하고, 스레드가 시작될 때 할당된다.
- 프로그램에서 추가적으로 스레드를 생성하지 않았다면 main 스레드만 존재하므로 JVM스택 역시 하나만 존재한다.
- Stack Frame이라는 구조체를 저장하는 스택
- JVM은 메소드를 호출할 때마다 JVM스택에 Stack Frame을 추가(Push)하고 메소드가 종료되면 해당 프레임을 제거(Pop)하는 동작만 수행
- Stack Trace의 각 라인은 하나의 스택 프레임을 표현한다.

![JvmStack](./image/JVMSTACK.png)
#### Local Variable Array: 지역변수 배열
- 0부터 시작하는 인덱스를 가진 배열
- 0은 메서드가 속한 클래스 인스턴스의 this 레퍼런스
- 1부터는 메서드에 전달된 파라미터들이 저장된다.
- 그 이후로는 메서드의 지역 변수들이 저장된다.
#### Operand Stack: 피연산자 스택
- 메서드의 실제 작업 공간
- 각 메서드는 피연산자 스택과 지역 변수 배열 사이에서 데이터를 교환하고, 다른 메서드 호출 결과를 추가하거나(push) 꺼낸다(pop).
- 피연산자 스택 공간이 얼마나 필요한지는 컴파일할 때 결정할 수 있으므로, 피연산자 스택의 크기도 컴파일 시에 결정된다.
#### Reference to Constant Pool: 현재 실행 중인 메서드가 속한 클래스의 런타임 상수 풀에 대한 레퍼런스
- 런타임 상수 풀: 클래스 파일 포맷에서 constant_pool 테이블에 해당하는 영역이며 JVM 동작에서 가장 핵심적인 역할을 수행하는 곳
    * 각 클래스와 인터페이스의 상수뿐만 아니라, 메서드와 필드에 대한 모든 레퍼런스까지 담고 있는 테이블
    * 즉, 어떤 메서드나 필드를 참조할 때 JVM은 런타임 상수 풀을 통해 해당 메서드나 필드의 실제 메모리상 주소를 찾아서 참조한다. 
![JvmInternal](./image/4ySVX.png)
[맨위로](https://github.com/irerin07/java_study#java-study)
