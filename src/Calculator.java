import java.util.ArrayList; // import the ArrayList class
import java.util.List;

public abstract class Calculator {

    // Instance Variables:

    // We initialise the order of operations to the standard operations order by default but let us change it later
    private List<List<String>> orderOfOperations = List.of(
            List.of("^"),
            List.of("*", "/"),
            List.of("+", "-")
    );

    // This is a simple order of operations use only for input parser and not for accumulations
    // When we update main order of operations we update this as well - check set method
    private List<String> orderOfOperationsSimple = List.of("^","*","/","+","-");
    private final String expression;
    private ArrayList<String> expressionList = new ArrayList<>();

    // Methods:

    protected String getExpression() {
        return expression;
    }

    protected List<List<String>> getOrderOfOperations() {
        return this.orderOfOperations;
    }

    protected List<String> getSimpleOrderOfOperations() {
        return this.orderOfOperationsSimple;
    }

    protected ArrayList<String> getExpressionList() {return this.expressionList;}

    protected void setExpressionList(ArrayList<String> expressionList) {this.expressionList = expressionList;}

    protected void setOrderOfOperations(List<List<String>> newListOfOperators) {

        // if we update the order of operations we need to update the simple list as well
        // We do this by building out a new list from all the inner lists
        ArrayList<String> operators = new ArrayList<>();
        for (List<String> innerOperatorsList : newListOfOperators) {
            operators.addAll(innerOperatorsList);
        }

        this.orderOfOperationsSimple = operators;
        this.orderOfOperations = newListOfOperators;
    }

    protected Calculator(String expression){
        this.expression = expression;
    }

    abstract String solve() throws Exception;

    abstract void parse() throws Exception;

    // Main Maths Solver for the calculator
    protected String finalMathSolver(ArrayList<String> expressionList){

        // we loop for every level of priority of operations so ^, */ and then +-
        for (List<String> innerOperatorsList : getOrderOfOperations()) {
            // loop through the expression list
            for (int i = 0; i < expressionList.size(); i++) {
                // Check if the item is an operator
                if (innerOperatorsList.contains(expressionList.get(i))) {

                    // Get number to the left and right of the operator
                    double a = Double.parseDouble(expressionList.get(i - 1));
                    double b = Double.parseDouble(expressionList.get(i + 1));
                    double result = 0;

                    // Perform the operation
                    switch (expressionList.get(i)) {
                        case "^" -> result = Math.pow(a, b);
                        case "*" -> result = a * b;
                        case "/" -> {
                            // Catch divide by 0 errors here
                            if (b == 0){
                               throw new ArithmeticException("\n\nOh no it looks like we ran into an issue with the expression :( \nLooks like we have a Divide by 0 and we can't continue\n");
                            } else {
                                result = a / b;
                            }
                        }
                        case "+" -> result = a + b;
                        case "-" -> result = a - b;
                    }

                    System.out.printf("\nAfter applying the (%s) operation on %.2f and %.2f we get the following result: %.2f",expressionList.get(i), a, b, result);

                    // Replace the operator and operands with the result
                    expressionList.set(i, String.valueOf(result));
                    expressionList.remove(i + 1); // Remove the operand after the operator
                    expressionList.remove(i - 1); // Remove the operand before the operator

                    i -= 1; // Step back to account for the removed elements and continue inline evaluation

                    System.out.printf("\nThe remaining list of operations: %s%n", expressionList);
                }
            }
        }

        // Ensure there is only one element left in the list, the final result
        return expressionList.getFirst();
    }
}
