import java.util.ArrayList;
import java.util.List;

public class Integration extends Calculator {
    private static String inputEquation;
    List<String> orderOfOperations = List.of("*", "/", "+", "-", "(", ")");  // List of the operands that include round brackets to make the parser treat these differently

    // Constructor of the Integration object that takes in the user input and gives it's value to inputEquation
    public Integration(String expression) {
        super(expression);
        this.inputEquation = expression;
    }
    // Override the abstract method parse() from Calculator.java
    @Override
    public void parse() throws Exception {
        setExpressionList(InputParser.parseFromString(inputEquation, orderOfOperations, "x+-*/^=")); // Method from Calculator that sets the Arraylist ExpressionList
        // parseFromString is a method from InputParser that takes a string and converts to an ArrayList of elements and operands
    }

    // Override the abstract method solve() from Calculator.java
    @Override
    public String solve() throws Exception {
        parse();
        ArrayList<String> elementList = getExpressionList(); // create a variable to store the Expression List

        // Iterate through the arraylist
        // integrate the elements and add them to the string equation
        // add the operands to the string equation
        String equation = "";
        for (int index = 0; index < elementList.size(); index++) {
            String item = elementList.get(index);
            if (orderOfOperations.contains(item)) {
                equation = equation + item;
                // if a bracket is found throw an exception to tell the user off
                if (item.equals("(") || item.equals(")")) {
                    throw new PrettyException("It looks like you hava a bracket that isn't supposed to be there", inputEquation, index);
                }
            } else {
                equation = equation + integrate(item);
            }
        }
        System.out.println();
        System.out.printf("This leaves us with the equation: %s%n", equation);

        return equation;
    }

    private String integrate(String expression){
        String resultString = "";
        // if the element has a power then increase the power by 1 and divide the coefficient of the x by the power
        if (expression.contains("^")) {
            int index = expression.indexOf("^");
            int number = Integer.parseInt(expression.substring(0,index-1));
            int power = Integer.parseInt(expression.substring(index+1));
            power++;
            // if it divides evenly use an int if not use a double and round to 2 decimal places
            if (number/power % 1 == 0){
                int result = number / power;
                resultString+=result;
            }else {
                double result = (double) number / power;
                resultString = String.format("%.2f",result);
            }
            // reconstruct the integrated expression
            expression = resultString + expression.substring(index-1, index+1) + String.valueOf(power);
        } else if (expression.contains("x")) { // if expression has x but no power
            // get the coefficient and use the power as 2
            int number = Integer.parseInt(expression.substring(0,expression.length()-1));
            // if it divides evenly use an int if not use a double and round to 2 decimal places
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
            // add x to the expression
            expression+="x";

        }
        return expression;
    }
}