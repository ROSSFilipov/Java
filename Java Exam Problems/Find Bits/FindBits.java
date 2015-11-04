import java.util.Collections;
import java.util.Scanner;

/**
 * Created by user on 11/4/2015.
 */
public class FindBits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initialNumber = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        String binaryS = ConvertToBinaryString(initialNumber, 5);
        int allOccurances = 0;
        for (int i = 0; i < n; i++) {
            String binaryNumber = ConvertToBinaryString(Integer.parseInt(scanner.nextLine()), 29);
            int currentOccurances = FindOccurances(binaryNumber, binaryS);
            allOccurances += currentOccurances;
        }

        System.out.println(allOccurances);
    }

    private static int FindOccurances(String binaryNumber, String initialNumber) {
        int occurancesCounter = 0;
        for (int i = 0; i < binaryNumber.length() - 5; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 5; j++){
                sb.append(binaryNumber.charAt(binaryNumber.length() - 1 - i - j));
            }
            sb.reverse();
            if (sb.toString().equals(initialNumber)){
                occurancesCounter++;
            }
        }

        return occurancesCounter;
    }

    private static String ConvertToBinaryString(int initialNumber, int numberOfBits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfBits; i++) {
            sb.append(((initialNumber >> i) & 1));
        }

        sb.reverse();
        return sb.toString();
    }
}
