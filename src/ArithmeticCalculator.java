import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArithmeticCalculator extends Calculator {

    public ArithmeticCalculator(String expression) {
        super(expression);
    }

    // Throws Exception which we handle up the stack
    public void parse() throws Exception {
        setExpressionList(InputParser.parseFromString(getExpression(), getSimpleOrderOfOperations() , ""));
    }

    // Method to validate the initial parse
   public void validate() throws Exception {

       ArrayList<String> expressionList = getExpressionList();
       List<String> operators = getSimpleOrderOfOperations();

       // Do a pass of the resulting list as there can be - - beside each other.
       boolean minusFlag = false;
       boolean lastOperator = false;

       for (int i = 0; i < expressionList.size(); i++) {
           String item = expressionList.get(i);

           // Compare with null checking - Good practice
           if (Objects.equals(item, "-")) {

               if (minusFlag) {
                   // remove the double minus as it cancels out
                   expressionList.remove(i);
                   i--; // decrement counter by 1 as we removed a element
                   expressionList.set(i-1,"+");
                   minusFlag = false;

               } else {
                   minusFlag = true;
               }

           } else if (!operators.contains(item) && !item.equals(")") && minusFlag && !item.equals("-") && !expressionList.get(i-2).equals(")") && ( lastOperator || i == 1)) {

               expressionList.set(i,"-"+expressionList.get(i));
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

       System.out.println("\nWe now check the list for any double minuses and other small things\nResulting list after the check:  "+expressionList);
       System.out.println("\nLet's start solving it now");
       setExpressionList(expressionList);
   }

   public String mathSolver() throws Exception {

       ArrayList<String> expressionList = getExpressionList();
       int startIndex = 0;
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
               System.out.println("\nCurrent list: "+expressionList);
               workingList = new ArrayList<>(expressionList.subList(startIndex+1, endIndex));

               System.out.println("We found brackets so we will solve those first:\nThe Contents of the brackets: "+workingList);
               String response = finalMathSolver(workingList);
               System.out.println("There is no more operations left so we are done! \nThis is the solution of the expression in the brackets: "+response);
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
       parse();
       validate();
       return(mathSolver());
   }


}
