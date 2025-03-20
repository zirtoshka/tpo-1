package itma;

import itma.task1.Tg;
import itma.task2.SkewHeap;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) throws Exception {
//        System.out.println("Hello world!");
////        System.out.println(Tg.getBernoulli(2));
//        for (int i = 0; i < 11; i++) {
////            System.out.println(i+" "+Tg.combinations(i, 4));
//        }
//        System.out.println(Tg.tg(BigDecimal.valueOf(-5*Math.PI/3),100));
//
//        System.out.println(Tg.tg(BigDecimal.valueOf(-6),40)+" ssss");

        SkewHeap heap = new SkewHeap();

//        // Вставка элементов
        heap.insert(10);
//
//        heap.printHeap();
//
//        heap.insert(20);
//        heap.printHeap();
//
        heap.insert(5);
        heap.insert(20);
        heap.printHeap();

//        heap.printHeap();
//
//        heap.insert(15);
//        heap.printHeap();
//
//        heap.insert(30);
//
//        // Печать кучи
//        System.out.println("Heap after insertions:");
//        heap.printHeap();
//
//        // Извлечение минимального элемента
//        System.out.println("Extracting min: " + heap.extractMin());
//        heap.printHeap();
//
//        System.out.println("Extracting min: " + heap.extractMin());
//        heap.printHeap();

    }
}