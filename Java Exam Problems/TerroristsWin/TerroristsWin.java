import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 10/20/2015.
 */
public class TerroristsWin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Pattern bombPattern = Pattern.compile("|([a-zA-Z]+)|");
        Matcher matcher = bombPattern.matcher(input);
        char[] array = input.toCharArray();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '|'){
                int startIndex = i;

                String bomb = GetBomb(array, startIndex);

                int bombPower = CalculateBombPower(array, startIndex);

                int lastIndex = startIndex + bomb.length() + 1;

                ExecuteExplosion(array, bomb, startIndex, lastIndex, bombPower);
            }
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }

    }

    private static void ExecuteExplosion(char[] array, String bomb, int startIndex, int lastIndex, int bombPower) {
        int leftSide = startIndex - bombPower;
        int rightSide = startIndex + bomb.length() + 2 + bombPower;

        int arrLength = array.length;
        for (int i = leftSide < 0 ? 0 : leftSide; i < (rightSide >= arrLength ? arrLength : rightSide); i++) {
            array[i] = '.';
        }
    }

    private static String GetBomb(char[] array, int startIndex) {
        StringBuilder sb = new StringBuilder();
        int index = startIndex + 1;
        while (array[index] != '|'){
            sb.append(array[index]);
            index++;
        }

        return sb.toString();
    }

    private static int CalculateBombPower(char[] array, int startIndex) {
        int bombPower = 0;

        int index = startIndex + 1;
        while (array[index] != '|'){
            bombPower += array[index];
            index++;
        }

        return bombPower % 10;
    }
}
