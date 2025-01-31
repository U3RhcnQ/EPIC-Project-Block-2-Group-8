import java.util.InputMismatchException;
import java.util.Scanner;

public class KidsCalc {
    private Scanner scanner = new Scanner(System.in);

    //starts KidsCalc - (shows menu and prompts user to select what they want to do e.g. addition/multiplication)
    public void start() {
        int choice;
        do {
            kidsMenu();
            choice = getChoice();
            processChoice(choice);
        } while (choice != 0);
    }

    //prints menu
    private void kidsMenu() {
        System.out.println("\nWelcome to Kids Calculator!\n" +
                "\nEnter '1' to learn addition!" +
                "\nEnter '2' to learn subtraction!" +
                "\nEnter '3' to learn division!" +
                "\nEnter '4' to learn multiplication!" +
                "\n\nEnter '0' to exit");
    }

    //when a string is entered where an integer should be, INVALID_INPUT will be returned.
    //This will trigger case '-2' in the switch-case field below which just resets the program
    public static final int INVALID_INPUT = -2;

    //returns users input (Numbers 0,1,2,3,4) and catches error if a string is input.
    private int getChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a number from 1 to 4");
            return INVALID_INPUT;
        }
    }
    //initializing variables
    int num1 = 0;
    int num2 = 0;
    boolean validAddInput = false;
    boolean validAddInput2 = false;

    //resets variables to original values (allows program to loop)
    private void reset() {
    num1 = 0;
    num2 = 0;
    validAddInput = false;
    validAddInput2 = false;
}
    // validates number 1 ( must be integer from 1-9 or else you will be asked to print a number in that range
    // or simply to print a number if you entered a string instead )
    private int validNumber() {

        while (!validAddInput) {
            System.out.println("Enter number 1 ( Must be between 0 and 10 ):");

            try{num1 = scanner.nextInt();
                if (num1 > 0 && num1 < 10) {
                    validAddInput = true;
                }
                else {
                    System.out.println("Number 1 must be between 0 and 10. Please try again");
                }
            } catch(InputMismatchException e){
                System.out.println("Must enter a number.");
                scanner.next();
            }

        } return -1;
    }
    // validates number 2 ( must be integer from 1-9 or else you will be asked to print a number in that range
    // or simply to print a number if you entered a string instead )
    private int validNumber2() {

        while(!validAddInput2){
            System.out.println("Enter number 2 ( Must be between 0 and 10 ):");
            try{num2 = scanner.nextInt();
                if (num2 > 0 && num2 < 10) {
                    validAddInput2 = true;
                }
                else {
                    System.out.println("Number 1 must be between 0 and 10. Please try again");
                }
            } catch(InputMismatchException e){
                System.out.println("Must enter a number.");
                scanner.next();
            }
        } return -1;
    }

    //method for switch case of add/sub/div/mult with integer 'choice' as parameter
    private void processChoice(int choice){
        switch (choice){
            case 1:
                System.out.print("\nAddition selected!\n\n");

                reset();
                validNumber();
                validNumber2();
                int addresult = num1 + num2;

                //prints boxes
                for (int i = 1; i <= num1; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= addresult; i++) {
                    System.out.print("+-----+ ");
                }

                System.out.println();

                for (int i = 1; i <= num1; i++) {
                    System.out.print("|  " + i + "  | ");
                }
                System.out.print("      PLUS      ");
                for (int i = 1; i <= num2; i++) {
                    System.out.print("|  " + i + "  | ");
                }
                System.out.print("     EQUALS     ");

                int j = 1;
                while(j<10 && j <= addresult){
                    System.out.print("|  " + j + "  | ");
                    j++;}
                for (int i = 10; i <= addresult; i++) {
                    System.out.print("|  " + i + " | ");}


                System.out.println();

                for (int i = 1; i <= num1; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= addresult; i++) {
                    System.out.print("+-----+ ");
                }
                //explains in simple terms for a kid
                System.out.println("\n\nImagine you have " + num1 + " candies in one hand and " + num2 + " candies in your other hand. If you put them all together, you have " + addresult + " candies in total.");
                System.out.println("In math words: " + num1 + " plus " + num2 + " equals " + addresult);
                break;


            case 2:
                System.out.println("\nSubtraction selected!\nNOTE: Number 1 must be bigger than Number 2");

               reset();

                validNumber();
                validNumber2();

                //ensures num1 is bigger than num2. if its not it will reset and ask you to re enter ur numbers
                if(num2 > num1){
                    System.out.println("Number 1 must be bigger than Number 2!");
                    reset();

                    validNumber();
                    validNumber2();
                }
                int subresult = num1 - num2;

                //PRINTING BOXES
                for (int i = 1; i <= num1; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= subresult; i++) {
                    System.out.print("+-----+ ");
                }

                System.out.println();

                for (int i = 1; i <= num1; i++) {
                    System.out.print("|  " + i + "  | ");
                }
                System.out.print("      MINUS     ");
                for (int i = 1; i <= num2; i++) {
                    System.out.print("|  " + i + "  | ");
                }
                System.out.print("     EQUALS     ");
                for (int i = 1; i <= subresult; i++) {
                    System.out.print("|  " + i + "  | ");
                }

                System.out.println();

                for (int i = 1; i <= num1; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 1; i <= subresult; i++) {
                    System.out.print("+-----+ ");
                }

                //explains in simple terms for a kid
                System.out.println("\n\nLet's say you start with " + num1 + " stickers, but you give " + num2 + " stickers to your friend. Now, you only have " + subresult + " stickers left.");
                System.out.println("In math words: " + num1 + " minus " + num2 + " equals " + subresult);

                break;

            case 3:
                System.out.print("\nDivision selected!\n");
                reset();
                validNumber();
                validNumber2();

                int divresult = num1 / num2;

                //Asks user to enter two divisible numbers if there is a remainder
                int divremaninder = num1 % num2;
                while(divremaninder != 0){
                    System.out.println(num1+" does not evenly divide into "+num2+". It has a remainder of "+divremaninder+".\nYou could try "+(num1-divremaninder)+" divided by "+(num2)+"?");
                    reset();
                    validNumber();
                    validNumber2();
                    divresult = num1 / num2;
                    divremaninder = num1 % num2;
                }

                int x = 1;
                int y = 1;
                while (x < (divresult)) {

                    //PRINTS BOXES
                    for (int i = 0; i < num2; i++) {
                        System.out.print("+-----+ ");
                    }
                    System.out.print("                                         ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("        ");
                    }
                    System.out.println("+-----+ ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("|  " + y + "  | ");
                        y++;
                    }
                    System.out.print("                                         ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("        ");
                    }
                    System.out.println("|  " + x + "  | ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("+-----+ ");

                    }
                    System.out.print("                                         ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("        ");
                    }
                    System.out.println("+-----+");


                    x++;
                }
                for (int i = 0; i < num2; i++) {
                    System.out.print("+-----+ ");

                }
                System.out.print("                      ");


                for (int i = 1; i <= num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                   ");
                System.out.println("+-----+ ");

                for (int i = 0; i < num2; i++) {
                    System.out.print("|  " + y + "  | ");
                    y++;
                }
                System.out.print("      DIVIDED BY      ");

                for (int i = 1; i <= num2; i++) {
                    System.out.print("|  " + i + "  | ");
                }
                System.out.print("     EQUALS        ");

                System.out.print("|  " + divresult + "  | ");
                System.out.println();

                for (int i = 0; i < num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                      ");


                for (int i = 1; i <= num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                   ");
                System.out.println("+-----+ ");

                //explains in simple terms for a kid
                System.out.println("\n\nImagine you have " + num1 + " cookies and " + num2 + " friends, and you want to share the cookies equally with all your friends. Each friend gets " + divresult + " cookies.");
                System.out.println("In math words: " + num1 + " divided by " + num2 + " equals " + divresult);



                break;

            case 4:
                System.out.print("\nMultiplication selected!\n");
                reset();
                validNumber();
                validNumber2();
                int multresult = num1 * num2;

                int z = 1;
                int a =1;
                while (z < num1) {

                //PRINTS BOXES
                    System.out.print("+-----+  ");
                    System.out.print("                                        ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("        ");
                    }

                    for (int i = 0; i < num2; i++) {
                        System.out.print("+-----+ ");

                    }
                    System.out.println();
                    System.out.print("|  " + z + "  | ");
                    System.out.print("                                         ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("        ");
                    }

                    //changes box size when numbers reach double digits
                    int b;
                    b=1 +(num2 *(z-1));

                        for (a = 1; a <= num2; a++) {
                            if(b<10){
                            System.out.print("|  " + b + "  | ");}
                            if(b>=10){
                                System.out.print("|  " + b + " | ");
                            }
                            b++;
                        }

                    System.out.println();
                    System.out.print("+-----+ ");
                    System.out.print("                                         ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("        ");
                    }
                    for (int i = 0; i < num2; i++) {
                        System.out.print("+-----+ ");
                    }
                    System.out.println();

                    z++;
                }
                System.out.print("+-----+                          ");


                for (int i = 0; i < num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 0; i < num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.println();
                System.out.print("|  " + num1 + "  |       MULTIPLIED BY      ");
                for (int i = 1; i <= num2; i++) {
                    System.out.print("|  " + i + "  | ");
                }
                System.out.print("     EQUALS     ");

                //changes box size when numbers reach double digits
                int c =multresult-num2+1;
                for (int i = 1; i <= num2; i++) {
                    if(c<10){
                        System.out.print("|  " + c + "  | ");}
                    if(c>=10){
                        System.out.print("|  " + c + " | ");
                    }
                    c++;

                }

                System.out.println();
                System.out.print("+-----+                          ");
                for (int i = 0; i < num2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for (int i = 0; i < num2; i++) {
                    System.out.print("+-----+ ");
                }

                //explains in simple terms for a kid
                System.out.println("\n\nThink about having " + num1 + " boxes, and each box has " + num2 + " chocolates. All together, that's " + multresult + " chocolates!");
                System.out.println("In math words: " + num1 + " times " + num2 + " equals " + multresult);

                break;

            case 0:
                //for when you want to exit the program
                System.out.println("Kids Calculator closed.");
                break;

            //if string is entered, at selection stage
            case -2:
                //when INVALID_INPUT is returned(a string is entered at the start), this case is triggered and the program is reset to  the start
                System.out.println();
                break;

            default:
                //if somehow an un accounted for case occurs the program is reset.
                System.out.println("Program reset");
                break;


        }
    }
}
