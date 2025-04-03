package task2;

import itma.task2.SkewHeap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SkewHeapTest {

    @Test
    @DisplayName("Вставка одного элемента в пустую кучу")
    void testInsertOneElement() {
        int data = 7;
        SkewHeap heap = new SkewHeap();
        heap.insert(data);
        assertEquals(data, heap.extractMin());
    }


//    @Test
//    @DisplayName("Вставка нескольких элементов")
//    void testInsertMultipleElements() {
//
//        SkewHeap heap = new SkewHeap();
//
//        heap.insert(10);
//        heap.insert(20);
//        heap.insert(5);
//        heap.insert(15);
//        heap.insert(30);
//
//        assertEquals(5, heap.extractMin());
//        assertEquals(10, heap.extractMin());
//        assertEquals(15, heap.extractMin());
//        assertEquals(20, heap.extractMin());
//        assertEquals(30, heap.extractMin());
//    }

    @Test
    @DisplayName("Вставка нескольких отрицательных элементов")
    void testInsertNegativeNumbers() {
        SkewHeap heap = new SkewHeap();

        heap.insert(-10);
        heap.insert(-20);
        heap.insert(-5);

        assertEquals(-20, heap.extractMin());
        assertEquals(-10, heap.extractMin());
        assertEquals(-5, heap.extractMin());
    }

    @Test
    @DisplayName("Вставка одинаковых элементов")
    void testInsertDuplicateElements() {
        SkewHeap heap = new SkewHeap();

        heap.insert(3);
        heap.insert(3);
        heap.insert(3);

        assertEquals(3, heap.extractMin());
        assertEquals(3, heap.extractMin());
        assertEquals(3, heap.extractMin());
    }

//    @Test
//    @DisplayName("Вставка в порядке возрастания")
//    void testInsertElementsInAscendingOrder() {
//        SkewHeap heap = new SkewHeap();
//
//        for (int i = 0; i < 5; i++) {
//            heap.insert(i);
//        }
//        for (int i = 0; i < 5; i++) {
//            assertEquals(i, heap.extractMin());
//        }
//    }

//    @Test
//    @DisplayName("Вставка в порядке убывания")
//    void testInsertElementsInDescendingOrder() {
//        SkewHeap heap = new SkewHeap();
//
//        for (int i = 5; i > 0; i--) {
//            heap.insert(i);
//        }
//        for (int i = 1; i <= 5; i++) {
//            assertEquals(i, heap.extractMin());
//        }
//    }

    @Test
    @DisplayName("Извлечение из пустой кучи")
    void testExtractMinFromEmptyHeap() {
        SkewHeap heap = new SkewHeap();
        assertThrows(IllegalStateException.class, heap::extractMin);
    }

    @Test
    @DisplayName("Извлечение из кучи с 1 элеметом")
    void testExtractMinFromSingleElementHeap() {
        SkewHeap heap = new SkewHeap();

        heap.insert(46);

        assertEquals(46, heap.extractMin());

        assertThrows(IllegalStateException.class, heap::extractMin);
    }

//    @Test
//    @DisplayName("Несколько вставок и извелчений")
//    void testExtractMinAfterMultipleInsertionsAndRemovals() {
//        SkewHeap heap = new SkewHeap();
//
//        heap.insert(50);
//        heap.insert(40);
//        heap.insert(60);
//        heap.insert(30);
//
//        assertEquals(30, heap.extractMin());
//        assertEquals(40, heap.extractMin());
//
//        heap.insert(35);
//        heap.insert(45);
//
//        assertEquals(35, heap.extractMin());
//        assertEquals(45, heap.extractMin());
//        assertEquals(50, heap.extractMin());
//        assertEquals(60, heap.extractMin());
//
//        assertThrows(IllegalStateException.class, heap::extractMin);
//    }

    @Test
    @DisplayName("print ^_-")
    void testPrintHeap() {
        SkewHeap heap = new SkewHeap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        heap.printHeap();
        String printedOutput = outContent.toString();

        assertTrue(printedOutput.contains("L---- 5"));
        assertTrue(printedOutput.contains("R---- 10"));
        assertTrue(printedOutput.contains("L---- 20"));

    }

}
