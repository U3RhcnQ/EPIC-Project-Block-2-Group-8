import java.util.Scanner;

public class CalculatorInterface {
    public static void main(String[] args) throws Exception {

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

        System.out.print("What do you want to do ?" +
                "\n\n 1: Arithmetic Calculator" +
                "\n 2: Algebraic Calculator" +
                "\n 3: Arithmetic Game" +
                "\n 4: Other Useful Calculations" +
                "\n 0: Exit"+
                "\n\nSelect an option: ");
        optionSelected = scanner.nextInt();

        while (optionSelected != 0){
            switch (optionSelected){
                case 1:
                    scanner.nextLine();  // discard newline
                    System.out.print("\nYou Selected the Arithmetic Calculator: "+"\nPlease type in your expression to solve: ");
                    String expression = scanner.nextLine();
                    arithmeticCalculator calculator = new arithmeticCalculator(expression);
                    try {
                        System.out.println("\nWe have nothing left to do !\nSo the solution is: " + calculator.solve() + "\nWasn't that easy ?");

                    } catch (PrettyException e) {
                        // Handle the PrettyException
                        System.err.println("Caught a PrettyException: " + e.getMessage());
                        //e.printStackTrace(); // For debugging

                    } catch (Exception e) {
                        // Catch other potential exceptions (optional)
                        System.err.println("Caught an unexpected exception: " + e.getMessage());
                        //e.printStackTrace();
                    }

                default:
                    System.out.print("\nSorry your input was invalid can you try again?\nSelect an option: ");
                    optionSelected = scanner.nextInt();
            }
        }
    }
}
