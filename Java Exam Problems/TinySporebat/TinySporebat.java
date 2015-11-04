import java.util.Scanner;

/**
 * Created by user on 10/27/2015.
 */
public class TinySporebat {
    private static int Health = 5800;
    private static int GlowCaps = 0;
    private static boolean PlayerAlive = true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (PlayerAlive){
            String currentLine = scanner.nextLine();

            if (currentLine.equals("Sporeggar")){
                break;
            }

            int currentNumber = Integer.parseInt(currentLine.substring(currentLine.length() - 1, currentLine.length()));
            PassEnemies(currentLine);

            if (Health <= 0){
                PlayerAlive = false;
                break;
            } else {
                GlowCaps += currentNumber;
            }
        }

        if (PlayerAlive){
            if (GlowCaps >= 30){
                System.out.printf("Health left: %d\nBought the sporebat. Glowcaps left: %d", Health, GlowCaps - 30);
            } else {
                System.out.printf("Health left: %d\nSafe in Sporeggar, but another %d Glowcaps needed.", Health, 30 - GlowCaps);
            }

        } else {
            System.out.printf("Died. Glowcaps: %d", GlowCaps);
        }
    }

    private static void PassEnemies(String currentLine) {
        for (int i = 0; i < currentLine.length() - 1; i++) {
            if (currentLine.charAt(i) == 'L'){
                Health += 200;
            } else {
                Health -= currentLine.charAt(i);
            }
        }
    }
}
