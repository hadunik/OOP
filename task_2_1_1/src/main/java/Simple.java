public class Simple {

    public static boolean simpleChecker(Integer[] arr) {
        for (Integer i : arr) {
            if (!CheckerOnPrime.isPrime(i)) {
                return true;
            }
        }

        return false;
    }
}
