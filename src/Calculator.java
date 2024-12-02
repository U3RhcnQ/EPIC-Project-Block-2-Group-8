import java.util.ArrayList; // import the ArrayList class
import java.util.List;


public class Calculator {

    private String expression = "";
    private String[] orderOfOperations = {"^", "*", "/", "+", "-"};
    private ArrayList<String> expressionList = new ArrayList<String>();

    public Calculator(String expression) throws Exception{
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public String[] getOrderOfOperations() {
        return orderOfOperations;
    }

    public void setOrderOfOperations(String[] newListOfOperators) {
        this.orderOfOperations = newListOfOperators;
    }

    public ArrayList<String> getExpressionList() {
        return expressionList;
    }

    public String finalMathSolverold(ArrayList<String> expressionList) {
        for (String operator : orderOfOperations) {
            int i = 0; // Start at the beginning of the list for each operator
            while (i < expressionList.size()) {
                if (expressionList.get(i).equals(operator)) {
                    // Parse operands
                    double a = Double.parseDouble(expressionList.get(i - 1));
                    double b = Double.parseDouble(expressionList.get(i + 1));
                    double result = 0;

                    // Perform the operation
                    switch (operator) {
                        case "^" -> result = Math.pow(a, b);
                        case "*" -> result = a * b;
                        case "/" -> result = a / b;
                        case "+" -> result = a + b;
                        case "-" -> result = a - b;
                    }

                    // Replace the operator and operands with the result
                    expressionList.set(i, String.valueOf(result));
                    expressionList.remove(i + 1); // Remove the operand after the operator
                    expressionList.remove(i - 1); // Remove the operand before the operator

                    // Adjust index to recheck the current position
                    i = 0;
                    System.out.println("List: " + expressionList);
                } else {
                    i++; // Move to the next element
                }
            }
        }

        // Ensure there is only one element left in the list, the final result
        return expressionList.get(0);
    }

    public String finalMathSolver(ArrayList<String> expressionList){


        List<List<String>> Operators = List.of(
                List.of("^"),
                List.of("*", "/"),
                List.of("+", "-")
        );

        for (List<String> innerOperatorsList : Operators) {
            for (int i = 0; i < expressionList.size(); i++) {
                if (innerOperatorsList.contains(expressionList.get(i))) {
                    double a = Double.parseDouble(expressionList.get(i - 1));
                    double b = Double.parseDouble(expressionList.get(i + 1));
                    double result = 0;

                    // Perform the operation
                    switch (expressionList.get(i)) {
                        case "^" -> result = Math.pow(a, b);
                        case "*" -> result = a * b;
                        case "/" -> result = a / b;
                        case "+" -> result = a + b;
                        case "-" -> result = a - b;
                    }

                    // Replace the operator and operands with the result
                    expressionList.set(i, String.valueOf(result));
                    expressionList.remove(i + 1); // Remove the operand after the operator
                    expressionList.remove(i - 1); // Remove the operand before the operator

                    i -= 1; // Step back to account for the removed elements and continue inline evaluation
                    System.out.println("List: " + expressionList);
                }
            }
        }
        // Ensure there is only one element left in the list, the final result
        return expressionList.getFirst();
    }

    public static void main(String[] args) throws Exception {

        arithmeticCalculator Calculator1 = new arithmeticCalculator("345.051505+2*-(4--15+(23+3)+1)+5^7");
        Calculator1.parse();
        //Calculator1.mathSolver();

    }
}
