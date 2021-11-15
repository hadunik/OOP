public class Arifmetics {
    public static double addition(double a, double b){
        return a + b;
    }

    public static double subtraction(double a, double b){
        return a - b;
    }

    public static double multiplication(double a, double b){
        return a * b;
    }

    public static double division(double a, double b) {
        return a / b;
    }

    public static double logarithm(double num) {
        return Math.log(num);
    }

    public static double power(double basis, double exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= basis;
        }
        return result;
    }

    public static double squareRoot(double d) {
        return Math.sqrt(d);
    }

    public static double sinus(double a) {
        return Math.sin(a);
    }

    public static double cosinus(double a){
        return Math.cos(a);
    }
}
