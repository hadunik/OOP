import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator extends Arifmetics {
    private static boolean isDigit(String str) throws NumberFormatException {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static double protectedPop(Deque stack) throws Exception {
        try {
            return (double) stack.pop();
        } catch (Exception e){
            throw new Exception("not enough numbers");
        }
    }

    public static double calculate(String str) throws Exception{
        String[] input = str.split(" ");

        Deque<Double> stack = new ArrayDeque<>();

        double a;
        double b;

        for (int i = input.length - 1; i >= 0; i--) {
            if (isDigit(input[i])) {
                stack.push(Double.parseDouble(input[i]));
            } else {
                switch (input[i]) {
                    case "+" -> {
                        a = protectedPop(stack);
                        b = protectedPop(stack);
                        stack.push(Arifmetics.addition(a, b));
                    }
                    case "-" -> {
                        a = protectedPop(stack);
                        b = protectedPop(stack);
                        stack.push(Arifmetics.subtraction(a,b));
                    }
                    case "*" -> {
                        a = protectedPop(stack);
                        b = protectedPop(stack);
                        stack.push(Arifmetics.multiplication(a,b));
                    }
                    case "/" -> {
                        a = protectedPop(stack);
                        b = protectedPop(stack);
                        stack.push(Arifmetics.division(a,b));
                    }
                    case "log" -> {
                        a = protectedPop(stack);
                        stack.push(Arifmetics.logarithm(a));
                    }
                    case "pow" -> {
                        a = protectedPop(stack);
                        b = protectedPop(stack);
                        stack.push(Arifmetics.power(a,b));
                    }
                    case "sqrt" -> {
                        a = protectedPop(stack);
                        stack.push(Arifmetics.squareRoot(a));
                    }
                    case "sin" -> {
                        a = protectedPop(stack);
                        stack.push(Arifmetics.sinus(a));
                    }case "cos" -> {
                        a = protectedPop(stack);
                        stack.push(Arifmetics.cosinus(a));
                    }
                    default -> {
                        System.out.println("There is no such function");
                        throw new Exception("Invalid function");
                    }
                }
            }
        }

        double ans = stack.pop();

        if(stack.isEmpty()) {
            return ans;
        }
        else{
            throw new Exception("Invalid number of arguments");
        }
    }
}
