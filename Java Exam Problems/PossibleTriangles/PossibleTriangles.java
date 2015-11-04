import java.util.Collections;
import java.util.Scanner;

/**
 * Created by user on 10/27/2015.
 */
public class PossibleTriangles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean trianglesFound = false;
        while (!scanner.hasNext("End")){
            Double[] numbers = new Double[3];
            numbers[0] = scanner.nextDouble();
            numbers[1] = scanner.nextDouble();
            numbers[2] = scanner.nextDouble();
            SelectionSort(numbers);

            if (numbers[0] + numbers[1] > numbers[2]){
                System.out.printf("%.2f+%.2f>%.2f\n", numbers[0], numbers[1], numbers[2]);
                trianglesFound = true;
            }
        }

        if (!trianglesFound){
            System.out.println("No");
        }
    }

    private static void SelectionSort(Double[] numbers) {
        for (int i = 0; i < 2; i++){
            int index = i;
            for (int j = i + 1; j < 3; j++) {
                if (numbers[i] > numbers[j] && numbers[index] > numbers[j]){
                    index = j;
                }
            }

            if (index != i){
                Double temp = numbers[i];
                numbers[i] = numbers[index];
                numbers[index] = temp;
            }
        }
    }
}
