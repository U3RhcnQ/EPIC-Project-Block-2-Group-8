import java.util.ArrayList;
import java.util.List;

public class Differentiate extends Calculator{
    private static String inputEquation;
    List<String> orderOfOperations = List.of("*","/","+","-","(",")");

    public Differentiate(String expression) {
        super(expression);
        this.inputEquation = expression;
    }

    @Override
    public void parse() throws Exception {
        setExpressionList(InputParser.parseFromString(inputEquation, orderOfOperations, "x+-*/^="));
    }

    @Override
    public String solve() throws Exception {
        parse();
        ArrayList<String> elementList = getExpressionList();

        String equation = "";
        for (int index = 0; index<elementList.size(); index++){
            String item = elementList.get(index);
            if (orderOfOperations.contains(item)) {
                equation = equation + item;
                if (item.equals("(") || item.equals(")")){
                    throw new PrettyException("It looks like you hava a bracket that isn't supposed to be there",inputEquation,index);
                }
            } else {
                equation = equation + differentiate(item);
            }
        }
        System.out.println();
        System.out.printf("This leaves us with the equation: %s%n",equation);

        char lastChar = equation.charAt(equation.length()-1);
        if (!Character.isDigit(lastChar) && !String.valueOf(lastChar).equals("x")){
            System.out.println();
            System.out.println("It looks like there is an extra operand");
            equation = equation.substring(0,equation.length()-1);
            System.out.printf("After removing this operand we are left with: %s%n",equation);
        }
        return equation;
    }

    private String differentiate(String expression){
        if (expression.contains("^")) {
            int index = expression.indexOf("^");
            int number = Integer.parseInt(expression.substring(0, index-1));
            int power = Integer.parseInt(expression.substring(index+1));
            number *= power;
            power--;
            if (power == 1) {
                expression = String.valueOf(number) + "x";
            }else{
                expression = String.valueOf(number) + expression.substring(index-1, index+1) + String.valueOf(power);
            }
        }else{
            expression = expression.substring(0,expression.length()-1);
        }
        return expression;
    }
}
