### Enum
- enum 정의하는 방법
- enum이 제공하는 메소드
- java.lang.Enum
- EnumSet

##### Enum은 무엇인가.
- 열거형(enum)은 자바 데이터 타입 중 참조타입의 한 종류
- 서로 관련된 상수를 편리하게 선언하기 위한 것으로 여러 상수를 정의할 때 사용
- 열거형이 가지는 값만 관리하는 것이 아니라 타입까지 관리
- 자바의 열거형은 타입에 안전한 열거형(typesafe enum)이라서 실제 값이 같아도 타입이 다르면 컴파일 에러가 발생
- 값 뿐만 아니라 타입까지 체크하기 때문에 타입에 안전
- 열거형 상수는 상수의 값이 바뀌어도 기존의 소스를 다시 컴파일하지 않아도 된다는 장점

#### Enum은 왜 만들어 졌는가
- 상수를 클래스로 정의해서 관리할 때 얻을 수 있는 이점들을 모두 취하면서 상수들을 더욱 간단히 선언할 수 있도록 하기 위해 만들어졌다.
```java
public class EnumEx {
    public static final int BIT_COIN = 1;
    public static final int DOGE_COIN = 2;
    public static final int RANDOM_COIN = 3;

    public static void main(String[] args) {
        int type = BIT_COIN;
        switch (type) {
            case BIT_COIN:
                System.out.println("5천만원");
                break;
            case DOGE_COIN:
                System.out.println("일론머스크 나쁜놈");
                break;
            case RANDOM_COIN:
                System.out.println("의미없는 코인");
                break;
        }
    }
}
```
- 위 코드의 문제점은 BIT_COIN이나 DOGE_COIN등은 정수 1과 아무련 관련이 없고 또 굳이 1일 필요도 없다.
```java
//Enum을 사용하지 않는 경우
public class EnumEx{
    //코인 종류
    public static final int BIT_COIN = 1;
    public static final int DOGE_COIN = 2;
    public static final int RANDOM_COIN = 3;
    
    //코인회사 이름
    public static final int BIT_COIN = 1;
    public static final int BIT_MASTER = 2;
    public static final int MASTER_COIN_SHOP = 3;
    
}
```
- 위 처럼 의도치 않게 이름의 중복이 발생할수도 있다.
```java
public class EnumEx{
    //코인 종류
    public static final int BIT_COIN = 1;
    public static final int DOGE_COIN = 2;
    public static final int RANDOM_COIN = 3;
    
    //코인회사 이름
    public static final int COMP_BIT_COIN = 1;
    public static final int COMP_BIT_MASTER = 2;
    public static final int COMP_MASTER_COIN_SHOP = 3;
    
}
```
- 위의 코드처럼 이름을 바꾸거나
```java
interface Coin {
    int BIT_COIN = 1, DOGE_COIN = 2, RANDOM_COIN = 3;
}

interface Snack {
    int BIT_COIN = 1, COIN_CHOCOLATE = 2, COIN_COOKIE = 3;
}
```
- 위의 코드처럼 인터페이스로 만들수도 있다.
- 하지만 상수를 인터페이스로 관리하는것은 Anti-pattern
```java
if(Coin.BIT_COIN == Snack.BIT_COIN) {
        ...
    }
```
- 심지어 Coin과 Snack 모두 int타입 자료형이라 위와 같은 코드도 가능해진다.
- 게다가 Coin과 Snack은 사실 아무런 연관도 없다.

```java
class Coin {
    public static final Coin BIT_COIN = new Coin();
    public static final Coin DOGE_COIN = new Coin();
    public static final Coin RANDOM_COIN = new Coin();
}

class Snack {
    public static final Snack BIT_COIN = new Snack();
    public static final Snack COIN_CHOCOLATE = new Snack();
    public static final Snack COIN_COOKIE = new Snack();
}

public class EnumEx {
    public static void main(String[] args) {
        if (Coin.BIT_COIN == Snack.BIT_COIN) {}   // 컴파일 에러 발생
    }
}
```
- 위와 같은 코드로 작성하면 커파일 에러가 발생하며 코드를 애초에 작성하지 못하게 막을수 있고
1. 상수와 리터럴이 논리적인 연관이 없음
2. 서로 다른 개념끼리 이름이 충돌할 수 있음
3. 서로 다른 개념임에도 비교하는 코드가 가능함
- 위 3가지 문제 역시 모두 해결이 가능하다.

