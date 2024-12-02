import java.util.ArrayList; // import the ArrayList class
import java.util.List;


public class Calculator {

    private String expression = "";
    private List<List<String>> orderOfOperations = List.of(
            List.of("^"),
            List.of("*", "/"),
            List.of("+", "-")
    );
    private ArrayList<String> expressionList = new ArrayList<String>();

    public Calculator(String expression) throws Exception{
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public List<List<String>> getOrderOfOperations() {
        return orderOfOperations;
    }

    public void setOrderOfOperations(List<List<String>> newListOfOperators) {
        this.orderOfOperations = newListOfOperators;
    }

    public ArrayList<String> getExpressionList() {
        return expressionList;
    }


    public String finalMathSolver(ArrayList<String> expressionList){

        for (List<String> innerOperatorsList : getOrderOfOperations()) {
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
}
