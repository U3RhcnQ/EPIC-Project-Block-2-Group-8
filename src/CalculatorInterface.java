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
        while (true){

            System.out.printf("What do you want to do ?" +
                    "%n%n 1: Arithmetic Calculator" +
                    "%n 2: Algebraic Calculator" +
                    "%n 3: Arithmetic Game" +
                    "%n 4: Calculus Calculator" +
                    "%n 0: Exit"+
                    "%n%nSelect an option: ");

            // try to get user input and catch exception if input type mismatch
            try {
                optionSelected = scanner.nextInt();

            } catch(Exception e){
                scanner.nextLine();  // discard newline or we get stuck infinite loop
                System.out.printf("%nSorry your input was invalid can you try again? %nSelect an option: ");
                continue;
            }

            scanner.nextLine();  // discard newline make sure next inputs are valid

            switch (optionSelected){
                case 0: // Exit code here

                    System.out.printf("%nThank you for using Calky we hope you had a fun and productive time :)%n");
                    break;

                case 1: // Arithmetic Calculator
                    scanner.nextLine();  // discard newline
                    System.out.print("\nYou Selected the Arithmetic Calculator: "+"\nPlease type in your expression to solve: ");
                    String expression = scanner.nextLine();
                    ArithmeticCalculator calculator = new ArithmeticCalculator(expression);
                    try {
                        System.out.println("\nWe have nothing left to do !\nSo the solution is: " + calculator.solve() + "\nWasn't that easy ?");

                    } catch (PrettyException | ArithmeticException e) {
                        // Handle the PrettyException
                        System.err.println(e.getMessage());
                        // Bad fix for out of order error printing
                        Thread.sleep(25); // Delay for 25 milliseconds
                        //e.printStackTrace(); // For debugging

                    } catch (Exception e) {
                        // Catch other potential exceptions (optional)
                        System.err.println("\nCaught an unexpected exception: " + e.getMessage());
                        e.printStackTrace();
                        // Bad fix for out of order error printing
                        Thread.sleep(25); // Delay for 25 milliseconds
                    }
                    break;

                case 2: //Linear Algebra Calculator

                    System.out.printf("%nYou Selected the Simultaneous Equation Calculator: %n");

                    try {
                        SECalculator SECalculator = new SECalculator();
                        System.out.println(SECalculator.solve());

                    } catch (Exception e) {
                        // Catch other potential exceptions
                        System.err.printf("%nOh no we ran into the following error: %s%n%n", e.getMessage());
                        //e.printStackTrace();
                        // Bad fix for out of order error printing
                        Thread.sleep(25); // Delay for 25 milliseconds
                    }
                    break;
                case 4: //Calculus Calculator
                    System.out.printf("%nYou Selected the Simultaneous Equation Calculator: %n");
                    System.out.printf("Which are you looking for" +
                            "%n%n Integration: 1" +
                            "%n Differentiation: 2");
                    int choice = scanner.nextInt();
                    String equationInput;
                    switch (choice){
                        case 1:
                            System.out.printf("%nYou have chosen Integration" +
                                    "%n Make sure to Integrate relative to x" +
                                    "%n%nPlease enter your equation:  ");

                            equationInput = scanner.nextLine();

                            Integration integration = new Integration(equationInput);
                            integration.solve();
                            break;
                        case 2:
                            System.out.printf("%nYou have chosen Differentiation" +
                                    "%n Make sure to Differentiate relative to x" +
                                    "%n%nPlease enter your equation:  ");

                            equationInput = scanner.nextLine();

                            Differentiate differentiate = new Differentiate(equationInput);
                            differentiate.solve();
                            break;
                        default:
                            System.out.printf("%nSorry your input was invalid can you try again? %nSelect an option: ");
                            break;
                    }
                default:
                    System.out.printf("%nSorry your input was invalid can you try again? %nSelect an option: ");
                    break;

            }
        } while (optionSelected != 0);
    }
}
