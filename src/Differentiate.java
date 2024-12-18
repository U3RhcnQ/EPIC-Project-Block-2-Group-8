import java.util.ArrayList;
import java.util.List;

public class Differentiate extends Calculator{
    private static String inputEquation;
    List<String> orderOfOperations = List.of("*","/","+","-","(",")"); // List of the operands that include round brackets to make the parser treat these differently

    // Constructor of the Differentiate object that takes in the user input and gives it's value to inputEquation
    public Differentiate(String expression) {
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
        // differentiate the elements and add them to the string equation
        // add the operands to the string equation
        String equation = "";
        for (int index = 0; index<elementList.size(); index++){
            String item = elementList.get(index);
            if (orderOfOperations.contains(item)) {
                equation = equation + item;
                // if a bracket is found throw an exception to tell the user off
                if (item.equals("(") || item.equals(")")){
                    throw new PrettyException("It looks like you hava a bracket that isn't supposed to be there",inputEquation,index);
                }
            } else {
                equation = equation + differentiate(item);
            }
        }
        System.out.println();
        System.out.printf("This leaves us with the equation: %s%n",equation);

        // check is the last character in equation an operand if so remove it
        char lastChar = equation.charAt(equation.length()-1);
        if (!Character.isDigit(lastChar) && !String.valueOf(lastChar).equals("x")){
            System.out.println();
            System.out.println("It looks like there is an extra operand");
            equation = equation.substring(0,equation.length()-1);
            System.out.printf("After removing this operand we are left with: %s%n",equation);
        }
        return equation;
    }

    private String differentiate(String expression){
        // if the element has a power then multiply the coefficient of the x by the power and reduce the power by 1
        if (expression.contains("^")) {
            int index = expression.indexOf("^");
            int number = Integer.parseInt(expression.substring(0, index-1));
            int power = Integer.parseInt(expression.substring(index+1));
            number *= power;
            power--;
            if (power == 1) {
                expression = String.valueOf(number) + "x";
            }else{
                expression = String.valueOf(number) + expression.substring(index-1, index+1) + String.valueOf(power);
            }
        // if the element has just an x with no power then remove the x
        }else if (expression.contains("x")){
            expression = expression.substring(0,expression.length()-1);
        }
        return expression; // return the new element
    }
}
