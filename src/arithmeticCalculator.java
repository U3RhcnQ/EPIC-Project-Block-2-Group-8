import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class arithmeticCalculator extends Calculator {

    public arithmeticCalculator(String expression) throws Exception{
        super(expression);
    }

    public ArrayList<String> parse() throws Exception{
        return inputParser.parseFromString(getExpression(), getOrderOfOperations());
    }

   public ArrayList<String> validate(ArrayList<String> expressionList, List<List<String>> operatorsList) throws Exception{

       // Do a pass of the resulting list as there can be - - beside each other.
       boolean minusFlag = false;
       boolean lastOperator = false;
       ArrayList<String> operators = new ArrayList<>();

       for (List<String> innerOperatorsList : operatorsList) {
           operators.addAll(innerOperatorsList);
       }

       for (int i = 0; i < expressionList.size(); i++) {
           String item = expressionList.get(i);
           // Compare with null checking - Good practice
           if (Objects.equals(item, "-")) {

               if (minusFlag) {
                   // remove the double minus as it cancels out
                   expressionList.remove(i);
                   expressionList.set(i-1,"+");
                   minusFlag = false;
               } else {
                   minusFlag = true;
               }

           } else if (!operators.contains(item) && !item.equals(")") && minusFlag && !item.contains("-") && lastOperator) {

               expressionList.set(i,"-"+expressionList.get(i));
               expressionList.remove(i - 1);
               minusFlag = false;
               lastOperator = false;

           }else if (operators.contains(item) || item.equals(")")){

               lastOperator = true;
               minusFlag = false;

           } else {

               lastOperator = false;
               minusFlag = false;

           }
       }
       System.out.println("\nResulting List After Check: "+expressionList);
       return expressionList;
   }

   public String mathSolver(ArrayList<String> expressionList) {

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

               System.out.println("i:"+i+"start:"+startIndex+"endIndex:"+endIndex);
               System.out.println("Main list: "+expressionList);
               workingList = new ArrayList<>(expressionList.subList(startIndex+1, endIndex));

               System.out.println("Sublist"+workingList);
               String response = finalMathSolver(workingList);
               if (expressionList.get(startIndex).equals("-(")){
                   response = String.valueOf(Double.parseDouble(response) * -1);
               }
               expressionList.add(endIndex+1, response);
               expressionList.subList(startIndex, endIndex+1).clear();

               i = 0;
           }
       }

       String result = finalMathSolver(expressionList);
       System.out.println("\nResulting List: "+result);
       return result;
   }

   public String solve() throws Exception{
       ArrayList<String> workingList;
       workingList = parse();
       workingList = validate(workingList, getOrderOfOperations());
       return(mathSolver(workingList));
   }


}
