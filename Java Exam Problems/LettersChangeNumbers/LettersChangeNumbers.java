import java.util.Scanner;

/**
 * Created by user on 10/31/2015.
 */
public class LettersChangeNumbers {
    private static double finalSum = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        for (int i = 0; i < input.length; i++) {
            int currentNumber = Integer.parseInt(input[i].substring(1, input[i].length() - 1));
            ManipulateCurrentNumber(currentNumber, input[i]);
        }

        System.out.printf("%.2f", finalSum);
    }

    private static void ManipulateCurrentNumber(int currentNumber, String currentString) {
        double tempSum = currentNumber;
        Character backLetter = currentString.charAt(0);
        Character frontLetter = currentString.charAt(currentString.length() - 1);
        if (Character.isUpperCase(backLetter)){
            tempSum /= (backLetter - 64);
        } else {
            tempSum *= (backLetter - 96);
        }

        if (Character.isUpperCase(frontLetter)){
            tempSum -= (frontLetter - 64);
        } else {
            tempSum += (frontLetter - 96);
        }

        finalSum += tempSum;
    }
}
