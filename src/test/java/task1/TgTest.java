package task1;


import itma.task1.Tg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

import static itma.task1.Tg.HALF_PI;
import static org.junit.jupiter.api.Assertions.*;

public class TgTest {
    private static final BigDecimal EPSILON = new BigDecimal("1E-10");

    @ParameterizedTest
//    @CsvSource({
//            "0.0, 10, 0.0",
//            "0.1, 10, 0.100334672", с периодом
//            "10, 100, 0.648360827",
//            "-1, 30, -1.5574077246549023",
//            "-6, 30, 0.29100619138474"
//    })
    @CsvFileSource(resources = "/task1/tg.csv")
    @DisplayName("Тестирование tg(x) адекватных случаев")
    void testTg(BigDecimal x, int n, BigDecimal expected) throws Exception {
        BigDecimal result = Tg.tg(x, n);
        assertEquals(expected.setScale(9, RoundingMode.HALF_UP), result.setScale(9, RoundingMode.HALF_UP));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/task1/tg2.csv")
    @DisplayName("Тестирование tg(x) адекватных случаев")
    void testTg2(BigDecimal x, int n, BigDecimal expected) throws Exception {
        BigDecimal result = Tg.tg(x, n);
        assertTrue(expected.subtract(result).abs().compareTo(BigDecimal.ONE) <= 0);
    }



    @Test
    @DisplayName("Тестирование исключений в tg(x)")
    void testTgExceptions() {
        assertThrows(InvalidParameterException.class, () -> Tg.tg(BigDecimal.ONE, 0));
        assertThrows(InvalidParameterException.class, () -> Tg.tg(BigDecimal.ONE, -1));

        assertThrows(ArithmeticException.class, () -> Tg.tg(HALF_PI, 10));
        assertThrows(ArithmeticException.class, () -> Tg.tg(HALF_PI.negate(), 10));
        assertThrows(ArithmeticException.class, () -> Tg.tg(HALF_PI.multiply(BigDecimal.valueOf(3)), 10));
        assertThrows(ArithmeticException.class, () -> Tg.tg(HALF_PI.multiply(BigDecimal.valueOf(-5)), 10));
    }


    @ParameterizedTest
    @CsvSource({
            "5, 2, 10",
            "1, -1, 0",
            "3, 5, 0"
    })
    @DisplayName("Тестирование биноминального коэффициента")
    void testBinoum(Integer n, Integer k, BigDecimal expected) throws Exception {
        BigDecimal result = Tg.binomCoeff(n, k);
        assertEquals(expected.setScale(9, RoundingMode.HALF_UP), result.setScale(9, RoundingMode.HALF_UP));
    }


    @Test
    public void testClassInstantiation() {
        Tg tg = new Tg();
        assertNotNull(tg);
    }


    @Test
    @DisplayName("Тестирование исключений вычисления бернулли")
    void testBernoulliExceptions() {
        assertThrows(InvalidParameterException.class, () -> Tg.getBernoulli(-3));
    }


    @Test
    @DisplayName("Тестирование исключений вычисления факториала")
    void testFactorialExceptions() {
        assertThrows(InvalidParameterException.class, () -> Tg.factorial(-3));
    }


    @Test
    @DisplayName("Тестирование исключений в функции нормализации аргумента")
    void testNormExceptions() {
        assertThrows(ArithmeticException.class, () -> Tg.normalize(HALF_PI.multiply(BigDecimal.valueOf(3)).subtract(new BigDecimal("1E-12"))));
        assertThrows(ArithmeticException.class, () -> Tg.normalize(HALF_PI.multiply(BigDecimal.valueOf(-5)).add(new BigDecimal("1E-12"))));
    }


}


