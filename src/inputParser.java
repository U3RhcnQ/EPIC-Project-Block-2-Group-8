import java.util.ArrayList;
import java.util.List;

public class inputParser {

    private String expression;

    public inputParser(){

    }

    public static ArrayList<String> parseFromString(String expression, List<List<String>> operatorsList) throws Exception {

        ArrayList<String> expressionList = new ArrayList<String>();
        int lasthit = 0;
        boolean doubleMinusFlag = false;
        String lastCharacter = "";
        ArrayList<String> operators = new ArrayList<>();

        // some cleanup - set all to lowercase and remove whitespaces
        expression = expression.toLowerCase();
        expression = expression.replaceAll("\\s+","");

        System.out.println(expression);

        for (List<String> innerOperatorsList : operatorsList) {
            operators.addAll(innerOperatorsList);
        }
        System.out.println(operators);

        for(int i = 0; i < expression.length(); i++){

            String c = String.valueOf(expression.charAt(i));

            if (operators.contains(c) || c.equals("(") || c.equals(")")){
                //System.out.println("Debug print 1: "+c);
                if (lastCharacter.equals(c)){

                    if (c.equals("-") && !doubleMinusFlag) {
                        doubleMinusFlag = true;
                        expressionList.add(c);

                    } else if (c.equals("-")){
                        throw new Exception("U dumb fuck -");

                    } else if (c.equals(")") || c.equals("(")){
                        doubleMinusFlag = false;
                        expressionList.add(c);
                    } else {
                        throw new Exception("U dumb fuck");
                    }

                    lasthit = i+1;

                } else {

                    if (lasthit != i) { expressionList.add(expression.substring(lasthit, i)); }
                    expressionList.add(c);
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

        System.out.println("Resulting List Before Check: "+expressionList);
        return expressionList;
    }

}
