import java.util.Scanner;

public class KidsCalc {
    public static void main(String[] args){
        //prompt
        System.out.print("Welcome to Kids Calculator!\n\nEnter '1' to learn addition!");
        System.out.print("\nEnter '2' to learn subtraction!\nEnter '3' to learn division!\n");
        System.out.println("Enter '4' to learn multiplication!");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                System.out.print("\nAddition selected!\nEnter number 1:\n");
                int add1 = scanner.nextInt();
                System.out.print("\nEnter number 2:\n");
                int add2 = scanner.nextInt();
                int addresult = add1 + add2;

                for( int i = 1; i <= add1; i++){
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for( int i = 1; i <= add2; i++){
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for( int i = 1; i <= addresult; i++){
                    System.out.print("+-----+ ");}

                System.out.println();

                for(int i =1;i <= add1; i++){
                    System.out.print("|  "+i+"  | ");
                }
                System.out.print("      PLUS      ");
                for( int i = 1; i <= add2; i++){
                    System.out.print("|  "+i+"  | ");}
                System.out.print("     EQUALS     ");
                for( int i = 1; i <= addresult; i++){
                    System.out.print("|  "+i+"  | ");}

                System.out.println();

                for(int i =1;i <= add1; i++){
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for( int i = 1; i <= add2; i++){
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for( int i = 1; i <= addresult; i++){
                    System.out.print("+-----+ ");}

                System.out.println("\n\n As we can see: "+ add1 + " + " + add2 + " Equals " + addresult);
                break;


            case 2:
                System.out.print("\nSubtraction selected!\nEnter number 1:\n");
                int sub1 = scanner.nextInt();
                System.out.print("\nEnter number 2:\n");
                int sub2 = scanner.nextInt();
                int subresult = sub1 - sub2;

                for( int i = 1; i <= sub1; i++){
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for( int i = 1; i <= sub2; i++){
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for( int i = 1; i <= subresult; i++){
                    System.out.print("+-----+ ");}

                System.out.println();

                for(int i =1;i <= sub1; i++){
                    System.out.print("|  "+i+"  | ");
                }
                System.out.print("      MINUS     ");
                for( int i = 1; i <= sub2; i++){
                    System.out.print("|  "+i+"  | ");}
                System.out.print("     EQUALS     ");
                for( int i = 1; i <= subresult; i++){
                    System.out.print("|  "+i+"  | ");}

                System.out.println();

                for(int i =1;i <= sub1; i++){
                    System.out.print("+-----+ ");
                }
                System.out.print("                ");
                for( int i = 1; i <= sub2; i++){
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for( int i = 1; i <= subresult; i++){
                    System.out.print("+-----+ ");}

                System.out.println("\n\n As we can see: "+ sub1 + " - " + sub2 + " Equals " + subresult);
                break;

            case 3:
                System.out.print("\nDivision selected!\nHow many apples does person 1 have?:\n");
                int div1 = scanner.nextInt();
                System.out.print("\nHow many people want some apples?:\n");
                int div2 = scanner.nextInt();
                int divresult = div1 / div2;

                int x =1;
                while(x < (divresult)) {


                    System.out.print("+-----+  ");
                    System.out.print("                                        ");
                    for (int i = 0; i < div2; i++) {
                        System.out.print("        ");
                    }
                    System.out.println("+-----+ ");
                    System.out.print("|  "+x+"  | ");
                    System.out.print("                                         ");
                    for (int i = 0; i < div2; i++) {
                        System.out.print("        ");
                    }
                    System.out.println("|  "+x+"  | ");
                    System.out.print("+-----+  ");
                    System.out.print("                                        ");
                    for (int i = 0; i < div2; i++) {
                        System.out.print("        ");
                    }
                    System.out.println("+-----+");




                        x++;
                }
                System.out.print("+-----+ ");
                System.out.print("                      ");


                for (int i = 1; i <= div2; i++) {
                    System.out.print("+-----+ ");}
                System.out.print("                   ");
                    System.out.println("+-----+ ");

                    System.out.print("|  "+divresult+"  | ");
                System.out.print("      DIVIDED BY      ");

                for(int i =1; i <= div2; i++){
                    System.out.print("|  "+i+"  | ");}
                System.out.print("     EQUALS        ");

                    System.out.print("|  "+divresult+"  | ");
                System.out.println();

                System.out.print("+-----+ ");
                System.out.print("                      ");


                for (int i = 1; i <= div2; i++) {
                    System.out.print("+-----+ ");}
                System.out.print("                   ");
                System.out.println("+-----+ ");

                System.out.println("\n\n As we can see,if person 1 shares "+ div1 + " apples with " + div2 + " people,then everyone gets " + divresult + " apples");
                System.out.print("In maths words: " + div1 + " divided by "+ div2 + " equals " + divresult);

                break;

            case 4:
                System.out.print("\nMultiplication selected!\nHow many apples does person 1 have?:\n");
                int mult1 = scanner.nextInt();
                System.out.print("\nHow many people want some apples?:\n");
                int mult2 = scanner.nextInt();
                int multresult = mult1 * mult2;

                int z = 1;
                while(z < mult1) {


                    System.out.print("+-----+  ");
                    System.out.print("                                        ");
                    for (int i = 0; i < mult2; i++) {
                        System.out.print("        ");
                    }

                    for (int i = 0; i < mult2; i++) {
                        System.out.print("+-----+ ");

                    }
                    System.out.println();
                    System.out.print("|  " + z + "  | ");
                    System.out.print("                                         ");
                    for (int i = 0; i < mult2; i++) {
                        System.out.print("        ");
                    }
                    for (int i = 1; i <= (mult2) ; i++) {
                        System.out.print("|  " + i + "  | ");
                    }
                    System.out.println();
                    System.out.print("+-----+ ");
                    System.out.print("                                         ");
                    for (int i = 0; i < mult2; i++) {
                        System.out.print("        ");
                    }
                    for (int i = 0; i < mult2; i++) {
                        System.out.print("+-----+ ");
                    }
                    System.out.println();

                    z++;
                }
                System.out.print("+-----+                          ");
//

                for (int i = 0; i < mult2 ; i++) {
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for (int i = 0; i < mult2; i++) {
                    System.out.print("+-----+ ");
                }
                System.out.println();
                System.out.print("|  "+mult1+"  |       MULTIPLIED BY      ");
                for (int i = 1; i <= mult2; i++) {
                    System.out.print("|  "+i+"  | ");}
                System.out.print("     EQUALS     ");
                for (int i = 1; i <= mult2; i++) {
                    System.out.print("|  "+i+"  | ");
                }
                System.out.println();
                System.out.print("+-----+                          ");
                for (int i = 0; i < mult2 ; i++) {
                    System.out.print("+-----+ ");}
                System.out.print("                ");
                for (int i = 0; i < mult2; i++) {
                    System.out.print("+-----+ ");
                }

                break;


        }
    }

}
