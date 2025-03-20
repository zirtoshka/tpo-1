package itma;

import itma.task1.Tg;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
//        System.out.println(Tg.getBernoulli(2));
        for (int i = 0; i < 11; i++) {
//            System.out.println(i+" "+Tg.combinations(i, 4));
        }
        System.out.println(Tg.tg(BigDecimal.valueOf(-5*Math.PI/3),100));

        System.out.println(Tg.tg(BigDecimal.valueOf(-6),40)+" ssss");

    }
}