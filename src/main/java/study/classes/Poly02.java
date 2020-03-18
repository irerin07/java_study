package main.java.study.classes;

class Car {
    public void carHonk() {
        System.out.println("자동차 경적 울리기");
    }
}
class Bus extends Car{
    public void carHonk() {
        System.out.println("자동차(버스)의 경적을 울리기");
    }
    public void busHonk() {
        System.out.println("버스 경적 울리기");
    }
}
class Bus2 extends Car{
    public void carHonk() {
        System.out.println("자동차(버스2)의 경적을 울리기");
    }
}
public class Poly02 {
    public static void main(String[] args) {
        Car c = new Bus();
        Car c1 = new Bus2();

        c.carHonk(); // 자동차(버스)의 경적을 울리기
        c1.carHonk(); // 자동차(버스2)의 경적을 울리기

    }
}
