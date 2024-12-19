import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static ArrayList<String> parseFromString(String expression, List<String> operators, String specialChars) throws Exception {

        // Initialise variables
        ArrayList<String> expressionList = new ArrayList<>();
        int lastHit = 0;
        String lastCharacter = "";
        int openBracketCount = 0;
        // Booleans for logic and error handling
        boolean doubleMinusFlag = false;
        boolean hasDecimalPoint = false;


        // some cleanup - set all to lowercase and remove whitespaces
        expression = expression.toLowerCase();
        expression = expression.replaceAll("\\s+","");

        System.out.printf("%nLet's solve the following: %s%n", expression);


        // loop through the whole expression
        for(int i = 0; i < expression.length(); i++){

            String c = String.valueOf(expression.charAt(i));

            if (operators.contains(c) || c.equals("(") || c.equals(")")){

                // Handle Bracket Count outside other logic
                // Has to be first as other logic depends on it
                if (c.equals("(") && lastCharacter.equals(")")) {
                    throw new PrettyException("It looks like you have two sets of brackets with no operator between each other", expression, i);

                } else if (c.equals("(") && !operators.contains(lastCharacter) && !lastCharacter.equals("(")) {
                    throw new PrettyException("It looks like you have a number and a open bracket without a operator between", expression, i);

                }else if (i == 0 && c.equals(")")) {
                    throw new PrettyException("It looks like you have an closing bracket before any number", expression, i);

                } else if (c.equals(")")) {

                    if (openBracketCount == 0) {
                        throw new PrettyException("It looks like you have a closing Bracket before a opening one", expression, i);
                    } else {
                        openBracketCount--;
                        System.out.println("Open Bracket -1: "+ openBracketCount);
                    }

                } else if (c.equals("(")) {
                    openBracketCount++;
                    System.out.println("Open Bracket +1: "+ openBracketCount);

                } else if (i == 0 && !c.equals("-") && !c.equals("x") && !c.equals("y") && !c.equals("z")) {
                    throw new PrettyException("It looks like you have a operator "+c+" at the start of your expression with no number before it", expression, i);
                }

                if (i > 1 && c.equals("(")){

                    if (!operators.contains(lastCharacter) && !lastCharacter.equals("(") && !lastCharacter.equals("-(")) {
                        throw new PrettyException("It looks like you have a bracket and a number beside each other with no operator", expression, i);
                    }
                    }

                // Check if the previous character was the same
                if (lastCharacter.equals(c)) {

                    // Set double minus flag if only one minus beforehand
                    if (c.equals("-") && !doubleMinusFlag) {
                        doubleMinusFlag = true;
                        expressionList.add(c);

                        // We have more than 2 minuses in a row now so we have to throw an error here
                    } else if (c.equals("-")) {
                        throw new PrettyException("It looks like you have too many minuses beside each other", expression, i);

                        // We have brackets here so we can reset the minus flag
                    } else if (c.equals(")") || c.equals("(")) {
                        doubleMinusFlag = false;
                        expressionList.add(c);

                        // Throw error we have double operator
                    } else {
                        throw new PrettyException("It looks like you have identical operators " + c + " and " + c + " beside each other", expression, i);

                    }

                    if (i == 1 && doubleMinusFlag) {
                        throw new PrettyException("It looks like you have double - at the start of the expression", expression, i);

                    } else if (i > 1 && c.equals("-")) {

                        if (operators.contains(expressionList.get(i-2)) || expressionList.get(i-2).equals("(") || expressionList.get(i-2).equals(")") || expressionList.get(i-2).equals("-(")) {
                            throw new PrettyException("It looks like you have a double minus after another operator "+c, expression, i);
                        }
                    }
                    lastHit = i+1;

                } else {

                    if (!c.equals("(") && !c.equals(")") && !c.equals("-") && operators.contains(lastCharacter) && !(lastCharacter.equals("*") || lastCharacter.equals("^")) && !( (lastCharacter.equals("z") || lastCharacter.equals("x") || lastCharacter.equals("y")) && c.equals("="))) {
                        throw new PrettyException("It looks like you have operators "+lastCharacter+" and "+c+" beside each other", expression, i);

                    } else if (i+1 == expression.length() && !c.equals(")")) {
                        throw new PrettyException("It looks like you have a operator "+c+" left without any number after it", expression, i);

                    }

                    if (lastHit != i) {
                        expressionList.add(expression.substring(lastHit, i));

                    }

                    expressionList.add(c);
                    lastHit = i+1;
                    doubleMinusFlag = false;

                }

                hasDecimalPoint = false;


            } else if (!c.matches("\\d") && !specialChars.contains(c) && !c.equals(".")) {
                throw new PrettyException("It looks like you have a invalid Symbol "+c+" which isn't a operator or a number", expression, i);

            } else if (c.equals(".") && lastCharacter.equals(".")) {
                throw new PrettyException("It looks like you have 2 decimal points beside another", expression, i);

            } else if (c.equals(".") && hasDecimalPoint) {
                throw new PrettyException("It looks like you have multiple decimal points in the same number", expression, i);

            } else if (c.equals(".")) {
                hasDecimalPoint = true;

            }
            lastCharacter = c;

        }

        if (openBracketCount != 0) {
            throw new PrettyException("It looks like you have "+openBracketCount+" open bracket(s) and no closing ones left", expression, expression.length()-1);

        }

        // Add remaining value to list if any left over
        if (lastHit < expression.length()) {
            expressionList.add(expression.substring(lastHit));
        }

        System.out.printf("%nWe converted the expression to the following list that we will now work with %nThis is what we have now: %s%n", expressionList);
        return expressionList;
    }

}
