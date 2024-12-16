import java.util.ArrayList;
import java.util.List;

public class Differenciate {
    static String equation = "2x^2+6x-1";


    public static void main(String[] args) throws Exception {
        //List<String> operations = List.of("^","*","/","+","-");
        //System.out.println(InputParser.parseFromString(equation,operations,""));
        ArrayList<String> elementList = new ArrayList<String>();

        while (equation.contains("x")) {
            int index = equation.indexOf("x");

            if (equation.substring(index+1, index+2).equals("^")) {
                elementList.add(equation.substring(0, index+3));
                equation = equation.substring(index+3);
            }else{
                elementList.add(equation.substring(0, index+1));
                equation = equation.substring(index+1);
            }
        }

        equation = "";
        System.out.println(elementList);
        for (int index = 0; index<elementList.size(); index++){
            equation = equation + differenciate(elementList.get(index));
        }

        System.out.println(equation);

    }

    private static String differenciate(String expression){
        if (expression.contains("^")) {
            int index = expression.indexOf("^");
            int number = Integer.parseInt(expression.substring(0, index-1));
            int power = Integer.parseInt(expression.substring(expression.length()-1));
            number *= power;
            power--;
            if (power == 1) {
                expression = String.valueOf(number) + "x";
            }else{
                expression = String.valueOf(number) + expression.substring(index-1, expression.length()-1) + String.valueOf(power);
            }
        }else{
            expression = expression.substring(0,expression.length()-1);
        }
        return expression;
    }

}
