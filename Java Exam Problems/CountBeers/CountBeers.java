import java.util.Scanner;

/**
 * Created by user on 10/18/2015.
 */
public class CountBeers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stacksCount = 0;
        int beersCount = 0;

        while (!scanner.hasNext("End"))
        {
            String[] currentLine = scanner.nextLine().split("[\\s]+");
            int quantity = Integer.parseInt(currentLine[0]);
            String type = currentLine[1];

            switch (type)
            {
                case "stacks": stacksCount += quantity;
                    break;
                case "beers": beersCount += quantity;
            }
        }

        while (beersCount >= 20)
        {
            beersCount -= 20;
            stacksCount++;
        }

        System.out.printf("%d stacks + %d beers", stacksCount, beersCount);
    }
}
