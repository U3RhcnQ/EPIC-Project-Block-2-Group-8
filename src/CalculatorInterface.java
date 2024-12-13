import java.util.Scanner;

public class CalculatorInterface {
    public static void main(String[] args) throws Exception {

        // Wierd fix for error printing out of order ?
        //System.setErr(System.out); // Redirect standard error to standard output

        System.out.println("\n" +
                " ██████╗ █████╗ ██╗      ██████╗██╗   ██╗██╗      █████╗ ████████╗ ██████╗ ██████╗     ██╗   ██╗ ██╗    ██████╗ \n" +
                "██╔════╝██╔══██╗██║     ██╔════╝██║   ██║██║     ██╔══██╗╚══██╔══╝██╔═══██╗██╔══██╗    ██║   ██║███║   ██╔═████╗\n" +
                "██║     ███████║██║     ██║     ██║   ██║██║     ███████║   ██║   ██║   ██║██████╔╝    ██║   ██║╚██║   ██║██╔██║\n" +
                "██║     ██╔══██║██║     ██║     ██║   ██║██║     ██╔══██║   ██║   ██║   ██║██╔══██╗    ╚██╗ ██╔╝ ██║   ████╔╝██║\n" +
                "╚██████╗██║  ██║███████╗╚██████╗╚██████╔╝███████╗██║  ██║   ██║   ╚██████╔╝██║  ██║     ╚████╔╝  ██║██╗╚██████╔╝\n" +
                " ╚═════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝      ╚═══╝   ╚═╝╚═╝ ╚═════╝ ");
        System.out.println("Made By Team 8:");

        System.out.println("\nWelcome to the Calculator here you will have the ability to use multiple different applications \n");

        Scanner scanner = new Scanner(System.in);
        int optionSelected = -1; // initial value before selection

        // Main program loop
        do {

            System.out.print("What do you want to do ?" +
                    "\n\n 1: Arithmetic Calculator" +
                    "\n 2: Algebraic Calculator" +
                    "\n 3: Arithmetic Game" +
                    "\n 4: Other Useful Calculations" +
                    "\n 0: Exit"+
                    "\n\nSelect an option: ");

            // try to get user input and catch exception if input type mismatch
            try {
                optionSelected = scanner.nextInt();

            } catch(Exception e){
                scanner.nextLine();  // discard newline or we get stuck infinite loop
                System.out.print("\nSorry your input was invalid can you try again?\nSelect an option: ");
                continue;
            }

            scanner.nextLine();  // discard newline make sure next inputs are valid

            switch (optionSelected){
                case 0: // Exit code here

                    System.out.println("\nThank you for using Calky we hope you had a fun and productive time :)");
                    break;

                case 1: // Arithmetic Calculator

                    System.out.print("\nYou Selected the Arithmetic Calculator: "+"\nPlease type in your expression to solve: ");
                    String expression = scanner.nextLine();
                    ArithmeticCalculator calculator = new ArithmeticCalculator(expression);
                    try {
                        System.out.println("\nWe have nothing left to do !\nSo the solution is: " + calculator.solve() + "\nWasn't that easy ?\n\n");

                    } catch (PrettyException | ArithmeticException e) {
                        // Handle the PrettyException
                        System.err.println(e.getMessage());
                        // Bad fix for out of order error printing
                        Thread.sleep(25); // Delay for 25 milliseconds
                        //e.printStackTrace(); // For debugging

                    } catch (Exception e) {
                        // Catch other potential exceptions
                        System.err.println("\nCaught an unexpected exception: " + e.getMessage());
                        e.printStackTrace();
                        // Bad fix for out of order error printing
                        Thread.sleep(25); // Delay for 25 milliseconds
                    }

                    break;

                case 2: //Linear Algebra Calculator
                    scanner.nextLine();
                    System.out.print("\nYou Selected the Simultaneous Equation Calculator: ");
                    LinearAlgebraCalculator2 linearAlgebraCalculator2 = new LinearAlgebraCalculator2();
                    linearAlgebraCalculator2.solve();
                    break;

                default:
                    System.out.print("\nSorry your input was invalid can you try again?\nSelect an option: ");
                    break;

            }
        } while (optionSelected != 0);
    }
}
