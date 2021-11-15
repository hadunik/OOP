import java.util.Objects;
import java.util.Scanner;

public class Program extends Calculator{

    public void main(String[] args) throws Exception {
        System.out.print("Type the expression: ");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        while(!(Objects.equals(str, "quit"))){
            System.out.println(calculate(str));
            str = in.nextLine();
        }
        in.close();
    }

}