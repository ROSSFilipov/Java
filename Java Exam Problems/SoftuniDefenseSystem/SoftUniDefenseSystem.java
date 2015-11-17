import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 11/15/2015.
 */
public class SoftUniDefenseSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalQuantity = 0;
        Pattern inputPattern = Pattern.compile("([A-Z][a-z]+).*?([A-Z][a-z]*[A-Z]).*?([0-9]+)L");
        while (scanner.hasNextLine()){
            String currentLine = scanner.nextLine();

            if (currentLine.equals("OK KoftiShans")){
                break;
            }
            Matcher matcher = inputPattern.matcher(currentLine);
            while (matcher.find()){
                String currentGuest = matcher.group(1);
                String alchohol = matcher.group(2);
                int quantity = Integer.parseInt(matcher.group(3));

                totalQuantity += quantity;
                System.out.printf("%s brought %d liters of %s!\n", currentGuest, quantity, alchohol.toLowerCase());
            }
        }

        System.out.printf("%.3f softuni liters", totalQuantity * 0.001);
    }
}