- 하지만 사용자 정의 타입은 switch 조건문에 들어갈 수 없다는 단점이 생긴다.
```java
public class EnumEx {
    public static void main(String[] args) {
        Coin type = Coin.BIT_COIN;
        switch (type) {   // 컴파일 에러
            case Coin.BIT_COIN:
                System.out.println("1");
                break;
            case Coin.DOGE_COIN:
                System.out.println("2");
                break;
            case Coin.RANDOM_COIN:
                System.out.println("3");
                break;
        }

    }
}
```
- Enum은 이렇게 상수를 클래스로 정의해서 관리할 때 얻을 수 있는 이점들을 모두 취하면서 상수들을 더욱 간단히 선언할 수 있도록 하기 위해 만들어진 것이다.


##### 열거형의 정의
- 기본적으로 상수명은 대문자로 작성하고, 여러 단어일 경우 언더바로 이어주는 것이 관례이다.
```java
public enum Week {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY,
        PUBLIC_HOLIDAY;
    }
```

- 열거형 상수에 괄호를 붙여서 값을 부여할 수 있다. 값을 부여하는 경우, 이에 해당하는 생성자도 함께 정의해줘야 한다.
```java
public enum Week {
        MONDAY(1, "월요일", "monday"),
        TUESDAY(2, "화요일", "tuesday"),
        WEDNESDAY(3, "수요일", "wednesday"),
        THURSDAY(4, "목요일", "thursday"),
        FRIDAY(5, "금요일", "friday"),
        SATURDAY(6, "토요일", "saturday"),
        SUNDAY(7, "일요일", "sunday"),
        PUBLIC_HOLIDAY(8, "공휴일", "public holiday");
        
        private final int num;
        private final String dayKor;
        private final String dayEng;
        
        Week(int num, String dayKor, String dayEng){
            this.num = num;
            this.dayKor = dayKor;
            this.dayEng = dayEng;
        }
        
        public int getNum(){
            return num;
        }
        public String getDayKor(){
            return dayKor;
        }
        public String getDayEng(){
            return dayEng;
        }
    }
```
- Enum은 switch문의 조건식으로 사용이 가능하지만 case문에 Enum의 이름이 아닌 상수의 이름을 적어야 한다.


###### enum이 제공하는 메소드
```java
public final String name()
public final int ordinal()
public String toString()
public final boolean equals(Object obj)
public final int hashCode()
public int compareTo(E obj)
public static <T extends Enum> T valueOf(Class enumType,String name)
public final Class <E> getDeclaringClass() // enum 객체의 타입 반환
public final Object clone() throws CloneNotSupportedException
protected final void finalize()
```
- toString()과 name()은 사실 같은 역할
  - 둘의 차이는 Override가 가능한가 불가능한가의 차이이다. 
  - 이는 final에 의해 발생하는 차이이다.
    
    
- name(): 열거형 상수의 이름을 문자열로 반환한다.
- ordinal():열거형 상수가 정의된 순서(0부터 시작)를 정수로 반환한다.
- toString():열거형 상수의 이름을 문자열로 반환한다.
- compareTo():정렬의 기준을 위한 메서드로 비교 대상보다 순서가 빠르면 -1, 같으면 0, 느리면 1을 반환한다.
- valueOf(enumType, name):지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다.
  - Enum 클래스를 살펴보면 valueOf()는 정의되어 있다.
- getDeclaringClass():열거형의 Class 객체를 반환한다.
- values():열거형의 모든 상수를 배열에 담아 반환한다.
  - values()는 상위 클래스에서 상속되는 것이 아닌 컴파일러가 자동으로 추가해주는 메소드


###### java.lang.Enum
- 모든 Enum 타입은 컴파일시 java.lang.Enum을 상속받는다.
- 단일 상속만 허용되는 자바이기 때문에 enum은 별도의 상속을 받을 수 없다.

