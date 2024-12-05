import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class petrtestingclass {
    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);
        List<String> orderOfOperations = List.of("x", "y", "z", "=");

        String input = scanner.nextLine();
        input = input.replace("+", "");

        ArrayList<String> expression = InputParser.parseFromString(input, orderOfOperations, "xyz=+-*/^=");
        double a = 0,b = 0,c = 0,d = 0;

        for (int i = 0; i < expression.size(); i++) {

            String item = expression.get(i);

            if (item.equals("x")) {
                a = Double.parseDouble(expression.get(i - 1));
            } else if (item.equals("y")) {
                b = Double.parseDouble(expression.get(i - 1));
            } else if (item.equals("z")) {
                c = Double.parseDouble(expression.get(i - 1));
            } else if (item.equals("=")) {
                d = Double.parseDouble(expression.get(i + 1));
            }
        }

        System.out.printf("a:%.2f b:%.2f c:%.2f d:%.2f", a, b, c, d);

    }
}
