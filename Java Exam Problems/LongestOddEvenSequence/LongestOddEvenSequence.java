import java.util.Scanner;

/**
 * Created by user on 10/18/2015.
 */
public class LongestOddEvenSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().trim().split("[)(\\s]+");
        int size = input.length;
        int[] numbers = new int[size - 1];

        for (int i = 0; i < size - 1; i++) {
            numbers[i] = Integer.parseInt(input[i + 1]);
        }

        GetLongestOddEvenSequence(numbers, size - 1);
    }

    private static void GetLongestOddEvenSequence(int[] numbers, int size) {
        int maxLength = 0;

        int tempLength = 1;
        for (int i = 0; i < size - 1; i++) {
            if ((numbers[i] % 2 == 0 || numbers[i] == 0) && (numbers[i + 1] % 2 != 0 || numbers[i + 1] == 0)) {
                tempLength++;
            }
            else if ((numbers[i] % 2 != 0 || numbers[i] == 0) && (numbers[i + 1] % 2 == 0 || numbers[i + 1] == 0)) {
                tempLength++;
            }
            else {
                if (tempLength > maxLength) {
                    maxLength = tempLength;
                    tempLength = 1;
                }
                else {
                    tempLength = 1;
                }
            }
        }

        if (tempLength > maxLength) {
            maxLength = tempLength;
        }

        System.out.println(maxLength);
    }
}
