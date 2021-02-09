### 인터페이스
- 자바에서는 단일 상속만을 지원하기 때문에 이를 극복하기 위해 Interface를 지원한다
- 일종의 추상클래스이며 추상클래스보다 추상화 정도가 높다.
- 자바 8 이전에는 인터페이스에 일반 메소드를 구현할 수 없었지만 8버전부터 default와 static 예약어를 통해 일반 메소드 구현이 가능해졌다.
```java
public interface 인터페이스명{
	// 상수
	타입 상수명 = 값;
	
	// 추상 메소드
	타입 메소드명(매개변수, ...);

	// 디폴트 메소드
	default 타입 메소드명(매개변수, ...){
		// 구현부
	}

	// 정적 메소드
	static 타입 메소드명(매개변수, ...){
		// 구현부
	}
}
```
1. 상수 **(절대적)**
   - 인터페이스에서 값을 정해줄태니 함부로 바꾸지 말고 제공해주는 값만 참조하라.
2. 추상 메소드 **(강제적)**
   - 가이드만 줄테니 오버라이딩해서 재구현하여 사용하라.
3. 디폴트 메소드 **(선택적)**
   - 인터페이스에서 기본적으로 제공해주지만, 구현내용이 원하지 않는다면 오버라이딩해서 재구현하여 사용하라
4. 스태틱 메소드 **(절대적)**
   - 인터페이스에서 제공해주는 것으로 무조건 사용

#### 추상클래스는 "is-a : ~는 ~이다" 의 개념이다.

#### 인터페이스는 "has-a : ~는 ~를 할 수 있다." 의 개념이다.

- 인터페이스를 사용함에 따라 
  - 개발 기간 단축
  - 클래스간 결합도 낮춤
    - 코드의 종속성을 줄이고 유지보수성을 높일 수 있다.
  - 표준화 가능  


#### 구현 방법
- implements 키워드 사용
```java
interface Flyable{
    void fly();
}

interface Jumpable {
	 void jump();
}

class Bird implements Flyable, Jumpable {
    @Override
    public void fly() {
        System.out.println("Bird's Flying");
    }
		@Override
		public void jump() {
        System.out.println("Bird's Jumping");
		}
}
```

#### 익명 구현 객체
- 일회성의 구현 객체를 위해 소스파일을 만들고 클래스를 선언하는 것은 낭비
- 자바에서는 소스 파일 없이도 구현객체를 만들 수 있는 방법을 제공
```java
public class App {
    public static void main(String[] args){

        RemoteControl remoteControl = new RemoteControl() {
            @Override
            public void turnOn() {

            }

            @Override
            public void turnOff() {

            }

            @Override
            public void setVolume(int volume) {

            }
        };

    }
}
```

#### 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
- 자손클래스의 인스턴스를 부모타입의 참조변수로 참조하는 것이 가능하다는 것을 알 수 있다.
- 해당 인터페이스 타입의 참조변수로클래스의 인스턴스를 참조할 수 있으며, 인터페이스 타입으로 형변환도 가능하다.
```java
public interface Animal {
    void sound();
}
public class Dog implements Animal {

    @Override
    public void sound() {
        System.out.println("멍멍");
    }

    public void sleep() {
        System.out.println("새근새근 잡니다.");
    }
}
public class Lion implements Animal {

    @Override
    public void sound() {
        System.out.println("크아앙");
    }

    public void hunting() {
        System.out.println("사냥을 합니다.");
    }
}
public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal lion = new Lion();

        dog.sound();
        lion.sound();

      //  dog.sleep();     X 
      //  lion.hunting();  X
      
      ((Dog)dog).sleep(); 	  // O
      ((Lion)lion).hunting();     // O
    }
}
```
- dog가 바라보고 있는 것은 Animal 인터페이스이기 때문에 dog.sleep()은 호출하지 못하므로 ((Dog)dog).sleep() 으로 캐스팅하여 사용해야 한다.

