import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsForArifmetics {
    @Test
    public void testForAdd() {
        double a = 5.01;
        double b = 3.5;
        double result = a + b;
        Assertions.assertEquals(result, Arifmetics.addition(a, b), 0);
    }

    @Test
    public void testForSub() {
        double a = 5.02;
        double b = 2.01;
        double result = a - b;
        Assertions.assertEquals(result, Arifmetics.subtraction(a, b), 0);
    }

    @Test
    public void testForMult() {
        double a = 5.03;
        double b = 3.23;
        double result = a * b;
        Assertions.assertEquals(result, Arifmetics.multiplication(a, b), 0);
    }

    @Test
    public void testForDiv() {
        double a = 5.25;
        double b = 3.25;
        double result = a / b;
        Assertions.assertEquals(result, Arifmetics.division(a, b), 0);
    }

    @Test
    public void testForLog() {
        double a = 5;
        double result = Math.log(a);
        Assertions.assertEquals(result, Arifmetics.logarithm(a), 0);
    }

    @Test
    public void testForPow() {
        double a = 5;
        double b = 6;
        double result = Math.pow(a, b);
        Assertions.assertEquals(result, Arifmetics.power(a, b), 0);
    }

    @Test
    public void testForSqrt() {
        double a = 30;
        double result = Math.sqrt(a);
        Assertions.assertEquals(result, Arifmetics.squareRoot(a), 0);
    }

    @Test
    public void testForSin() {
        double a = 5;
        double result = Math.sin(a);
        Assertions.assertEquals(result, Arifmetics.sinus(a), 0);
    }

    @Test
    public void testForCos() {
        double a = 4;
        double result = Math.cos(a);
        Assertions.assertEquals(result, Arifmetics.cosinus(a), 0);
    }

    @Test
    public void testForCalc() throws Exception {
        String input = "sin + - 1 2 1";
        double res = Calculator.calculate(input);
        Assertions.assertEquals(0, res, 0);
    }

    @Test
    public void testForCalc2() throws Exception {
        String input = "sqrt + 2 7";
        double res = Calculator.calculate(input);
        Assertions.assertEquals(3, res, 0);
    }

    @Test
    public void testForCalc3() throws Exception {
        String input = "/ 12 + + 1 2 3";
        double res = Calculator.calculate(input);
        Assertions.assertEquals(2, res, 0.0001);
    }

    @Test
    public void testForExceptionOperations() throws Exception {
        String input = "err + 2 7";
        Throwable thrown = assertThrows(Exception.class, () -> {
            double res = Calculator.calculate(input);
        });
        assertEquals(thrown.getMessage(), "Invalid function");
    }

    @Test
    public void testForExceptionStackNotEmptyOnFinish() throws Exception {
        String input = "+ 2 3 5";
        Throwable thrown = assertThrows(Exception.class, () -> {
            double res = Calculator.calculate(input);
        });
        assertEquals(thrown.getMessage(), "Invalid number of arguments");
    }

    @Test
    public void testForExceptionLessArgs() throws Exception {
        String input = "+ - + - 5";
        Throwable thrown = assertThrows(Exception.class, () -> {
            double res = Calculator.calculate(input);
        });
        Assertions.assertEquals("not enough numbers", thrown.getMessage());
    }

}
