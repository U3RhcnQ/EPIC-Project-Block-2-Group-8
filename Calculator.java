import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Objects;

public class Calculator {

    public static String mathSolver (ArrayList<String> expressionList){
        String[] orderOfOperations = {"^", "*", "/", "+", "-"};
        for(String Operator: orderOfOperations) {
            for (int i = 0; i < expressionList.size(); i++) {
                if (expressionList.get(i).equals(Operator)){

                    double a = Double.parseDouble(expressionList.get(i-1));
                    double b = Double.parseDouble(expressionList.get(i+1));

                    switch (expressionList.get(i)) {
                        case "^" -> {
                            expressionList.set(i, String.valueOf(Math.pow(a, b)));
                        }
                        case "*" -> {
                            expressionList.set(i, String.valueOf(a * b));
                        }
                        case "/" -> {
                            expressionList.set(i, String.valueOf(a / b));
                        }
                        case "+" -> {
                            expressionList.set(i, String.valueOf(a + b));
                        }
                        case "-" -> {
                            expressionList.set(i, String.valueOf(a - b));
                        }
                    }

                    // Special order if you switch it will break horribly :(
                    expressionList.remove(i + 1);
                    expressionList.remove(i - 1);

                    i = 0; //Don't ask I am already ashamed
                    System.out.println("List: " + expressionList);
                }
            }
        }
        return expressionList.getFirst();
    }

    public static void main(String[] args) throws Exception {

        String expression = "345.051505+2*-(4--15)+5^7";
        ArrayList<String> expressionList = new ArrayList<String>();

        Character[] operators = {'*','/','+','-', '^'};

        int lasthit = 0;
        boolean doubleMinusFlag = false;
        char lastCharacter = ' ';

        for(int i = 0; i < expression.length(); i++){

            char c = expression.charAt(i);

            System.out.println(i);
            System.out.println(c+"_"+lastCharacter);

            if ((Arrays.asList(operators).contains(c)) || (c == '(') || (c == ')')){
                System.out.println("Debug print 1: "+c);
                if (lastCharacter == c){

                    if (c == '-' && !doubleMinusFlag) {
                        doubleMinusFlag = true;

                    } else if (c == '-'){
                        throw new Exception("U dumb fuck -");

                    } else {
                        throw new Exception("U dumb fuck");
                    }

                    //lasthit = i;

                } else {

                    if (lasthit != i) {
                        expressionList.add(expression.substring(lasthit, i));
                    }
                    expressionList.add(String.valueOf(c));
                    lasthit = i+1;
                    doubleMinusFlag = false;

                }

                System.out.println("List: " + expressionList);

            }

            lastCharacter = c;

        }

        // Add remaining value to list if any left over
        if (lasthit < expression.length()) {
            expressionList.add(expression.substring(lasthit));
        }

        System.out.println("\nResulting List Before Check: "+expressionList);

        // Do a pass of the resulting list as there can be - - beside each other.
        boolean minusFlag = false;
        for (int i = 0; i < expressionList.size(); i++) {
            String item = expressionList.get(i);
            // Compare with null checking - Good practice
            if (Objects.equals(item, "-")) {
                if (minusFlag) {
                    // remove the double minus as it cancels out
                    expressionList.remove(i-1);
                    expressionList.remove(i);
                    minusFlag = false;
                } else {
                    minusFlag = true;
                }
            } else if (!Arrays.asList("+", "-", "*", "/", "^", ")").contains(item) && minusFlag && !item.contains("-")) {
                expressionList.set(i,"-"+expressionList.get(i));
                expressionList.remove(i - 1);
                minusFlag = false;
            }else {
                minusFlag = false;
            }
        }

        System.out.println("\nResulting List After Check: "+expressionList);


        int startIndex;
        int endIndex = expressionList.size();
        ArrayList<String> workingList;

        for(int i = 0; i < expressionList.size(); i++) {
            if (expressionList.get(i).equals("(") || expressionList.get(i).equals("-(")){
                startIndex = i;
                for(int j = i; j < expressionList.size(); j++) {
                    if (expressionList.get(j).equals(")")){
                        endIndex = j;
                    } else if (expressionList.get(j).equals("(") || expressionList.get(j).equals("-(")) {
                        startIndex = j;
                    }
                }

                workingList = new ArrayList<>(expressionList.subList(startIndex+1, endIndex));
                System.out.println("Sublist"+workingList);
                String response = mathSolver(workingList);
                expressionList.add(endIndex+1, response);
                expressionList.subList(startIndex, endIndex+1).clear();

                i = 0;
            }
        }


        System.out.println("\nResulting List: "+expressionList);

    }
}
