import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 10/27/2015.
 */
public class ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("[\\s()\\\\/]+");
        Pattern pattern = Pattern.compile("^[a-zA-Z][\\w]{2,25}+$");

        ArrayList<String> validUsernames = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (pattern.matcher(input[i]).find()){
                validUsernames.add(input[i]);
            }
        }

        int index = 0;
        int bestLength = 0;
        for (int i = 0; i < validUsernames.size() - 1; i++) {
            int currentLength = validUsernames.get(i).length() + validUsernames.get(i + 1).length();
            if (currentLength > bestLength){
                index = i;
                bestLength = currentLength;
            }
        }

        System.out.printf("%s\n%s", validUsernames.get(index), validUsernames.get(index + 1));
    }
}
