import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinearAlgebraCalculator2 {

    //Coefficients for each expression
    private double a1 = 0, b1 = 0, c1 = 0, d1 = 0;
    private double a2 = 0, b2 = 0, c2 = 0, d2 = 0;
    private double a3 = 0, b3 = 0, c3 = 0, d3 = 0;

    public void parse(String input, int expressionNumber) throws Exception {
        List<String> orderOfOperations = List.of("x", "y", "z", "=");
        input = input.replace("+", ""); //Gets rid of +'s

        // Input validation
        if (!input.matches("(-?\\d*\\.?\\d*[xyz]\\s*)+(=\\s*-?\\d+\\.?\\d*)")) {
            throw new Exception("Invalid input format! Use the form ax + by + cz = d.");
            /*
        Regex legend:
        -? → Optional minus sign for negative numbers.
	    \\d* → Zero or more digits (e.g., 123, or no digits like x for coefficient 1).
	    \\.? → Optional decimal point (allows decimals like 3.5 or integers like 2).
	    \\d* → Zero or more digits after the decimal point (e.g., 0.5 or 3.).
	    [xyz] → Matches one of the variables x, y, or z.
	    \\s* → Allows for zero or more spaces after the variable.
	    + → The entire group can repeat one or more times (e.g., 3x, -2y, 0.5z).

	    = → Matches the equals sign (=).
	    \\s* → Allows for zero or more spaces after the equals sign.
	    -? → Optional minus sign for negative values.
	    \\d+ → One or more digits for the integer part (e.g., 5, 123).
	    \\.? → Optional decimal point.
	    \\d* → Zero or more digits for the fractional part (allows decimals like 4.5 or integers like 6).
        */
        }

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an instance of LinearAlgebraCalculator2
        LinearAlgebraCalculator2 linearAlgebraCalculator2 = new LinearAlgebraCalculator2();

        try {
            //Explanation
            System.out.println("\nExpression must be in the form of: ");
            System.out.print("\nFor 1 variable: ax = d");
            System.out.print("\nFor 2 variable: ax + by = d");
            System.out.print("\nFor 3 variable: ax + by + cx = d");

            System.out.print("\n\nHow many variables (1 to 3): ");
            int k = scanner.nextInt();
            scanner.nextLine();

            //Make sure it can only have up to 3 variables
            if (k < 1 || k > 3) {
                throw new Exception("Number of variables must be between 1 and 3.");
            }

            // Input and process expressions
            for (int i = 1; i <= k; i++) {
                System.out.print("\nExpression" + i + ": ");
                String expression = scanner.nextLine();
                linearAlgebraCalculator2.parse(expression, i);
            }

            double a1 = linearAlgebraCalculator2.a1;
            double b1 = linearAlgebraCalculator2.b1;
            double c1 = linearAlgebraCalculator2.c1;
            double d1 = linearAlgebraCalculator2.d1;
            double a2 = linearAlgebraCalculator2.a2;
            double b2 = linearAlgebraCalculator2.b2;
            double c2 = linearAlgebraCalculator2.c2;
            double d2 = linearAlgebraCalculator2.d2;
            double a3 = linearAlgebraCalculator2.a3;
            double b3 = linearAlgebraCalculator2.b3;
            double c3 = linearAlgebraCalculator2.c3;
            double d3 = linearAlgebraCalculator2.d3;

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
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}