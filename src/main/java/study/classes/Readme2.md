### 클래스
- 클래스 정의하는 방법
- 객체 만드는 방법 (new 키워드 이해하기)
- 메소드 정의하는 방법
- 생성자 정의하는 방법
- this 키워드 이해하기

#### 클래스를 정의하는 방법
-  클래스: 같은 속성과 기능을 가진 객체를 총칭하는 개념
- 클래스를 정의하는 방법
  - 클래스는 필드, 생성자, 메소드로 나뉜다.
    - 필드: 속성, 멤버 변수
      - 인스턴스 변수: 인스턴스가 갖는 변수. 인스턴스 생성시 만들어진다. heap 영역에 할당, GC에 의해 관리
      - 클래스 변수: static 키워드가 붙은 인스턴스 변수. 해당 클래스에서 파생된 모든 인스턴스는 이 변수를 공유한다. static 영역에 할당되고 GC의 관리를 받지 않는다. 
    - 생성자: 변수에 초기값을 대입하여 사용하듯 클래스에도 생성후 초기화를 담당하는 부분
    - 메소드: 기능에 해당하며 클래스를 사용하여 메소드에 정의된 행위를 실행한다.
      - 인트턴스 메소드: 인스턴스 변수와 연관된 작업을 하는 메서드. 인스턴스를 통해서만 호출이 가능하다.
      - 클래스 메소드: 인스턴스와 관계 없는 메소드
    - 초기화 블록: 조건문, 반복문등을 사용해 명시적 초기화에서 불가능한 초기화를 할 수 있다.
- 클래스 이름과 파일 이름이 반드시 같은 필요는 없다. 같은 패키지 안이라면 탐색이 가능하기 때문.
  - 다만 해당 클래스 파일을 외부에서 사용하기 위해서는 파일과 클래스명이 같아야 한다. (public 접근제어자 필요)
```java
class Class {               // 클래스
    String constructor;
    String instanceVar;     // 인스턴스 변수
    static String classVar; // 클래스 변수

    static {                // 클래스 초기화 블록
        classVar = "Class Variable";
    }

    {                       // 인스턴스 초기화 블록
        instanceVar = "Instance Variable";
    }

    Class() {                // 생성자
        constructor = "Constructor";
    }

    void instanceMethod() {       // 인스턴스 메서드
        System.out.println(instanceVar);
    }

    static void classMethod() {   // 클래스 메서드
        System.out.println(classVar);
    }
}
```
#### 객체 만드는 방법 (new 키워드)
- 정의한 클래스를 사용하기 위해서는 인스턴스를 생성해야한다.
- 이를 위해 우리는 new 키워드를 사용한다.
- new 키워드를 사용하게되면 메모리 힙 영역에 데이터를 저장할 영역을 할당 받게 되고 해당 영역의 주소를 객체에게 반환하여 객체를 사용할 수 있도록 한다.

- 생성과정
  1) new 연산자가 메모리 영역에 저장공간을 할당받음.

  2) 생성자가 객체를 초기화

  3) new연산자가 새로 생긴 객체의 주소를 변수에 저장함.

  4) 변수를 통해 해당 객체에 접근함.

- 클래스 내부에 다른 클래스를 정의하는 중첩 클래스
```java
class OuterClass {
	
  static class StaticNestedClass {
  }
  class InnerClass { //내부 클래스
  }
}
```
- 사용 이유?
  - 단 한 곳에서만 사용되는 클래스들을 논리적으로 그룹화
  - 캡슐화에 더 도움을 준다. 최상위클래스 A와 B가 있다고 가정해보자. B는 A의 멤버에 접근해야 하는데, 이 것이 아니라면 A의 멤버들은 private으로 선언될 수 있다. B를 A내에 감춤으로써, A의 멤버들은 private으로 선언될 수 있고, B역시 접근가능하다. 게다가 B 자신도 외부로부터 감춰진다.
  - 코드를 더 가독성 있게 만들고, 유지가능하게 만든다. 작은 클래스들을 상위클래스로 중첩하는 것은 코드를 더 사용할 곳 가까이에 위치시킨다.  

