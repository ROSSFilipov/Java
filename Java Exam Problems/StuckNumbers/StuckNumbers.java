import com.sun.javafx.image.IntPixelGetter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by user on 10/23/2015.
 */
public class StuckNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] input = scanner.nextLine().split("\\s+");

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        boolean found = false;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                if (a != b) {
                    for (int c = 0; c < n; c++) {
                        if (c != a && c != b) {
                            for (int d = 0; d < n; d++) {
                                if (d != a && d != b && d != c){
                                    StringBuilder sb1 = new StringBuilder();
                                    sb1.append(numbers[a]);
                                    sb1.append(numbers[b]);

                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(numbers[c]);
                                    sb2.append(numbers[d]);
                                    if (sb1.toString().equals(sb2.toString())){
                                        found = true;
                                        System.out.printf("%d|%d==%d|%d\n", numbers[a], numbers[b], numbers[c], numbers[d]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!found){
            System.out.println("No");
        }
    }
}