###### EnumSet
- Set 인터페이스를 기반으로 열거형 타입으로 지정해놓은 요소들을 배열처럼 다룰수 있는 기능을 제공
- 모든 메소드가 static 키워드를 사용하여 정의되어 있기 때문에 별도의 객체 생성없이 사용할 수 있다고 하지만 사실은 객체를 생성할 수 없는 것
```java
public abstract class EnumSet<E extends Enum<E>>
extends AbstractSet<E>
implements Cloneable, Serializable{
    
}
```
- allOf(Class elementType): 지정한 Type의 모든 원소를 포함하는 EnumSet을 만든다.
- clone(): 이 집합의 복사본을 반환한다.
- complementOf(EnumSet s): 지정한 EnumSet에 포함되지 않은 원소만 갖는 동일한 Type의 EnumSet을 만든다.
- copyOf(Collection c): 지정한 Collection에서 초기화된 EnumSet을 만든다.
- copyOf(EnumSet s): 지정한 EnumSet과 동일한 Type을 가진 EnumSet을 만든다. 이 때, 처음과 동일한 원소(원소가 있는 경우)를 포함한다.
- noneOf(Class elementType): 지정한 Type을 가지는 빈 EnumSet을 만든다.
- of(E e), of(E first, E… rest), of(E e1, E e2): 지정한 원소(또는 원소들)를 포함하는 EnumSet을 만든다.
- range(E from, E to): 지정된 두 원소 사이에 있는 모든 원소를 포함하는 EnumSet을 만든다.

```java
public class EnumSetEx {
    public static void main(String[] args) {
        EnumSet<Fruit> enumSet = EnumSet.allOf(Fruit.class);

        System.out.println("> 전부 출력");
        System.out.println(enumSet);
        System.out.println();

        EnumSet<Fruit> RedFruit = EnumSet.of(Fruit.APPLE, Fruit.STRAWBERRY);

        System.out.println("> 빨간 과일만");
        System.out.println(RedFruit);
        System.out.println();

        System.out.println("> 빨간 과일을 제외한 나머지 과일");
        System.out.println(EnumSet.complementOf(RedFruit));
        System.out.println();

        System.out.println("> 범위 출력");
        System.out.println(EnumSet.range(Fruit.APPLE, Fruit.ORANGE));
        System.out.println();
    }
}

enum Fruit {
    APPLE, BANANA, ORANGE, STRAWBERRY, BLUEBERRY
}
```

##### Enum은 내가 원하는대로 정렬을 할 수 없는가?
- static final로 int 값을 상수로 줘서 정렬할 때는 순서를 고려하여 int 값을 부여했다.
- Enum의 경우 ordinal을 통해서 순서대로 보여줄 수 있다. 단, 이것은 처음에 입력해 놓은 순서대로 출력이 된다.
- 내가 정의한 것과 화면에 출력되야 하는 순서가 다른 경우 숫자를 부여하여 순서를 정해줘야한다. 
- 이 때 토비님으로부터 전수받은 기선님의 꿀팁을 활용하여! 숫자를 부여하여 할 때 확장성을 고려하여 숫자를 띄엄띄엄 입력한다. 
- 즉, 1, 2, 3이 아니라 10, 20, 30과 같이 여백을 추가하는 것이다. 이러면 중간에 다른 값을 추가하더라도 15, 25와 같이 중간 값을 주면서 추가하여 숫자가 밀리는 경우를 줄일 수 있다.

##### ordinal
- ordinal( )
- 열거형 상수가 정의된 순서(0부터 시작)를 정수로 반환한다.
```java
public class EnumExample {

    enum Fruit {
        Apple, Banana
    }

    public static void main(String[] args) {
        System.out.println(Fruit.Apple.ordinal());
        System.out.println(Fruit.Banana.ordinal());
    }
}
```
- ordinal()의 가장 큰 문제점은 바로 순서가 바뀔 수 있다는 가능성을 간과한 것
```java
public class EnumExample {

    enum Fruit {
        Grape, Apple, Banana
    }

    public static void main(String[] args) {
        System.out.println(Fruit.Apple.ordinal());
        System.out.println(Fruit.Banana.ordinal());

        if(Fruit.Apple.ordinal() == 0) {
            System.out.println("hello");
        }
    }
}
```
- Apple이 첫 번째일 경우에 hello를 출력하는데 만일 Apple 앞에 다른 과일이 추가된다고 생각해보자. 이렇게 될 경우 Apple은 0이 아니고 1이 되고 hello는 출력되지 못한다.

###### type-safety
- 타입이 일치해야 안전하다. 즉, String 타입에는 String 타입이 와야 한다는 것이다. 
- 같은 이름을 가진 상수라도 타입이 다르면 막아내는 것이 type-safety라고 볼 수 있다.

