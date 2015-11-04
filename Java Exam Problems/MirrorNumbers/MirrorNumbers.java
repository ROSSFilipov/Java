import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.setOut;

/**
 * Created by user on 10/27/2015.
 */
public class MirrorNumbers {
    public static void main(String[] args){
        Scanner scanner = new Scanner(in);

        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        boolean mirrorNumberFound = false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (AreMirrorNumbers(numbers[i], numbers[j])){
                    System.out.printf("%d<!>%d\n", numbers[i], numbers[j]);
                    mirrorNumberFound = true;
                }
            }
        }

        if (!mirrorNumberFound){
            System.out.println("No");
        }
    }

    private static boolean AreMirrorNumbers(Integer numberOne, Integer numberTwo) {
        String one = numberOne.toString();
        String two = numberTwo.toString();

        int iterations = one.length();
        if (one.length() != two.length()){
            return false;
        } else {
            for (int i = 0; i < iterations; i++) {
                if (one.charAt(i) != two.charAt(iterations - 1 - i)){
                    return false;
                }
            }

            return true;
        }
    }
}
