import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by user on 10/18/2015.
 */
public class ThreeLargestNumbers {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        BigDecimal[] decimalList = new BigDecimal[n];

        for (int i = 0; i < n; i++) {
            String currentLine = scanner.next();
            decimalList[i] = new BigDecimal(currentLine);
        }

        Arrays.sort(decimalList);

        GetThreeLargest(decimalList);
    }

    private static void GetThreeLargest(BigDecimal[] decimalList)
    {
        int index = 0;

        for (int i = decimalList.length - 1; i >= 0; i--) {
            if (index == 3)
            {
                break;
            }

            System.out.println(decimalList[i].toPlainString());
            index++;
        }
    }
}
