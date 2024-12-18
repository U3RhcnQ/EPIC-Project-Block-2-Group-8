import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator extends Calculator {

    public ArithmeticCalculator(String expression) {
        super(expression);
    }

    // Throws Exception which we handle up the stack
    @Override
    void parse() throws Exception {

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

           } else if (i < 2 && !operators.contains(item) && !item.equals(")") && minusFlag && ( lastOperator || i == 1)) {

               // Set the number to be itself + (-)
               expressionList.set(i,"-"+expressionList.get(i));
               // Remove the previous (-) as we added it to the number
               expressionList.remove(i - 1);
               minusFlag = false;
               lastOperator = false;

           }else if (!operators.contains(item) && !item.equals(")") && minusFlag && !expressionList.get(i-2).equals(")") && ( lastOperator || i == 1)) {

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

       // Handle + edge Case
       if (expressionList.getFirst().equals("+")) {
           expressionList.removeFirst();
       }

       System.out.printf("%nWe now check the list for any double minuses and other small things %nResulting list after the check:  %s%n", expressionList);
       System.out.println("\nLet's start solving it now");
       setExpressionList(expressionList);
   }

   private String mathSolver() throws Exception {

       // Initialise variables
       ArrayList<String> expressionList = getExpressionList();
       int startIndex;
       int endIndex = expressionList.size();
       ArrayList<String> workingList;

       // Iterate over the expressionList
       for(int i = 0; i < expressionList.size(); i++) {

           // We will handle brackets here
           // If we encounter an open bracket we will step into the expression list until we encounter a Closing bracket,
           // or we encounter another open bracket in which case we update the starting index and repeat until all open brackets have a closed bracket.
           // Starting to the end point will be passed to the maths solver (Solving the contents of the bracket) and will push the result back into the list
           // We will always solve the inner brackets first

           // Look for open brackets
           if (expressionList.get(i).equals("(") || expressionList.get(i).equals("-(")){
               // Set initial Starting point
               startIndex = i;

               // Now we look for a closing bracket or ar another opening one
               for(int j = i; j < expressionList.size(); j++) {
                   if (expressionList.get(j).equals(")")){

                       // We found a closing bracket so we can now end the iteration here for this set of brackets.
                       endIndex = j;
                       break;

                   } else if (expressionList.get(j).equals("(") || expressionList.get(j).equals("-(")) {

                       // We found another open bracket so we will use it first always solving the inner set of brackets first
                       startIndex = j;
                   }
               }

               System.out.printf("%nCurrent list: %s%n", expressionList);
               // Temporary store for contents of brackets without "(" or ")"
               workingList = new ArrayList<>(expressionList.subList(startIndex+1, endIndex));

               // Solve the maths expression inside
               System.out.printf("We found brackets so we will solve those first: %nThe Contents of the brackets: %s%n", workingList);
               String response = finalMathSolver(workingList);

               System.out.printf("There is no more operations left so we are done! %nThis is the solution of the expression in the brackets: %s%n", response);

               // we handle a "-(" here basically assume it's multiplied by -1
               if (expressionList.get(startIndex).equals("-(")){
                   response = String.valueOf(Double.parseDouble(response) * -1);
               }

               // We add the result after the closing bracket
               expressionList.add(endIndex+1, response);
               // We delete the brackets that we just solved
               expressionList.subList(startIndex, endIndex+1).clear();
               // Update the Global expressionList
               setExpressionList(expressionList);

               // Move back iterator by 1 to account for removed bracket
               i--;
           }
       }

       System.out.println("\nWe are Done with all the brackets so we can start solving the Final Expression");
       // Update the Global expressionList
       setExpressionList(expressionList);
       return finalMathSolver(expressionList);
   }

   @Override
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
