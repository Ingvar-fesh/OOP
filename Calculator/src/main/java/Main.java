import Calculator.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        Calculator p = new Calculator();
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("Prefix expression: ");
            String str = in.nextLine();
            if (str.isEmpty())
                break;
            System.out.printf("Answer: %f \n", p.calculate(str));
        }
        in.close();
    }
}
