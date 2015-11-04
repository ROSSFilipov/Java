import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 10/27/2015.
 */
public class WeirdScript {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        char keyChar;
        int keyInteger = n % 52;
        if (keyInteger == 0){
            keyInteger = ((n - 1) % 52) + 1;
        }

        if (keyInteger <= 26){
            keyChar = (char)(keyInteger + 96);
        } else {
            keyChar = (char)(keyInteger + 38);
        }

        String keyString = "" + keyChar + keyChar;
        Pattern pattern = Pattern.compile(keyString + "(.*?)" + keyString);

        StringBuilder sb = new StringBuilder();
        while (!scanner.hasNext("End")){
            String currentLine = scanner.nextLine();
            sb.append(currentLine);
        }

        Matcher matcher = pattern.matcher(sb.toString());
        while (matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
}
