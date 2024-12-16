import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Simultaneous Equation Calculator
public class SECalculator extends Calculator{

    private static Scanner scanner = new Scanner(System.in);

    //Coefficients for each expression
    private double a1 = 0, b1 = 0, c1 = 0, d1 = 0;
    private double a2 = 0, b2 = 0, c2 = 0, d2 = 0;
    private double a3 = 0, b3 = 0, c3 = 0, d3 = 0;

    public SECalculator() {
        super("");
    }

    public void parse(String input, int expressionNumber) throws Exception {
        List<String> orderOfOperations = List.of("x", "y", "z", "=");
        input = input.replace("+", ""); //Gets rid of +'s

        //Parses input and checks for the special characters
        ArrayList<String> expression = InputParser.parseFromString(input, orderOfOperations, "xyz=+-*/^=");
        double a = 0, b = 0, c = 0, d = 0;

        try {
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
        } catch (NumberFormatException e) {
            throw new Exception("Invalid numeric value detected in the input.");
        }

        //Assigning the coefficient to the variable
        switch (expressionNumber) {
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
    public void solve() {
        try {
            System.out.println("\nExpression must be in the form of:");
            System.out.println("For 1 variable: ax = d");
            System.out.println("For 2 variables: ax + by = d");
            System.out.println("For 3 variables: ax + by + cz = d");

            System.out.print("\nHow many variables (1 to 3): ");
            int k = scanner.nextInt();
            scanner.nextLine();

            if (k < 1 || k > 3) {
                throw new Exception("Number of variables must be between 1 and 3.");
            }

            for (int i = 1; i <= k; i++) {
                System.out.print("\nExpression " + i + ": ");
                String expression = scanner.nextLine();
                setExpression(expression);
                parse(expression, i);
            }

            if (k == 1) {
                if (a1 == 0) {
                    System.out.println("No solution or infinite solutions exist.");
                } else {
                    double x = d1 / a1;
                    System.out.println("Solution: \nx = " + x);
                }
            } else if (k == 2) {
                double determinant = (a1 * b2) - (a2 * b1);
                if (determinant == 0) {
                    System.out.println("No unique solution exists (either no solution or infinitely many solutions).");
                } else {
                    double x = (d1 * b2 - d2 * b1) / determinant;
                    double y = (a1 * d2 - a2 * d1) / determinant;
                    System.out.println("Solution: \nx = " + x + "\ny = " + y);
                }
            } else if (k == 3) {
                double L1 = a2 / a1;
                double newB2 = b2 - (L1 * b1);
                double newC2 = c2 - (L1 * c1);

                double L2 = a3 / a1;
                double newB3 = b3 - (L2 * b1);
                double newC3 = c3 - (L2 * c1);

                if (Math.abs(newB2) < 1e-9) {
                    throw new Exception("Division by zero encountered during Gaussian elimination.");
                }

                double L3 = newB3 / newB2;
                double finalC3 = newC3 - (L3 * newC2);

                if (Math.abs(finalC3) < 1e-9) {
                    throw new Exception("System cannot be solved: determinant is zero.");
                }

                double g1 = d1;
                double g2 = d2 - (L1 * g1);
                double g3 = d3 - ((L2 * g1) + (L3 * g2));

                double z = g3 / finalC3;
                double y = (g2 - (newC2 * z)) / newB2;
                double x = (g1 - ((c1 * z) + (b1 * y))) / a1;

                System.out.println("Solution: \nx = " + x + "\ny = " + y + "\nz = " + z);
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        // Created an instance of SECalculator
        SECalculator SECalculator = new SECalculator();
        SECalculator.solve();



    }
}