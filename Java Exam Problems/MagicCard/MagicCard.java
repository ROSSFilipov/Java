import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by user on 10/25/2015.
 */
public class MagicCard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String command = scanner.nextLine();
        String magicCard = scanner.next();
        String magicFace = magicCard.substring(0, magicCard.length() - 1);
        String magicSuit = magicCard.substring(magicCard.length() - 1, magicCard.length());

        ArrayList<String> filteredCards = new ArrayList<>();

        FilterOddEven(input, filteredCards, command);

        final int[] sum = {0};
        filteredCards
                .stream()
                .forEach(s -> {
                            String currentCardFace = s.substring(0, s.length() - 1);
                            String currentCardSuit = s.substring(s.length() - 1, s.length());
                            Integer currentFaceInteger = GetFaceInteger(currentCardFace);
                            if (currentCardFace.equals(magicFace)) {
                                sum[0] += 3 * currentFaceInteger;
                            } else if (currentCardSuit.equals(magicSuit)) {
                                sum[0] += 2 * currentFaceInteger;
                            } else {
                                sum[0] += currentFaceInteger;
                            }
                        }
                );

        System.out.println(sum[0]);

    }

    private static void FilterOddEven(String input, ArrayList<String> filteredCards, String command) {
        String[] inputSplit = input.split("\\s+");
        switch (command){
            case "odd":
                for (int i = 1; i < inputSplit.length; i += 2) {
                    filteredCards.add(inputSplit[i]);
                }
                return;
            case "even":
                for (int i = 0; i < inputSplit.length; i += 2) {
                    filteredCards.add(inputSplit[i]);
                }
                return;
        }
    }

    private static Integer GetFaceInteger(String currentCardFace) {
        Integer faceInteger = 0;
        switch (currentCardFace){
            case "2": faceInteger = 2; break;
            case "3": faceInteger = 3; break;
            case "4": faceInteger = 4; break;
            case "5": faceInteger = 5; break;
            case "6": faceInteger = 6; break;
            case "7": faceInteger = 7; break;
            case "8": faceInteger = 8; break;
            case "9": faceInteger = 9; break;
            case "10": faceInteger = 10; break;
            case "J": faceInteger = 12; break;
            case "Q": faceInteger = 13; break;
            case "K": faceInteger = 14; break;
            case "A": faceInteger = 15; break;
        }

        return faceInteger * 10;
    }
}
