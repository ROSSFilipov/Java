import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 10/23/2015.
 */
public class SumCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] hand = scanner.nextLine().split("\\s+");

        int size = hand.length;
        int[] cardFaces = new int[size];
        for (int i = 0; i < size; i++) {
            String currentCard = hand[i].substring(0, hand[i].length() - 1);
            cardFaces[i] = TakeCurrentFace(currentCard);
        }

        long sum = 0;
        int i = 0;
        while (i < size) {
            int multiplyIndex = 1;
            int currentNumber = cardFaces[i];
            i++;
            while (i < size && cardFaces[i] == currentNumber){
                multiplyIndex++;
                i++;
            }

            if (multiplyIndex == 1){
                sum += currentNumber;
            } else {
                sum += (currentNumber * multiplyIndex) * 2;
            }
        }

        System.out.println(sum);
    }

    private static int TakeCurrentFace(String currentCard) {
        switch (currentCard){
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "J": return 12;
            case "Q": return 13;
            case "K": return 14;
            case "A": return 15;
            default: return -1;
        }
    }
}
