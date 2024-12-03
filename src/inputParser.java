import java.util.ArrayList;
import java.util.List;

public class inputParser {

    public static ArrayList<String> parseFromString(String expression, List<String> operators, String specialChars) throws Exception {

        ArrayList<String> expressionList = new ArrayList<String>();
        int lasthit = 0;
        boolean doubleMinusFlag = false;
        String lastCharacter = "";
        int openBracketCount = 0;

        // some cleanup - set all to lowercase and remove whitespaces
        expression = expression.toLowerCase();
        expression = expression.replaceAll("\\s+","");

        System.out.println("\nLet's solve the following: "+expression);


        for(int i = 0; i < expression.length(); i++){

            String c = String.valueOf(expression.charAt(i));

            if (operators.contains(c) || c.equals("(") || c.equals(")")){

                // Handle Bracket Count outside other logic
                // Has to be first as other logic depends on it
                if (c.equals("(")) {
                    openBracketCount++;
                    System.out.println(i+" openBracketCount: "+openBracketCount);
                } else if (i == 0 && c.equals(")")) {
                    throw new PrettyException("It looks like you have an closing bracket before any number", expression, i);
                } else if (c.equals(")")) {
                    if (openBracketCount == 0) {
                        throw new PrettyException("It looks like you have a closing Bracket before a opening one", expression, i);
                    } else {
                        openBracketCount--;
                        System.out.println(i + " openBracketCount: " + openBracketCount);
                    }
                }

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
                    } else if (c.equals(")") || c.equals("(")) {
                        doubleMinusFlag = false;
                        expressionList.add(c);

                    // Throw error we have double operator
                    } else {
                        throw new PrettyException("It looks like you have identical operators "+c+" and "+c+" beside each other", expression, i);
                    }

                    lasthit = i+1;

                } else {

                    if (!c.equals("(") && !c.equals(")") && !lastCharacter.equals("-") && operators.contains(lastCharacter)) {
                        throw new PrettyException("It looks like you have operators "+lastCharacter+" and "+c+" beside each other", expression, i);
                    } else if (i+1 == expression.length() && !c.equals(")")) {
                        throw new PrettyException("It looks like you have a operator "+c+" left without any number after it", expression, i);
                    }

                    if (lasthit != i) { expressionList.add(expression.substring(lasthit, i)); }
                    expressionList.add(c);
                    lasthit = i+1;
                    doubleMinusFlag = false;

                }
                //System.out.println("List: " + expressionList);

            } else if (!c.matches("\\d") && !specialChars.contains(c)) {
                throw new PrettyException("It looks like you have a invalid Symbol "+c+" wich isn't a operator or a number", expression, i);
            }
            lastCharacter = c;
        }

        if (openBracketCount != 0) {
            throw new PrettyException("It looks like you have "+openBracketCount+" open bracket(s) and no closing ones left", expression, expression.length()-1);
        }

        // Add remaining value to list if any left over
        if (lasthit < expression.length()) {
            expressionList.add(expression.substring(lasthit));
        }

        System.out.println("\nWe converted the expression to the following list that we will now work with\nThis is what we have now: "+expressionList);
        return expressionList;
    }

}
