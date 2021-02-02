### 연산자
- 산술 연산자
- 비트 연산자
- 관계 연산자
- 논리 연산자
- instance of
- assignment operator (=)
- 화살표 연산자 (->)
- 3항 연산자
- 연산자 우선 순위
- Java13의 switch 연산자

#### 연산자
- 연산에 사용되는 기호
  - 연산이란 주어진 정보를 통해 일정한 규칙에 따라 어떤 값이나 결과를 구하는 과정
- 연산자는 연산을 수행하는 기호    
- 피연산자: 연산자의 작업 대상
    - 대부분의 연산자는 두개의 피연산자를 필요로 한다.
#### 산술 연산자
- + - % / *
- 수학적인 계산에 사용되는 연산자
- boolean을 제외한 다른 primitive 타입에 사용 가능
- 나누기 연산자의 경우 두 피연산자가 정수인 경우에는 정수형 나눗셈, 실수인 경우에는 실수형 나눗셈이 진행된다.
- 실수와 정수간 산술 연산을 진행하면 실수 연산자가 진행된다.
```java
public class Test {
  public static void main(String[] args) {
    double a = 1.2;
    int b = 2;
    System.out.println(a/b);//0.6
  }
}
```
- +연산자의 경우 두 피연산자 중 하나가 String 타입이라면 두 피연산자를 String으로 인식해 둘을 이어준다.
```java
public class Test {
  public static void main(String[] args) {
    double a = 1.2;
    String b = "hello";
    System.out.println(a+b); //1.2hello
  }
}
```
#### 비트 연산자
- >> << >>> ~ & | ^
- 비트 연산자는 피연산자를 비트단위로 연산한다.
- 실수는 허용하지 않고 정수만 허용한다.
- 주로 개별의 비트를 integer값으로 만들기 위해 사용한다.
- 비트 연산자는 실수, boolean, 배열 그리고 객체(Object)에는 사용할 수 없다.
- 두 피연산자 중 하나가 long이라면 연산되는 부분은 long타입으로, 그렇지 않다면 int로 연산된다.
- '<<'
  - 좌측에 있는 피연산자의 비트를 우측에 있는 피연산자의 숫자만큼 비트를 옮기는 연산자
  - 자릿수를 넘어가면 해당 비트는 사라지게 되고 비트를 옮겨 새로 생긴 자리의 비트는 0으로 대체한다.
- '>>'
  - 좌측에 있는 피연산자의 비트를 우측에 있는 피연산자의 숫자만큼 비트를 옮기는 연산자이다.
  - 자릿수를 넘어가면 해당 비트는 사라지게 되고 비트를 옮겨 새로 생긴 자리의 비트는 이전의 최상위 비트로 대체한다.
- '>>>'
  - 위에서 사용한 '>>' 비트 연산과 같은데 새로생긴 자리의 비트가 무조건 0으로 따라가는것이 다르다.
- &(and)
  - 피연산자간의 비트를 비교했을 때 두개 모두 1이면 1로 치환되고 아니면 0으로 치환된다.
- |(or)
  - 피연산자간의 비트를 비교했을 때 둘중 하나가 1이면 1로 치환된다. 
- ^(xor)  
  - 피연산자간의 비트를 비교했을 때 두개의 비트가 다르면 1 같으면 0을 반환한다.
- ~
  - 단항 연산자로 각 자리에 해당하는 비트를 모두 반전시킨다. 정확하게 말하자면 -1과 xor 연산을 한다. 

#### 관계 연산자
- == != < <= > >=
- 두 피연산자를 비교하는데 사용한다.
- 피연산자들의 값들이 같은지 비교하거나 크고 작음을 비교하는 연산
- 만약 두가지 타입을 비교하게 된다면 true 혹은 false를 반환한다.
  - 참조형의 경우에는 객체의 주소값을 비교한다.
    
#### 논리 연산자
- && || !
- 조건부 &&, || 연산자
  - && 두번째 피연산자의 결과값에 따라 평가
  - || 첫번째 피연산자의 결과값에 따라 평과  
    
#### instanceof
- 특정 객체가 특정 타입인지 확인하는 연산자
  - 참조 변수가 지정된 유형의 객체 참조를 포함하고 있는지의 여부를 확인
- 주로 다형성, 업-다운 캐스팅과 함께 설명되는 내용
- 왼쪽에는 참조변수, 오른쪽에는 타입(클래스명)이 피연산자로 위치
- true 혹은 false 반환
  - true를 반환한다면 참조변수가 검사한 타입으로 형변환이 가능하다는 뜻
- 값이 null인 참조변수에 대해선 항상 false를 반환
```java
class Parent {
	...
}
class Child extends Parent {
	...
}

Parent parent = new Child();
System.out.println(parent instanceof Child); //true
System.out.println(parent instanceof Parent); //true
System.out.println(parent instanceof Object); //true
```

#### 대입 연산자
- 연산자 오른쪽에 있는 값을 연산자 왼쪽에 있는 변수에 대입
- 객체 참조를 할당하기 위해 사용

#### 화살표 연산자
- 메서드를 하나의 식으로 표현한 것
```java
int min(int x, int y){

	return x < y ? x : y ;
	
}
```

#### 3항 연산자
- 3개의 피연산자를 가지고 연산을 한다.
- C언어로부터 차용
- 조건?true일때 반환값:false일때 반환값

#### Java 13 switch
```java
//기존 switch
switch(test)
{
    case 1:
        result = 3;
        break;
    case 2:
        result = 3;
        break;
    case 3:
        result = 33;
        break;
}
```
```java
//Java 12. 같은 행위를 하는 다른 케이스들을 묶을 수 있다.
//예를 들어 case 1과 case 2가 같은 행위를 한다면
int test = 0;

switch(test)
{
    case 1, 2:
        result = 3;
        break;
    case 3:
        result = 33;
        break;
}
```
```java
// -> 를 통해 break도 생략 가능
switch(test)
{
    case 1, 2 -> 3;
    case 3 -> 33;
}
```

```java
//Java 13
// yield가 추가되었다.
// break로 값을 반환하는 문법이 yield로 변경되었다.
private static int getValueViaYield(String mode) {
    int result = switch (mode) {
    case "a", "b":
    yield 1;
    case "c":
    yield 2;
    case "d", "e", "f":
    // do something here...
    System.out.println("Supports multi line block!");
    yield 3;
default:
    yield -1;
    };
    return result;
    }}
```
