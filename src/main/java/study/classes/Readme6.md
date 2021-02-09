### 상속
- 상속이란 상위클래스에서 정의한 필드와 메서드를 하위클래스도 동일하게 사용할 수 있게 물려받는 것
- 상속을 사용하면 코드 재사용이 용이해지고 클래스 간 계층구조를 분류하고 관리하기 쉬워진다.
- 접근지정자를 사용하여 사용할 수 없는 변수 및 메서드를 만들 수 있다.

#### 상속의 특징
- extends 키워드를 사용하여 상속
```java
class Dog extends Animal {
    
}
```  
- 다중상속 불가능
  - 자바는 다중상속을 허용하지 않는다.
```java
class Dog extends Animal, Carnivore {
    //불가능
}
```    
- 자바의 모든 클래스는 Object 클래스의 자식 클래스
  - 자바의 최상위 클래스는 Object
  - 한 클래스가 다른 클래스를 extend하지 않는다면 기본적으로 Object 클래스를 상속한다.  
  - 왜 Object를 최상위 클래스로 갖는가?
    - https://javapapers.com/java/why-object-is-super-class-in-java/
    - 객체지향적으로 개발을 하려면 오브젝트들을 여기저기 주고 받고 해야 하는데 이러한 상황때문에 하나의 일관된 타입이 필요했다.
- 부모 클래스의 메서드를 자식 클래스에서 재정의(Overriding) 가능
- final 클래스는 상속 불가, final 메소드는 오버라이딩 불가능  
- 동일한 이름의 변수가 부모 클래스와 자식 클래스에 존재하는 경우 부모 클래스의 변수는 가려진다.
  - 그럼 부모 클래스의 변수에 접근하려면?

#### super 키워드    
- 참조변수
  - 모든 인스턴스 메서드는 자신이 속한 인스턴스의 주소가 지역변수로 저장된다.
  - 이 지역변수로 this와 super의 값을 지정  
- super()는 부모의 기본 생성자를 호출
  - 자식클래스의 생성자 내에서 부모 생성자를 호출
- super.메소드명();
- super.변수명;

#### 메소드 오버라이딩
```java
class Parent {
  void method1() {}

  void method2() {}
}

class Child extends Parent {
  @Override
  void method2() {}

  void method3() {}
}
```
- 부모 메소드와 동일한 메소드 시그니처를 사용해야한다.
- 부모 메소드의 접근 제한자보다 강한 제한 사용이 불가능하다
  - 부모 메소드가 default를 사용중이라면 자식은 public, protected 그리고 default가 사용가능
    
#### 정적 메소드 디스패치
- 컴파일 단계에서 이미 메서드의 의존성을 알고 있는 것
```java
public class A{
     public void print(){
           System.out.println("A");
     }
}

public class B extends A {
     
     //메소드 오버라이딩 - A를 상속받았으나 함수를 재정의
     public void print(){
           System.out.println("B");
     }
}

public class Test{
      public static void main(String[] args){
             B b = new B();
             b.print();         //B를 출력 
      }
}
```
#### 다이나믹 디스패치
- 컴파일 단계에서는 알 수 없는 메서드의 의존성을 런타임에 바인딩 하는 것
- 굳이 추상클래스나 인터페이스가 아니어도 동적 디스패치 발생 가능
- 수퍼 클래스의 메서드를 오버라이딩해도 동적 디스패치가 가능하다
```java
class A {
  private BB bb;

  A(BB bb) {
    this.bb = bb;
  }

  void print() {
    bb.print();
  }
}

class B implements BB {
  public void print() {
    System.out.println("B");
  }
}
class B1 implements BB {
  public void print() {
    System.out.println("B1");
  }
}

interface BB {
  void print();
}
```
#### 더블 디스패치
- 다이나믹 디스패치를 두 번 실행
- 방문자 패턴