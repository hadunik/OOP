import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestForTask {
    private Integer[] array;
    private Integer[] arrayWithNotSimple;
    private long started;
    @BeforeAll
    public void startPoint(){
        array = new Resheto(100000).getter();
        arrayWithNotSimple = Arrays.copyOf(array, array.length + 1);
        arrayWithNotSimple[array.length] = 4;
    }

    @BeforeEach
    public void startTime() {
        started = System.nanoTime();
    }

    @AfterEach
    public void finishTime() {
        System.out.println((System.nanoTime() - started) / 1000000);
        started = 0L;
    }

    @Test
    public void seqTest() {
        assertTrue(Simple.simpleChecker(arrayWithNotSimple));
    }

    @Test
    public void seqTest2() {
        assertFalse(Simple.simpleChecker(array));
    }

    @Test
    public void parallelsTest() {
        assertTrue(ParallelsStream.checkerParallels(arrayWithNotSimple));
    }

    @Test
    public void parallelsTest2() {
        assertFalse(ParallelsStream.checkerParallels(array));
    }

    @Test
    public void threadsTest() throws Exception {
        assertTrue(Threads.checkerOnThreads(arrayWithNotSimple, 15));

    }

    @Test
    public void threadsTest2() throws Exception {
        assertFalse(Threads.checkerOnThreads(array, 4));
    }
}
