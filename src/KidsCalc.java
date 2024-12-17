import java.util.Scanner;

public class KidsCalc {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[]args){
        KidsCalc calc = new KidsCalc();
        calc.start();
    }

    public void start() {
        int choice;
        do {
            kidsMenu();
            choice = getChoice();
            processChoice(choice);
        } while (choice != 0);
    }

    private void kidsMenu()

    {
        System.out.print("Welcome to Kids Calculator!\n\nEnter '1' to learn addition!");
        System.out.print("\nEnter '2' to learn subtraction!\nEnter '3' to learn division!\n");
        System.out.println("Enter '4' to learn multiplication!\n\nEnter '0' to exit");
    }

    private int getChoice() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a number from 1 to 4");
            return -1;
        }
    }
    private int validNumber() {

        while (!validAddInput) {
            System.out.println("Enter number 1 ( Must be between 0 and 10 ):");
            num1 = scanner.nextInt();
            if (num1 > 0 && num1 < 10) {
                validAddInput = true;
            } else {
                System.out.println("Number 1 must be between 0 and 10. Please try again");
            }
        } return -1;
    }
    int num1 = 0;
    int num2 = 0;
    boolean validAddInput = false;
    boolean validAddInput2 = false;


    private int validNumber2() {

        while(!validAddInput2){
            System.out.println("Enter number 2 ( Must be between 0 and 10 ):");
            num2 = scanner.nextInt();
            if (num2 > 0 && num2 <10){
                validAddInput2 = true;
            }else {
                System.out.println("Number 2 must be between 0 and 10. Please try again");
            }
        } return -1;
    }

    private void processChoice(int choice){
        switch (choice){
            case 1:
                System.out.print("\nAddition selected!\n\n");
                num1 = 0;
                num2 = 0;
                validAddInput = false;
                validAddInput2 = false;
                validNumber();
                validNumber2();
                int addresult = num1 + num2;


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

                System.out.println("\n\n As we can see: " + num1 + " + " + num2 + " Equals " + addresult);
                break;


            case 2:
                System.out.println("\nSubtraction selected!\nNOTE: Number 1 must be bigger than Number 2");
                num1 = 0;
                num2 = 0;
                validAddInput = false;
                validAddInput2 = false;
                validNumber();
                validNumber2();
                int subresult = num1 - num2;

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

                System.out.println("\n\n As we can see: " + num1 + " - " + num2 + " Equals " + subresult);
                break;

            case 3:
                System.out.print("\nDivision selected!\n");
                num1 = 0;
                num2 = 0;
                validAddInput = false;
                validAddInput2 = false;
                validNumber();
                validNumber2();
                int divresult = num1 / num2;

                int x = 1;
                while (x < (divresult)) {

                    for (int i = 0; i < num2; i++) {
                        System.out.print("+-----+ ");
                    }
                    System.out.print("                                         ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("        ");
                    }
                    System.out.println("+-----+ ");
                    for (int i = 0; i < num2; i++) {
                        System.out.print("|  " + x + "  | ");
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
                    System.out.print("|  " + divresult + "  | ");
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

                System.out.println("\n\n As we can see,if person 1 shares " + num1 + " apples with " + num2 + " people,then everyone gets " + divresult + " apples");
                System.out.println("In maths words: " + num1 + " divided by " + num2 + " equals " + divresult);

                break;

            case 4:
                System.out.print("\nMultiplication selected!\n");
                num1 = 0;
                num2 = 0;
                validAddInput = false;
                validAddInput2 = false;
                validNumber();
                validNumber2();

                int z = 1;
                while (z < num1) {


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
                    for (int i = 1; i <= (num2); i++) {
                        System.out.print("|  " + i + "  | ");
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
                for (int i = 1; i <= num2; i++) {
                    System.out.print("|  " + i + "  | ");
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
                System.out.println();

                break;
            case 0:
                System.out.println("Kids Calculator closed.");

            default:
                System.out.println("Invalid input. Number must be from 1 to 4");
                break;


        }
    }
}