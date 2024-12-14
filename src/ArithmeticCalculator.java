import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator extends Calculator {

    public ArithmeticCalculator(String expression) {
        super(expression);
    }

    // Throws Exception which we handle up the stack
    private void parse() throws Exception {

        // Send the raw input to the parser with order of operations and no special chars
        setExpressionList(InputParser.parseFromString(getExpression(), getSimpleOrderOfOperations() , ""));

    }

    // Method to validate the initial parse
   private void validate() throws Exception {

       ArrayList<String> expressionList = getExpressionList();
       List<String> operators = getSimpleOrderOfOperations();

       // Do a pass of the resulting list as there can be - - beside each other.
       // Flags for minus and Operators we use those to handle cases
       boolean minusFlag = false;
       boolean lastOperator = false;

       // Go through the whole expression list
       for (int i = 0; i < expressionList.size(); i++) {
           // Get Item to check
           String item = expressionList.get(i);

           // Check if Item is a minus
           if (item.equals("-")) {

               if (minusFlag) {
                   // remove the first minus in the double minus as it cancels out
                   expressionList.remove(i);
                   // Set the first minus to be a +
                   expressionList.set(i-1,"+");
                   minusFlag = false;
                   i--; // decrement counter by 1 as we removed an element

               } else {
                   minusFlag = true;
               }

           // Here we check for any single numbers with a minus in front, and we convert it into a negative number where needed
           // eg: +,-,2 equals: +,-2

           } else if (!operators.contains(item) && !item.equals(")") && minusFlag && !expressionList.get(i-2).equals(")") && ( lastOperator || i == 1)) {

               // Set the number to be itself + (-)
               expressionList.set(i,"-"+expressionList.get(i));
               // Remove the previous (-) as we added it to the number
               expressionList.remove(i - 1);
               minusFlag = false;
               lastOperator = false;

           } else if (operators.contains(item) || item.equals(")") || item.equals("(")){

               lastOperator = true;
               minusFlag = false;

           } else {

               lastOperator = false;
               minusFlag = false;

           }
       }

       System.out.printf("%nWe now check the list for any double minuses and other small things %nResulting list after the check:  %s%n", expressionList);
       System.out.println("\nLet's start solving it now");
       setExpressionList(expressionList);
   }

   private String mathSolver() throws Exception {

       ArrayList<String> expressionList = getExpressionList();
       int startIndex;
       int endIndex = expressionList.size();
       ArrayList<String> workingList;

       for(int i = 0; i < expressionList.size(); i++) {

           if (expressionList.get(i).equals("(") || expressionList.get(i).equals("-(")){
               startIndex = i;
               for(int j = i; j < expressionList.size(); j++) {
                   if (expressionList.get(j).equals(")")){
                       endIndex = j;
                       break;
                   } else if (expressionList.get(j).equals("(") || expressionList.get(j).equals("-(")) {
                       startIndex = j;
                   }
               }

               //System.out.println("i:"+i+"start:"+startIndex+"endIndex:"+endIndex);
               System.out.printf("%nCurrent list: %s%n", expressionList);
               workingList = new ArrayList<>(expressionList.subList(startIndex+1, endIndex));

               System.out.printf("We found brackets so we will solve those first: %nThe Contents of the brackets: %s%n", workingList);
               String response = finalMathSolver(workingList);
               System.out.printf("There is no more operations left so we are done! %nThis is the solution of the expression in the brackets: %s%n", response);
               if (expressionList.get(startIndex).equals("-(")){
                   response = String.valueOf(Double.parseDouble(response) * -1);
               }
               expressionList.add(endIndex+1, response);
               expressionList.subList(startIndex, endIndex+1).clear();
               setExpressionList(expressionList);

               i = 0;
           }
       }

       System.out.println("\nWe are Done with all the brackets so we can start solving the Final Expression");
       setExpressionList(expressionList);
       return finalMathSolver(expressionList);
   }

   public String solve() throws Exception{

       // This is the full solver that we can call externally
       // First we parse the input
       parse();
       // Then we validate the input
       validate();
       // Finally we solve the expression and return
       return(mathSolver());
   }
}