#### 메소드 정의하는 방법
- 접근지정자, 리턴타입, 메소드명, 파라미터(선택)로 구성된 정의부 
- 메소드의 기능을 호출하는 호출부
```java
//접근지정자 리턴타입 메소드명(파라미터)
public String getName() {... 호출부 ...}
public void setName(String name) {..}
```
- 메소드 오버로딩
  - 파라미터의 갯수나 타입이 다르다면 동일한 이름의 메소드명을 사용해 메소드를 정의할 수 있는 기법
  - 매개변수는 동일하고 리턴타입이 다른 경우에는 사용 불가능
```java
//메소드 오버로딩
public String getName() {...}
public String getName(int age) {...}

//대표적인 메소드 오버로딩
System.out.println("바이에른 뮌헨");
System.out.println("1900);
```    
- 메소드 오버라이딩
  - 상위 클래스가 정의한 메소드를 하위 클래스가 가져와 변경하거나 확장하는 기법
```java
class Person {
	public void info() {
		System.out.println("사람입니다");
	}
}

class Adult extends Person {
	@Override
	public void info() {
		System.out.println("어른입니다.");
	}
}

class Child extends Person {
	@Override
	public void info() {
		System.out.println("어린이입니다.");
	}
}

Person person = new Person();
Adult adult = new Adult();
Child child = new Child();

person.info();     //사람입니다.
adult.info();      //어른입니다.
child.info();      //어린이입니다.
```    
- 상위 클래스의 메소드를 하위 클래스에서 메소드를 재정의하기 때문에 확장과 변경에 용이하다

#### 생성자 정의하는 방법
- 클래스를 생성하고 객체를 호출할 때 객체를 초기화 하기 위해 사용되는 것이 생성자
1. 기본 생성자: 클래스 내부에 선언된 생성자가 없는 경우 객체 생성시에 컴파일러가 자동으로 생성
2. 묵시적 생서자: 파라미터 값을 가지지 않는 생성자
3. 명시적 생성자: 파라미터 값을 가지는 생성자

- 생성자의 특징
  - 리턴 타입을 가지지 않는다.
  - 생성자는 클래스 이름과 동일하다.
  - 모든 클래스는 한개 이상의 생성자를 가진다.
  - 명시적 생성자만 선언되있는 경우 파라미터가 없는 생성자를 사용하고 싶다면 묵시적 생성자를 선언해주어야 한다.
    - 생성자가 클래스 내부에 선언되어 있기 때문에 기본 생성자가 생성되지 않는다.

#### this 키워드 이해하기
- this키워드는 클래스가 인스턴스화 되었을 때 자기 자신의 메모리 주소를 가진다.
- 자기자신을 나타내는 키워드
- 클래스 내부의 필드 이름과 메소드를 통해 넘어온 파라미터의 변수명이 동일한 경우 this키워드를 이용해 클래스 내부의 ㅍ필드 이름과 파라미터를 구분할 수 있다.
```java
class Person {
	private String name;
	public Person(String name) {
		this.name = name;   // 클래스 필드 name = 파라미터 name
	}
}
```
- this()는 클래스 내부에서 생성자를 호출
- this()의 경우 호출하는 곳의 첫 번째 문장에서 호출되어야 한다.
- 생성자 파라미터가 있는 경우 this()안에 생성자 파라미터 타입에 맞게 직접 입력하여 사용할 수 있다.
```java
class Person {
	private String name;
	public Person(String name) {
		this.name = name;   // 클래스 필드 name = 파라미터 name
	}
	
	public Person(String name){
		this(name+"입니다");
	}
}
```
```java
//오버로딩된 다른 생성자를 호출할 때

public Class Test{
       int a;
       int b;

       public void Test(){
           this(null,null);
       }

       public void Test(int a){
           this(a, null);
       }

       public void Test(int b){
           this(null, b);
       }

       public void Test(int a , int b){
            this.a = a;
            this.b = b;
       }
}
```
```java
public Class Test{
       int a;
       public Test(int a){
            this.a = a;
       }
        
       public Test getTest(){
            return this;
       }
}
```

#### super
- super는 자식클래스가 부모클래스로부터 상속받은 멤버를 사용하고자 할 때 사용한다.
```java
class Parent{
   int a = 10;
}

class Child extends Parent{
    int a = 20;
    
    void childMethod(){
           System.out.println(a);         //20
           System.out.println(this.a);     //20
           System.out.println(super.a);   //10
    }
}
```
```java
class Canvas2D{
	int x;
	int y;
		
	public Canvas2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
	
class Canvas3D extends Canvas2D{
	int z;

	public Canvas3D() {
		this(100, 200, 300);
	}
		
	public Canvas3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
}
```


