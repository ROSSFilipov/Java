import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 10/20/2015.
 */
public class Pyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int index = 0;
        ArrayList<Integer> allNumbers = new ArrayList<>();

        int previousNumber = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {

            List<Integer> currentNumbers = Arrays.asList(scanner.nextLine().split("\\s+"))
                    .stream()
                    .filter(s -> !s.equals(""))
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());

            Collections.sort(currentNumbers);

            int nextNumber = TakeNextNumber(currentNumbers, previousNumber);

            if (nextNumber == previousNumber){
                previousNumber++;
            } else {
                previousNumber = nextNumber;
                allNumbers.add(nextNumber);
            }
        }

        int size = allNumbers.size();
        for (int i = 0; i < size; i++) {
            System.out.print(allNumbers.get(i));
            if (i != size - 1){
                System.out.print(", ");
            }
        }
    }

    private static int TakeNextNumber(List<Integer> currentNumbers, int previousNumber) {
        int nextNumber = previousNumber;
        for (int i = 0; i < currentNumbers.size(); i++) {
            if (currentNumbers.get(i) > previousNumber){
                nextNumber = currentNumbers.get(i);
                break;
            }
        }

        return nextNumber;
    }
}
