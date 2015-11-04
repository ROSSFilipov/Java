import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by user on 10/25/2015.
 */
public class Enigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String currentLine = scanner.nextLine();
            int m = CalculateM(currentLine);
            char[] newLine = new char[currentLine.length()];
            DecryptCurrentLine(newLine, currentLine, m);

            for (int j = 0; j < newLine.length; j++) {
                System.out.print(newLine[j]);
            }

            System.out.println();
        }
    }

    private static int CalculateM(String currentLine) {
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < currentLine.length(); i++) {
            if (currentLine.charAt(i) != ' ' && !Character.isDigit(currentLine.charAt(i))){
                charList.add(currentLine.charAt(i));
            }
        }

        return charList.size() / 2;
    }

    private static void DecryptCurrentLine(char[] newLine, String currentLine, int m) {
        for (int i = 0; i < newLine.length; i++) {
            if (currentLine.charAt(i) == ' ' || Character.isDigit(currentLine.codePointAt(i))){
                newLine[i] = currentLine.charAt(i);
            } else {
                newLine[i] = (char) (currentLine.charAt(i) + m);
            }
        }
    }
}
