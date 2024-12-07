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
        int optionSelected;

        // Main program loop
        while (true){

            System.out.print("What do you want to do ?" +
                    "\n\n 1: Arithmetic Calculator" +
                    "\n 2: Algebraic Calculator" +
                    "\n 3: Arithmetic Game" +
                    "\n 4: Other Useful Calculations" +
                    "\n 0: Exit"+
                    "\n\nSelect an option: ");

            try{
                optionSelected = scanner.nextInt();
            }
            catch(Exception e){
                scanner.nextLine();  // discard newline or we get stuck infinite loop
                System.out.print("\nSorry your input was invalid can you try again?\nSelect an option: ");
                continue;
            }

            // we exit program loop here
            if (optionSelected == 0){
                break;
            }

            switch (optionSelected){
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
                    scanner.nextLine();
                    System.out.print("\nYou Selected the Simultaneous Equation Calculator: ");
                    LinearAlgebraCalculator2 linearAlgebraCalculator2 = new LinearAlgebraCalculator2();
                    linearAlgebraCalculator2.solve();
                    break;


                default:
                    System.out.print("\nSorry your input was invalid can you try again?\nSelect an option: ");
                    break;
            }
        }
    }
}
