import java.util.Scanner;

public class LinearAlgebraCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            System.out.print("How many variables do your equations have? \n1, 2, 3: ");
            String varNum = scanner.nextLine();

            if (varNum.equals("1")) {
                // For 1 variable
                System.out.println("Enter the equation (ax = d): ");
                System.out.print("a: ");
                float a = scanner.nextFloat();
                System.out.print("d: ");
                float d = scanner.nextFloat();

                float x = d / a;

                if (a == 0) {
                    System.out.println("No solution or infinite solutions exist.");
                } else {
                    System.out.println("Solution: x = " + x);
                }

            } else if (varNum.equals("2")) {
                // For 2 variables
                System.out.println("Enter coefficients for the first equation (ax + by = d): ");
                System.out.print("a: ");
                float a1 = scanner.nextFloat();
                System.out.print("b: ");
                float b1 = scanner.nextFloat();
                System.out.print("d: ");
                float d1 = scanner.nextFloat();

                System.out.println("Enter coefficients for the second equation (ax + by = d): ");
                System.out.print("a: ");
                float a2 = scanner.nextFloat();
                System.out.print("b: ");
                float b2 = scanner.nextFloat();
                System.out.print("d: ");
                float d2 = scanner.nextFloat();

                float L1 = a2 / a1;
                float newA2 = a2 - (L1 * a1);
                float newB2 = b2 - (L1 * b1);
                float newD2 = d2 - (L1 * d1);

                float y = newD2 / newB2;
                float x = (d1 - (b1 * y)) / a1;

                System.out.println("Solution: x = " + x + ", y = " + y);

            } else if (varNum.equals("3")) {
                // For 3 variables
                System.out.println("Enter coefficients for the first equation (ax + by + cz = d): ");
                System.out.print("a: ");
                float a1 = scanner.nextFloat();
                System.out.print("b: ");
                float b1 = scanner.nextFloat();
                System.out.print("c: ");
                float c1 = scanner.nextFloat();
                System.out.print("d: ");
                float d1 = scanner.nextFloat();

                // Inputs for second equation
                System.out.println("Enter coefficients for the second equation (ax + by + cz = d): ");
                System.out.print("a: ");
                float a2 = scanner.nextFloat();
                System.out.print("b: ");
                float b2 = scanner.nextFloat();
                System.out.print("c: ");
                float c2 = scanner.nextFloat();
                System.out.print("d: ");
                float d2 = scanner.nextFloat();

                // Inputs for third equation
                System.out.println("Enter coefficients for the third equation (ax + by + cz = d): ");
                System.out.print("a: ");
                float a3 = scanner.nextFloat();
                System.out.print("b: ");
                float b3 = scanner.nextFloat();
                System.out.print("c: ");
                float c3 = scanner.nextFloat();
                System.out.print("d: ");
                float d3 = scanner.nextFloat();


            /*
            What the matrix looks like
            |a1 b1 c1|   |d1|
            |a2 b2 c2| = |d2|
            |a3 b3 c3|   |d3|

            U Triangle
            |1   0  0|
            |L1  1  0|
            |L2 L3  1|
         */

                // L Triangle
                float L1 = a2 / a1;
                float newA2 = a2 - (L1 * a1);
                float newB2 = b2 - (L1 * b1);
                float newC2 = c2 - (L1 * c1);

                float L2 = a3 / a1;
                float newA3 = a3 - (L2 * a1);
                float newB3 = b3 - (L2 * b1);
                float newC3 = c3 - (L2 * c1);

                float L3 = newB3 / newB2;
                float finalA3 = newA3 - (L3 * newA2);
                float finalB3 = newB3 - (L3 * newB2);
                float finalC3 = newC3 - (L3 * newC2);

                // Solve for g3, g2, g1
                float g1 = d1;
                float g2 = d2 - (L1 * g1);
                float g3 = d3 - ((L2 * g1) + (L3 * g2));

                // Ux=z
                float z = g3 / finalC3;
                float y = (g2 - (newC2 * z)) / newB2;
                float x = (g1 - ((c1 * z) + (b1 * y))) / a1;

                System.out.println("Solution: \nx = " + x + "\ny = " + y + "\nz = " + z);

            } else {
                System.out.println("Invalid input. Only 1, 2, or 3 variables are supported.");
            }

    }
}