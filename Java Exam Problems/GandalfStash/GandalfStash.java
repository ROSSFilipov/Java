import java.util.Scanner;

/**
 * Created by user on 10/31/2015.
 */
public class GandalfStash {
    private static int happyness;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        happyness = Integer.parseInt(scanner.nextLine());
        String[] input = scanner.nextLine().toLowerCase().split("[^a-zA-Z]+");

        EatFood(input);
        PrintOutcome();
    }

    private static void EatFood(String[] input) {
        for (int i = 0; i < input.length; i++) {
            switch (input[i]){
                case "cram": happyness += 2;
                    break;
                case "lembas": happyness += 3;
                    break;
                case "apple": happyness += 1;
                    break;
                case "melon": happyness += 1;
                    break;
                case "honeycake": happyness += 5;
                    break;
                case "mushrooms": happyness -= 10;
                    break;
                default: happyness -= 1;
                    break;
            }
        }
    }

    private static void PrintOutcome() {
        boolean isAngry = happyness < -5;
        boolean isSad = happyness >= -5 && happyness < 0;
        boolean isHappy = happyness >= 0 && happyness <= 15;
        boolean hasSpecialMood = happyness > 15;

        System.out.println(happyness);
        if (isAngry){
            System.out.println("Angry");
        } else if (isSad){
            System.out.println("Sad");
        } else if (isHappy){
            System.out.println("Happy");
        } else if (hasSpecialMood){
            System.out.println("Special JavaScript mood");
        } else {
            System.out.println("Wrong output.");
        }
    }
}
