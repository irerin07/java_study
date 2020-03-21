package main.java.study.classes;

import jdk.nashorn.internal.runtime.regexp.joni.WarnCallback;

class Watch{
    String company;
    String model;
    String color;
    int price;

    Watch(){

    }
    Watch(String company){
        this(company,"-", "-", 0);
    }
    Watch(String company, String model){
        this(company, model,"-", 0);
    }

    Watch(String company, String model, String color){
        this(company, model, color, 0);
    }
    Watch(String company, String model, String color, int price){
        this.company = company;
        this.model = model;
        this.color = color;
        this.price = price;
    }
}

public class Constructor01 {
    public static void main(String[] args) {
        Watch w1 = new Watch();
        Watch w2 = new Watch("iou");
        Watch w3 = new Watch("teston", "k4434-z");
        Watch w4 = new Watch("pahltek", "o-mk-9888-z", "Bright Red");
        Watch w5 = new Watch("kios","87u Br", "Dark Grey", 5800);

        System.out.println(w1.company);
        System.out.println(w1.model);
        System.out.println(w1.color);
        System.out.println(w1.price);
        System.out.println();
        System.out.println(w2.company);
        System.out.println(w2.model);
        System.out.println(w2.color);
        System.out.println(w2.price);
        System.out.println();
        System.out.println(w3.company);
        System.out.println(w3.model);
        System.out.println(w3.color);
        System.out.println(w3.price);
        System.out.println();
        System.out.println(w4.company);
        System.out.println(w4.model);
        System.out.println(w4.color);
        System.out.println(w4.price);

    }
}
