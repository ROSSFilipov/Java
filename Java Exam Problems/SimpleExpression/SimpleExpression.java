import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by user on 10/23/2015.
 */
public class SimpleExpression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> input = TakeInput(scanner);

        String operator = "";
        int iterations = input.size();
        Stack<String> currentStack = new Stack<>();
        for (int i = 0; i < iterations; i++) {
            if (input.get(i).equals("+") || input.get(i).equals("-")){
                operator = input.get(i);
            } else {
                currentStack.push(input.get(i));
                if (currentStack.size() == 2) {
                    BigDecimal num2 = new BigDecimal(currentStack.pop());
                    BigDecimal num1 = new BigDecimal(currentStack.pop());
                    BigDecimal result = CalculateExpression(operator, num1, num2);
                    currentStack.push(result.toPlainString());
                }
            }
        }

        BigDecimal result = new BigDecimal(currentStack.pop());

        System.out.println(result);
    }

    private static ArrayList<String> TakeInput(Scanner scanner) {
        String input = scanner.nextLine();
        ArrayList<String> result = new ArrayList<>();

        int i = 0;
        while (i < input.length()){
            StringBuilder sb = new StringBuilder();
            if (input.charAt(i) == ' '){
                i++;
            } else if (input.charAt(i) == '+' || input.charAt(i) == '-'){
                sb.append(input.charAt(i));
                result.add(sb.toString());
                i++;
            } else {
                while (i < input.length() && input.charAt(i) != ' ' && input.charAt(i) != '+' && input.charAt(i) != '-'){
                    sb.append(input.charAt(i));
                    i++;
                }
                result.add(sb.toString());
            }
        }

        return result;
    }

    private static BigDecimal CalculateExpression(String operator, BigDecimal num1, BigDecimal num2) {
        BigDecimal result = num1;
        switch (operator){
            case "+": result = result.add(num2);
                break;
            case "-": result = result.subtract(num2);
        }

        return result;
    }
}
