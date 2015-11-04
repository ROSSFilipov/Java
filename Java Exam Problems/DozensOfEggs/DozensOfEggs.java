import java.util.Scanner;

/**
 * Created by user on 10/18/2015.
 */
public class DozensOfEggs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dozensCount = 0;
        int eggsCount = 0;

        for (int i = 0; i < 7; i++) {
            String[] currentLine = scanner.nextLine().split("[\\s]+");
            int quantity = Integer.parseInt(currentLine[0]);
            String type = currentLine[1];

            switch (type)
            {
                case "dozens": dozensCount += quantity;
                    break;
                case "eggs": eggsCount += quantity;
            }
        }

        while (eggsCount >= 12)
        {
            eggsCount -= 12;
            dozensCount++;
        }

        System.out.printf("%d dozens + %d eggs", dozensCount, eggsCount);
    }
}