#### EnumSet에 new 연산자를 사용하지 않는 이유
참고 : https://siyoon210.tistory.com/152

- EnumSet은 다른 컬렉션들과 달리 new 연산자를 사용할 수 없다. 단지 정적 팩토리 메서드(static factory method)만으로 EnumSet의 구현 객체를 반환받을 수 있다. 왜 그럴까?

- EnumSet의 내부를 살펴보면 abstract 클래스, 추상클래스라는 것을 알 수 있다. 즉, EnumSet은 추상클래스이기 때문에 객체로써 생성 및 사용이 불가능한 것이다.

##### **이렇게 만든 이유?**
1.사용자 편의성 - (사용자는 어떤 구현 객체가 적합한지 몰라도 상관없다)
- RegularEnumSet은 원소의 갯수가 적을 때 적합하고, JumboEnumSet은 원소의 갯수가 많을때 적합하지만, 이는 EnumSet의 구현체들을 모두 알고 있는 사용자가 아니라면 난해한 선택지가 될 수도 있다. 하지만 EnumSet을 가장 잘 알고 있는 EnumSet 개발자가 적절한 구현 객체를 반환해준다면 EnumSet을 사용하는 입장에서는 어떤 구현체가 적합한지 고려하지 않아도 된다.

2. 사용자 편의성2 - (사용자는 빈번하게 발생되는 EnumSet초기화 과정을 간단히 진행할 수 있다.)
- EnumSet이 다루는 Enum의 모든 원소들을 Set에 담는 행위는 빈번하게 수행될 것으로 예상되는 일이다. 이러한 경우를 대비하여서 EnumSet의 allOf라는 메소드를 사용하면 모든 Enum 원소가 담겨있는 EnumSet을 쉽게 반환받고 사용 할 수 있다.

3. EnumSet의 확장성과 유지보수의 이점 
-  EnumSet을 유지보수하는 과정에서 RegularEnumSet과 JumboEnumSet 이외에 다른 경우를 대비하는 구현 클래스가 추가 된다고 하여도 내부에 감추어져 있기 때문에, EnumSet을 사용하던 기존의 코드에는 전혀 영향이 없다. 심지어 RegularEnumSet이 삭제된다 하더라도 사용자에게 영향이 없다. 이는 EnumSet의 확장성의 큰 이점으로 작용할 수 있다.


######Enum 싱글톤
[kwj1270](https://velog.io/@kwj1270/Enum) 님이 [싱글톤](https://github.com/kwj1270/TIL_DESIGN_PATTERN/blob/main/Singletone%20pattern.md) 을 활용해 만든 예제

```java

public enum EnumSettings {

    INSTANCE; // 생성자이자 식별자를 의미 -> 밑에 정의된 생성자에 파라미터가 있다면 여기에도 인수 넣어줘야한다.   
              // 식별자라고 말을 한 것은 해당 문구를 기준으로 객체를 참조하기에 싱글톤 기준이 된다.      

    private boolean darkMode = false; // 디폴트 값
    private int fontSize = 13; // 디폴트 값

    private EnumSettings() {} // 생성자

    public EnumSettings getInstance() {
        return INSTANCE;
    }

    public boolean getDarkMode(){
        return darkMode;
    }
    public int getFontSize(){
        return fontSize;
    }
    public void setDarkMode(boolean darkMode){
        this.darkMode = darkMode;
    }
    public void setFontSize(int fontSize){
        this.fontSize = fontSize;
    }
}
```
- 이렇게 했을 때 다음과 같은 장점이 있다고 한다.
  - 싱글톤의 특징(단 한 번의 인스턴스 호출, Thread간 동기화) 을 가지며 비교적 간편하게 사용할 수 있는 방법이다.
  - 단 한번의 인스턴스 생성을 보장하며 사용이 간편하고 직렬화가 자동으로 처리되고 직렬화가 아무리 복잡하게 이루어져도 여러 객체가 생길 일이 없다.
  - [리플렉션을 통해 싱글톤을 깨트릴 수도 없다.](http://wiki.hash.kr/index.php/%EC%8B%B1%EA%B8%80%ED%86%A4%ED%8C%A8%ED%84%B4#reflection.EC.9C.BC.EB.A1.9C_.EC.8B.B1.EA.B8.80.ED.86.A4_.EA.B9.A8.EA.B8.B0)