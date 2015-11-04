import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by user on 10/25/2015.
 */
public class Biggest3PrimeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.asList(scanner.nextLine().split("[)(\\s]+"))
                .stream()
                .filter(s -> !s.equals(""))
                .map(s -> Integer.parseInt(s))
                .filter(x -> IsPrime(x) && x > 1)
                .distinct()
                .sorted(Comparator.<Integer>reverseOrder())
                .limit(3)
                .collect(Collectors.toList());

        if (numbers.size() < 3) {
            System.out.println("No");
        } else {
            long sum = 0;
            for (Integer number : numbers) {
                sum += number;
            }

            System.out.println(sum);
        }
    }

    private static boolean IsPrime(Integer integer) {

        for (int i = 2; i <= Math.sqrt(integer); i++) {
            if (integer % i == 0){
                return false;
            }
        }

        return true;
    }
}