- 반대로 Dog dog = new Animal(); 는 선언하지 못하고 컴파일 에러가 발생한다.
- 객체의 상세정보를 제외하고 분류(classification)의 정의에 중요한 부분을 강조를 할 수 있다.

#### 인터페이스 상속
- 인터페이스 역시 상속을 사용할 수 있으며 클래스와 달리 다중 상속을 허용한다.
- 상위 인터페이스를 구현하는 클래스는 하위 인터페이스의 메소드 뿐만 아니라 상위 인터페이스의 모든 추상 메소드에 대한 실체 메소드까지 가지고 있어야 한다.
- 상위 인터페이스의 메서드 중에 메서드 명과 파라미터 형식은 같지만 리턴 타입이 다른 메서드가 있다면 다중 상속이 불가능해진다.
```java
public interface A { void m1(); }
public interface B { void m1(); void m2();}
public interface B2 { int m1(); void m2(); }
public interface C extends A, B { void m2();}
public interface C2 extends A, B2 {void m3();} // 'm1()' in 'B2' clashes with 'm1()' in 'A'; methods have unrelated return types
```

##### 두개 인터페이스를 구현하는 경우 메소드 시그니쳐가 같은 케이스에서는 어떻게 해야할까?
```java
public interface JoinMember {

    default void preJoin(){
        System.out.println("pre member");
    }
}

public interface JoinGroup {

  default void preJoin(){
    System.out.println("pre group");
  }
}

public class Member implements JoinMember, JoinGroup{

  @Override
  public void preJoin() {
    JoinMember.super.preJoin();
    JoinGroup.super.preJoin();
  }
}
```
- 중복되는 인터페이스의 추상 메소드를 재정의 하여 사용할 수 있다.
- preJoin 메소드를 재정의 하지 않으면 컴파일 에러가 발생한다.

- 스태틱 메소드가 아님으로 참조할 수 있는 방법이 위와 같다 **".super.메소드"**

- 스태틱 메소드는 오버라이드가 안된다.

##### 인터페이스 상속과 인터페이스 레퍼런스
```java
public interface Bar extends Foo {
    void barMethod();
}
public interface Foo {

  void fooMethod();
}
```
```java
public class DefaultFoo implements Bar {
    @Override
    public void barMethod() {
        System.out.println("bar method - by DefaultFoo");
    }

    @Override
    public void fooMethod() {
        System.out.println("foo method - by DefaultFoo");
    }

    public void testMethod_1(){
        System.out.println("test method _ 1");
    }
    public void testMethod_2(){
        System.out.println("test method _ 2");
    }
}
```
```java
import me.ks.study.interfacestudy.Bar;
import me.ks.study.interfacestudy.DefaultFoo;

public class App {
    public static void main(String[] args){
        System.out.println("hi");

        DefaultFoo defaultFoo = new DefaultFoo();
        defaultFoo.fooMethod();
        defaultFoo.barMethod();
        defaultFoo.testMethod_1();
        defaultFoo.testMethod_2();

        Bar bar = defaultFoo;
        bar.barMethod();
        bar.fooMethod();
    }
}
```
```java
DefaultFoo defaultFoo = new DefaultFoo();
defaultFoo.fooMethod();
defaultFoo.barMethod();
defaultFoo.testMethod_1();
defaultFoo.testMethod_2();
```
- 클래스 타입 레퍼런스는 해당 클래스에 정의된 메소드를 모두 호출 가능

```java
Bar bar = defaultFoo;
bar.barMethod();
bar.fooMethod();
```
- 인스턴스 레퍼런스는 해당 인터페이스를 구현한 클래스 인스턴스를 가리킬 수 있고 해당 인터페이스에 선언된 메서드만 호출할 수 있다.

