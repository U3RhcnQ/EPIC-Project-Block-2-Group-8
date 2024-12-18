import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Simultaneous Equation Calculator
public class SECalculator extends Calculator{

    private Scanner scanner = new Scanner(System.in);

    //Initialised coefficients for each expression
    private double a1 = 0, b1 = 0, c1 = 0, d1 = 0;
    private double a2 = 0, b2 = 0, c2 = 0, d2 = 0;
    private double a3 = 0, b3 = 0, c3 = 0, d3 = 0;

    private int expressionNumber; //how many variables in the equation

    //getting expression from calculator, initialises it to nothing
    public SECalculator() {
        super("");
    }

    //sets the expression number, to decide how the variables will be calculator
    private void setExpressionNumber(int expressionNumber){
        this.expressionNumber = expressionNumber;
    }

    //gets the expression number
    private int getExpressionNumber(){ return this.expressionNumber; }

    //overrides the parse from calculator
    //parses the expression
    @Override
    public void parse() throws Exception {

        String input = getExpression();
        List<String> orderOfOperations = List.of("x", "y", "z", "="); //operators

        //Parses input and checks for the special characters
        ArrayList<String> expression = InputParser.parseFromString(input, orderOfOperations, "xyz=+-*/^=");
        double a = 0, b = 0, c = 0, d = 0;

        try {
            //iterates through the expression to find the operators(variables)
            for (int i = 0; i < expression.size(); i++) {
                String item = expression.get(i); //gets the current element in the list
                if (item.equals("x")) {
                    //checks if there is a character or number before the variable
                    String coefficient = (i > 0) ? expression.get(i - 1) : "";//if no number is there, then it sets it to an empty string

                    //if no number or operator is there
                    if (coefficient.isEmpty()) { // eg x + ... = ...
                        a = 1;
                    } else if(coefficient.equals("+")) { //if there is a + before the variable eg ... + x = ...
                        a = 1;
                    }else if(coefficient.equals("-")) { //if there is a - before the variable eg ... - x = ...
                        a = -1;
                    }else {
                        try {
                            //get teh coefficient
                            a = Double.parseDouble(coefficient);
                        } catch (NumberFormatException e) {
                            throw new Exception("Invalid coefficient for x.");
                        }
                    }
                } else if (item.equals("y")) {
                    String coefficient = (i > 0) ? expression.get(i - 1) : "";

                    if (coefficient.isEmpty()) {
                        b = 1;
                    } else if(coefficient.equals("+")) {
                        b = 1;
                    }else if(coefficient.equals("-")) {
                        b = -1;
                    }else {
                        try {
                            b = Double.parseDouble(coefficient);
                        } catch (NumberFormatException e) {
                            throw new Exception("Invalid coefficient for y.");
                        }
                    }
                } else if (item.equals("z")) {
                    String coefficient = (i > 0) ? expression.get(i - 1) : "";

                    if (coefficient.isEmpty()) {
                        c = 1;
                    } else if(coefficient.equals("+")) {
                        c = 1;
                    }else if(coefficient.equals("-")) {
                        c = -1;
                    }else {
                        try {
                            c = Double.parseDouble(coefficient);
                        } catch (NumberFormatException e) {
                            throw new Exception("Invalid coefficient for z.");
                        }
                    }
                } else if (item.equals("=")) {
                    d = Double.parseDouble(expression.get(i + 1));
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }



        //Assigning the coefficient to the variable
        switch (getExpressionNumber()) {
            case 1: // 1 Variables
                a1 = a;
                b1 = b;
                c1 = c;
                d1 = d;
                break;
            case 2: // 2 Variables
                a2 = a;
                b2 = b;
                c2 = c;
                d2 = d;
                break;
            case 3: // 3 Variables
                a3 = a;
                b3 = b;
                c3 = c;
                d3 = d;
                break;
        }
    }

    // Solve method
    @Override
    String solve() throws Exception{

        int k = 0; //number of variables
        String response = ""; //initialise output of method(default)

        System.out.println("\nExpression must be in the form of:");
        System.out.println("For 1 variable: ax = d");
        System.out.println("For 2 variables: ax + by = d");
        System.out.println("For 3 variables: ax + by + cz = d");

        System.out.print("\nHow many variables 1,2 or 3: ");

        try {
            //gets how many variables
            k = scanner.nextInt();
            setExpressionNumber(k);
        } catch (Exception e) {
            throw new Exception("Invalid input must be a number please try again");
        }
        scanner.nextLine();

        //checks if number of variables is between 1 and 3
        if (k < 1 || k > 3) {
            throw new Exception("Number of variables must be between 1 and 3.");
        }

        //captures the input expressions
        for (int i = 1; i <= k; i++) {
            System.out.print("\nExpression " + i + ": ");
            String expression = scanner.nextLine();
            setExpression(expression);
            setExpressionNumber(i);
            parse();
        }


        if (k == 1) {// for expressions with 1 variable
            if (a1 < 1e-9) { // Treat near-zero values as zero to avoid floating-point precision errors and instability.
                System.out.println("No solution or infinite solutions exist.");
            } else {
                double x = d1 / a1;
                response = "Solution: \nx = " + x;
            }
        } else if (k == 2) { // for expressions with 2 variables
            double determinant = (a1 * b2) - (a2 * b1);//finds determinant

            if (determinant < 1e-9 && determinant > -1e-9) { // treat near-zero values as zero to avoid floating-point precision errors
                throw new Exception("No unique solution exists (either no solution or infinitely many solutions).");
            } else {
                double x = ((d1 * b2) + (d2 * -b1)) / determinant;
                double y = ((a1 * d2) + (-a2 * d1)) / determinant;
                response = "Solution: \nx = " + x + "\ny = " + y;
            }
        } else if (k == 3) { // for expressions with 3 variables


            double L1 = a2 / a1;
            double newB2 = b2 - (L1 * b1);
            double newC2 = c2 - (L1 * c1);

            double L2 = a3 / a1;
            double newB3 = b3 - (L2 * b1);
            double newC3 = c3 - (L2 * c1);

            if (Math.abs(newB2) < 1e-9) { // treat near-zero values as zero to avoid floating-point precision errors
                throw new Exception("Division by zero encountered during Gaussian elimination.");
            }

            double L3 = newB3 / newB2;
            double finalC3 = newC3 - (L3 * newC2);

            if (Math.abs(finalC3) < 1e-9) { // treat near-zero values as zero to avoid floating-point precision errors
                throw new Exception("System cannot be solved: determinant is zero.");
            }

            double g1 = d1;
            double g2 = d2 - (L1 * g1);
            double g3 = d3 - ((L2 * g1) + (L3 * g2));

            double z = g3 / finalC3;
            double y = (g2 - (newC2 * z)) / newB2;
            double x = (g1 - ((c1 * z) + (b1 * y))) / a1;

            response = "Solution: \nx = " + x + "\ny = " + y + "\nz = " + z;

        } else {
            throw new Exception("oops something has gone horribly wrong :(");
        }

        return response;
    }

    public static void main(String[] args) throws Exception{

        // Created an instance of SECalculator
        SECalculator SECalculator = new SECalculator();
        System.out.println(SECalculator.solve());

    }
}