import java.util.ArrayList;
import java.util.List;

public class Integration extends Calculator {
    private static String inputEquation;
    List<String> orderOfOperations = List.of("*", "/", "+", "-", "(", ")");

    public Integration(String expression) {
        super(expression);
        this.inputEquation = expression;
    }

    public ArrayList<String> parse(String input) throws Exception {
        ArrayList<String> expression = InputParser.parseFromString(input, orderOfOperations, "x+-*/^=");
        return expression;
    }

    public void solve() throws Exception {
        ArrayList<String> elementList = parse(inputEquation);

        String equation = "";
        for (int index = 0; index < elementList.size(); index++) {
            String item = elementList.get(index);
            if (orderOfOperations.contains(item)) {
                equation = equation + item;
                if (item.equals("(") || item.equals(")")) {
                    throw new PrettyException("It looks like you hava a bracket that isn't supposed to be there", inputEquation, index);
                }
            } else {
                equation = equation + integrate(item);
            }
        }
        System.out.println();
        System.out.printf("This leaves us with the equation: %s%n", equation);

        char lastChar = equation.charAt(equation.length() - 1);
        if (!Character.isDigit(lastChar) && !String.valueOf(lastChar).equals("x")) {
            System.out.println();
            System.out.println("It looks like there is an extra operand");
            equation = equation.substring(0, equation.length() - 1);
            System.out.printf("After removing this operand we are left with: %s%n", equation);
        }
    }

    private String integrate(String expression){
        String resultString = "";
        if (expression.contains("^")) {
            int index = expression.indexOf("^");
            int number = Integer.parseInt(expression.substring(0,index-1));
            int power = Integer.parseInt(expression.substring(index+1));
            power++;
            if (number/power % 1 == 0){
                int result = number / power;
                resultString+=result;
            }else {
                double result = (double) number / power;
                resultString = String.format("%.2f",result);
            }
            expression = resultString + expression.substring(index-1, index+1) + String.valueOf(power);
        } else if (expression.contains("x")) {
            int number = Integer.parseInt(expression.substring(0,expression.length()-1));
            if (number%2 == 0){
                number/=2;
                resultString += number;
            }else {
                double result = (double) number / 2;
                resultString = String.format("%.2f",result);
            }
            resultString+="x^2";
            expression = resultString;
        }else {
            expression+="x";

        }
        return expression;
    }
}