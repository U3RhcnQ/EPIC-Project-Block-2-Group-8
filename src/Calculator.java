import java.util.ArrayList; // import the ArrayList class
import java.util.List;


public class Calculator {

    // We initialise the order of operations to the standard operations order by default but let us change it later
    private List<List<String>> orderOfOperations = List.of(
            List.of("^"),
            List.of("*", "/"),
            List.of("+", "-")
    );
    private List<String> orderOfOperationsSimple = List.of("^","*","/","+","-");

    private String expression = "";
    private ArrayList<String> expressionList = new ArrayList<String>();


    public String getExpression() {
        return expression;
    }

    public List<List<String>> getOrderOfOperations() {
        return this.orderOfOperations;
    }

    public List<String> getSimpleOrderOfOperations() {
        return this.orderOfOperationsSimple;
    }

    public ArrayList<String> getExpressionList() {return this.expressionList;}

    public void setExpressionList(ArrayList<String> expressionList) {this.expressionList = expressionList;}

    public void setOrderOfOperations(List<List<String>> newListOfOperators) {
        this.orderOfOperations = newListOfOperators;

        // if we update the order of operations we need to update the simple list as well
        // We do this by building out a new list from all the inner lists
        ArrayList<String> operators = new ArrayList<>();
        for (List<String> innerOperatorsList : newListOfOperators) {
            operators.addAll(innerOperatorsList);
        }

        this.orderOfOperationsSimple = operators;
    }

    public Calculator(String expression){
        this.expression = expression;
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

                    System.out.printf("\nAfter applying the (%s) operation on %.2f and %.2f we get the following result: %.2f",expressionList.get(i), a, b, result);

                    // Replace the operator and operands with the result
                    expressionList.set(i, String.valueOf(result));
                    expressionList.remove(i + 1); // Remove the operand after the operator
                    expressionList.remove(i - 1); // Remove the operand before the operator

                    i -= 1; // Step back to account for the removed elements and continue inline evaluation

                    System.out.println("\nThe remaining list of operations: " + expressionList);
                }
            }
        }

        // Ensure there is only one element left in the list, the final result
        return expressionList.getFirst();
    }
}
