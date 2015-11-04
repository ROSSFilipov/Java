import java.util.Scanner;

/**
 * Created by user on 10/17/2015.
 */
public class Durts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int centerX = scanner.nextInt();
        int centerY = scanner.nextInt();

        int radius = scanner.nextInt();
        int darts = scanner.nextInt();

        for (int i = 0; i < darts; i++) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();

            if (IsInside(centerX, centerY, radius, X, Y)){
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    private static boolean IsInside(int centerX, int centerY, int radius, int x, int y) {
        boolean isIndsideOne = (x >= centerX - (radius / 2) && x <= centerX + (radius / 2))
                && (y >= centerY - radius && y <= centerY + radius);

        boolean isInsideTwo = (x >= centerX - radius && x <= centerX + radius)
                && (y >= centerY - (radius / 2) && y <= centerY + (radius / 2));

        if (isIndsideOne || isInsideTwo){
            return true;
        } else {
            return  false;
        }
    }
}
