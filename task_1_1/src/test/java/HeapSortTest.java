import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapSortTest {
    @Test
    public void commonTest() {
        int[] testArr = {1, 6, 3, 5, 8};
        HeapSort.sort(testArr);
        int[] ansArr = {1, 3, 5, 6, 8};
        Assertions.assertArrayEquals(ansArr, testArr);
    }

    @Test
    public void emptyTest() {
        int[] testArr = {};
        HeapSort.sort(testArr);
        int[] ansArr = {};
        Assertions.assertArrayEquals(ansArr, testArr);
    }

    @Test
    public void criticalTest() {
        int[] testArr = {Integer.MAX_VALUE, 8, -1, 0, Integer.MIN_VALUE};
        HeapSort.sort(testArr);
        int[] ansArr = {Integer.MIN_VALUE, -1, 0, 8, Integer.MAX_VALUE};
        Assertions.assertArrayEquals(ansArr, testArr);
    }

    @Test
    public void noPositiveTest() {
        int[] testArr = {-6, -7, -3, -1};
        HeapSort.sort(testArr);
        int[] ansArr = {-7, -6, -3, -1};
        Assertions.assertArrayEquals(ansArr, testArr);
    }

}