##### 다중 인터페이스에서의 인터페이스 레퍼런스
- 다중 인터페이스를 통해 클래스는 여러개의 규칙을 이행할 수 있다.
- 한 클래스가 여러 인터페이스를 구현했다면, 각 인터페이스로 구분해서 그 객체를 사용할 수 있게 된다.
  - 구현체를 어떤 인터페이스 레퍼런스에 담느냐에 따라 사용할 때 따르는 규칙이 달라진다.
```java
public class DefaultFoo implements Bar, Baz {
    @Override
    public void barMethod() {
        System.out.println("bar method - by DefaultFoo");
    }

    @Override
    public void fooMethod() {
        System.out.println("foo method - by DefaultFoo");
    }

    @Override
    public void bazMethod() {
        System.out.println("baz method  By DefaultFoo");
    }
}
```    
```java
import me.ks.study.interfacestudy.Bar;
import me.ks.study.interfacestudy.Baz;
import me.ks.study.interfacestudy.DefaultFoo;

public class App {
    public static void main(String[] args){
        System.out.println("hi");

        DefaultFoo defaultFoo = new DefaultFoo();
        defaultFoo.fooMethod();
        defaultFoo.barMethod();

        Bar bar = defaultFoo;
        bar.barMethod();
        bar.fooMethod();

        Baz baz = defaultFoo;
        baz.bazMethod();
    }
}
```
- DefaultFoo 는 Bar 인터페이스와 Baz 인터페이스를 구현하였음으로 위 예제를 보듯이
- **Bar 라는 인터페이스 레퍼런스에 담을수도 있고**
- **Baz 라는 인터페이스 레퍼런스에 담을수도 있다.**

#### 인터페이스의 기본 메소드 (Default Method), 자바 8
- 인터페이스에 메소드 선언이 아니라 "구현체"를 제공하는 방법
- 해당 인터페이스를 구현한 클래스의 어떠한 영향없이 새로운 기능을 추가하는 방법
- 디폴트 메소드는 해당 인터페이스를 구현한 구현체가 모르게 추가된 기능
  - 컴파일 에러는 발생하지 않지만, 특정한 구현체의 로직에 따라 런타임 에러가 발생할 수 있다.
  - 꼭 문서화를 하여 실수하지 않도록 하자
- Object가 제공하는 기능과 같은 기본 메소드는 제공할 수 없다 (equals, hashCode)
  - 구현체가 재정의하여 사용하는것은 가능
- 본인이 수정할 수 있는 인터페이스게만 기본 메소드를 제공 가능
- 인터페이스를 상속받은 인터페이스에서 다시 추상 메소드로 변경 가능
- 인터페이스 구현체가 default method를 재정의 가능
##### 왜 Default 메소드를 사용하지?
- 하위호환성
- 인터페이스에 기능을 추가했지만 해당 인터페이스를 구현한 클래스들이 깨지지 않도록 하기 위함
- default method로 제공한 기능이 항상 제대로 동작할 것이라는 보장이 없다.
- 구현체들은 default method가 어떻게 구현되어 있는지 알 수 없다.
    - 위 예제와 같은 경우에서는 "getName()"이 어떻게 구현되어있는지 모르는 상황에서
    - default method에서 getName() 메소드를 호출하여 로직을 구현하였다.
- **문제가 발생되면 RuntimeException이 발생할 수 있다.**
#### 인터페이스의 static 메소드, 자바 8
- 해당 인터페이스를 구현한 모든 인스턴스, 해당 타입에 관련되어 있는 유틸리티, 헬퍼 메소드를 제공하고 싶다면? 
  - static method로 제공할 수 있다.
```java
import java.util.Locale;

public interface Foo {
    void printName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔출력한다.
     */
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    String getName();

    static void printAnything(){
        System.out.println("FOO static");
    }
}
```    
```java
public class FooMain {

    public static void main(String[] args){
        DefaultFoo foo = new DefaultFoo("sson");

        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();
    }
}
```
#### (optional) 인터페이스의 private 메소드, 자바 9
- 
