import java.util.Scanner;

/**
 * Created by user on 10/18/2015.
 */
public class AddingAngles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        boolean found = false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = numbers[i] + numbers[j] + numbers[k];
                    if (sum % 360 == 0){
                        System.out.printf("%d + %d + %d = %d degrees\n", numbers[i], numbers[j], numbers[k], sum);
                        found = true;
                    }
                }
            }
        }

        if (!found){
            System.out.println("No");
        }
    }
}
