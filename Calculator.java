import java.util.ArrayList; // import the ArrayList class


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

    public String finalMathSolver(ArrayList<String> expressionList){
        for(String Operator: getOrderOfOperations()) {
            for (int i = 0; i < expressionList.size(); i++) {
                if (expressionList.get(i).equals(Operator)){

                    double a = Double.parseDouble(expressionList.get(i-1));
                    double b = Double.parseDouble(expressionList.get(i+1));

                    switch (expressionList.get(i)) {
                        case "^" -> {
                            expressionList.set(i, String.valueOf(Math.pow(a, b)));
                        }
                        case "*" -> {
                            expressionList.set(i, String.valueOf(a * b));
                        }
                        case "/" -> {
                            expressionList.set(i, String.valueOf(a / b));
                        }
                        case "+" -> {
                            expressionList.set(i, String.valueOf(a + b));
                        }
                        case "-" -> {
                            expressionList.set(i, String.valueOf(a - b));
                        }
                    }

                    // Special order if you switch it will break horribly :(
                    expressionList.remove(i + 1);
                    expressionList.remove(i - 1);

                    i = 0; //Don't ask I am already ashamed
                    System.out.println("List: " + expressionList);
                }
            }
        }
        return expressionList.getFirst();
    }

    public static void main(String[] args) throws Exception {

        arithmeticCalculator Calculator1 = new arithmeticCalculator("345.051505+2*-(4--15+(23+3)+1)+5^7");
        Calculator1.parse();
        //Calculator1.mathSolver();

    }
}
