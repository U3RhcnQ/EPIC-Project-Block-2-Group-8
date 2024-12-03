import java.util.ArrayList;
import java.util.List;

public class inputParser {

    public static ArrayList<String> parseFromString(String expression, List<List<String>> operatorsList) throws Exception {

        ArrayList<String> expressionList = new ArrayList<String>();
        int lasthit = 0;
        boolean doubleMinusFlag = false;
        String lastCharacter = "";
        ArrayList<String> operators = new ArrayList<>();
        ArrayList<String> initialExpressionList = expressionList;

        // some cleanup - set all to lowercase and remove whitespaces
        expression = expression.toLowerCase();
        expression = expression.replaceAll("\\s+","");

        System.out.println("\nLet's solve the following: "+expression);

        for (List<String> innerOperatorsList : operatorsList) {
            operators.addAll(innerOperatorsList);
        }

        for(int i = 0; i < expression.length(); i++){

            String c = String.valueOf(expression.charAt(i));

            if (operators.contains(c) || c.equals("(") || c.equals(")")){

                // Check if the previous character was the same
                if (lastCharacter.equals(c)){

                    // Set double minus flag if only one minus beforehand
                    if (c.equals("-") && !doubleMinusFlag) {
                        doubleMinusFlag = true;
                        expressionList.add(c);

                    // We have more than 2 minuses in a row now so we have to throw an error here
                    } else if (c.equals("-")){
                        throw new PrettyException("It looks like you have too many minuses beside each other", expression, i);

                    // We have brackets here so we can reset the minus flag
                    } else if (c.equals(")") || c.equals("(")){
                        doubleMinusFlag = false;
                        expressionList.add(c);

                    // Throw error we hav double operator
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
                //System.out.println("List: " + expressionList);
            }
            lastCharacter = c;
        }

        // Add remaining value to list if any left over
        if (lasthit < expression.length()) {
            expressionList.add(expression.substring(lasthit));
        }

        System.out.println("\nWe converted the expression to the following list that we will now work with\nThis is what we have now: "+expressionList);
        return expressionList;
    }

}
