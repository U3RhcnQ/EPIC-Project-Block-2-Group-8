import java.util.Scanner;

public class CalculatorInterface {
    public static void main(String[] args) throws Exception {

        System.out.printf("%n" +
                " ██████╗ █████╗ ██╗     ██╗  ██╗██╗   ██╗    ██╗   ██╗ ██╗    ██████╗ %n" +
                "██╔════╝██╔══██╗██║     ██║ ██╔╝╚██╗ ██╔╝    ██║   ██║███║   ██╔═████╗%n" +
                "██║     ███████║██║     █████╔╝  ╚████╔╝     ██║   ██║╚██║   ██║██╔██║%n" +
                "██║     ██╔══██║██║     ██╔═██╗   ╚██╔╝      ╚██╗ ██╔╝ ██║   ████╔╝██║%n" +
                "╚██████╗██║  ██║███████╗██║  ██╗   ██║        ╚████╔╝  ██║██╗╚██████╔╝%n" +
                " ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝   ╚═╝         ╚═══╝   ╚═╝╚═╝ ╚═════╝ %n");
        System.out.println("Made By Team 8:");

        System.out.printf("%nWelcome to the Calculator here you will have the ability to use multiple different applications %n");

        Scanner scanner = new Scanner(System.in);
        int optionSelected = -1; // initial value before selection

        // Main program loop
        do {

            System.out.printf("What do you want to do ?" +
                    "%n%n 1: Arithmetic Calculator" +
                    "%n 2: Algebraic Calculator" +
                    "%n 3: Kids Calculator" +
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

                    System.out.printf("%nYou Selected the Arithmetic Calculator: %nPlease type in your expression to solve: ");
                    String expression = scanner.nextLine();
                    ArithmeticCalculator calculator = new ArithmeticCalculator(expression);

                    // Try with resources - Makes sure we clear the memory after use
                    try {

                        System.out.printf("%nWe have nothing left to do ! %nSo the solution is: %s %nWasn't that easy ?%n", calculator.solve());

                    } catch (PrettyException | ArithmeticException e) {

                        // Handle the PrettyException
                        System.err.println(e.getMessage());
                        // Bad fix for out of order error printing
                        Thread.sleep(25); // Delay for 25 milliseconds

                    } catch (Exception e) {

                        // Catch other potential exceptions
                        System.err.printf("%nCaught an unexpected exception: %s%n", e.getMessage());
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
                case 3:
                    KidsCalc calc = new KidsCalc();
                    calc.start();
                    break;
                case 4: //Calculus Calculator

                    // Get the choice of Integration or Differentiation
                    System.out.printf("%nYou Selected the Simultaneous Equation Calculator: %n");
                    System.out.printf("Which are you looking for" +
                            "%n%n Integration: 1" +
                            "%n Differentiation: 2" +
                            "%n%n Enter your choice: ");
                    int choice = scanner.nextInt();
                    String equationInput;
                    switch (choice){
                        case 1:
                            // Get the user's equation from the user
                            System.out.printf("%nYou have chosen Integration" +
                                    "%n Make sure to Integrate relative to x" +
                                    "%n%nPlease enter your equation:  ");

                            scanner.nextLine();
                            equationInput = scanner.nextLine();

                            // Create the integration object and input the equation
                            Integration integration = new Integration(equationInput);
                            integration.solve();
                            break;
                        case 2:
                            // Get the user's equation from the user
                            System.out.printf("%nYou have chosen Differentiation" +
                                    "%n Make sure to Differentiate relative to x" +
                                    "%n%nPlease enter your equation:  ");

                            scanner.nextLine();
                            equationInput = scanner.nextLine();

                            // Create the differentiation object and input the equation
                            Differentiate differentiate = new Differentiate(equationInput);
                            differentiate.solve();
                            break;
                        default:// In case an incorrect choice is given output this message
                            System.out.printf("%nSorry your input was invalid, make sure to pick either 1 or 2");
                            break;
                    }
                    break;
                default:
                    System.out.printf("%nSorry your input was invalid can you try again? %nSelect an option: ");
                    break;

            }
        } while (optionSelected != 0);
    }
}

