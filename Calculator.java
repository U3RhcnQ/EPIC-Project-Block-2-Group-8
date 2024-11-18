import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;

public class Calculator {

    public static void main(String[] args) throws Exception {

        String expression = "345.051505+++2*-(-423--1)+5";
        ArrayList<String> expressionList = new ArrayList<String>();

        Character[] operators = {'*','/','+','-'};

        int lasthit = 0;
        boolean islastoperator = false;
        boolean islastoperatorminus = false;

        for(int i = 0; i < expression.length(); i++){

            char c = expression.charAt(i);

            System.out.println(i);
            System.out.println(c+"_");

            if ((Arrays.asList(operators).contains(c)) || (c == '(') || (c == ')')){
                if ((c == '-') && islastoperator){

                    if (islastoperatorminus) {
                        throw new Exception("U dumb fuck");
                    }

                    lasthit = i;

                } else {

                    if (lasthit != i) {
                        expressionList.add(expression.substring(lasthit, i));
                    }
                    expressionList.add(String.valueOf(c));
                    lasthit = i + 1;

                }

                if (c== '-') {
                    islastoperatorminus = true;
                }

                System.out.println("List: " + expressionList);
                islastoperator = true;

            } else {
                islastoperator = false;
            }
        }

        if (lasthit < expression.length()) {
            expressionList.add(expression.substring(lasthit));
        }

        System.out.println("\nResulting List: "+expressionList);

    }
}
