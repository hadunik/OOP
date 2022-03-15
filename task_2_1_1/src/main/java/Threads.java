import java.util.Arrays;

public class Threads {
    public static boolean checkerOnThreads(Integer[] array, int numOfThreads) throws Exception {
        boolean hasNotPrime = false;
        Checker[] arrayOfThreads = new Checker[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            arrayOfThreads[i] = new Checker(i, numOfThreads, array);
            arrayOfThreads[i].start();
        }
        for (int i = 0; i < numOfThreads; i++) {
            arrayOfThreads[i].join();
            hasNotPrime |= arrayOfThreads[i].isHasNotPrime();
        }
        return hasNotPrime;
    }
}

class Checker extends Thread {
    int ID;
    int cntOfThreads;
    Integer[] array;
    boolean hasNotPrime = false;

    Checker(int id, int cntThreads, Integer[] arr) {
        ID = id;
        cntOfThreads = cntThreads;
        array = arr;
    }

    @Override
    public void run() {
        for (int i = ID - 1; i < array.length; i += cntOfThreads) {
            if(CheckerOnPrime.isNotPrime(array[i])) {
                hasNotPrime = true;
            }
        }
    }

    public boolean isHasNotPrime() {
        return hasNotPrime;
    }
}