import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 10/17/2015.
 */
public class StraightFlush
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("[,\\s]+");

        GenerateFlushes(input);
    }

    private static void GenerateFlushes(String[] input)
    {
        Map<String, Integer> cards = new LinkedHashMap<>();
        FillCardsDictionary(cards);

        Pattern pattern = Pattern.compile("(2|3|4|5|6|7|8|9|10|J|Q|K|A)(H|D|C|S)");
        Pattern colorPattern = Pattern.compile("S|H|C|D");
        int size = input.length;
        boolean found = false;
        for (int i1 = 0; i1 < size; i1++)
        {
            Matcher matcherOne = pattern.matcher(input[i1]);
            matcherOne.find();
            String currentCardOne = matcherOne.group(1);
            String currentColorOne = matcherOne.group(2);
            for (int i2 = 0; i2 < size; i2++)
            {
                Matcher matcherTwo = pattern.matcher(input[i2]);
                matcherTwo.find();
                String currentCardTwo = matcherTwo.group(1);
                String currentColorTwo = matcherTwo.group(2);
                if (i1 != i2 && cards.get(currentCardTwo) - 1 == cards.get(currentCardOne) &&
                        currentColorOne.equals(currentColorTwo))
                {
                    for (int i3 = 0; i3 < size; i3++)
                    {
                        Matcher matcherThree = pattern.matcher(input[i3]);
                        matcherThree.find();
                        String currentCardThree = matcherThree.group(1);
                        String currentColorThree = matcherThree.group(2);
                        if (i3 != i2 && i3 != i1 && cards.get(currentCardThree) - 1 == cards.get(currentCardTwo) &&
                                currentColorThree.equals(currentColorTwo))
                        {
                            for (int i4 = 0; i4 < size; i4++)
                            {
                                Matcher matcherFour = pattern.matcher(input[i4]);
                                matcherFour.find();
                                String currentCardFour = matcherFour.group(1);
                                String currentColorFour = matcherFour.group(2);
                                if (i4 != i3 && i4 != i2 && i4 != i1 && cards.get(currentCardFour) - 1 == cards.get(currentCardThree) &&
                                        currentColorFour.equals(currentColorThree))
                                {
                                    for (int i5 = 0; i5 < size; i5++)
                                    {
                                        Matcher matcherFive = pattern.matcher(input[i5]);
                                        matcherFive.find();
                                        String currentCardFive = matcherFive.group(1);
                                        String currentColorFive = matcherFive.group(2);
                                        if (i5 != i4 && i5 != i3 && i5 != i2 && i5 != i1 &&
                                                cards.get(currentCardFive) - 1 == cards.get(currentCardFour) &&
                                                currentColorFive.equals(currentColorFour))
                                        {
                                            System.out.printf("[%s, %s, %s, %s, %s]\n", input[i1], input[i2], input[i3], input[i4], input[i5]);
                                            found = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!found)
        {
            System.out.println("No Straight Flushes");
        }
    }

    private static void FillCardsDictionary(Map<String, Integer> cards)
    {
        String[] cardsArray = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int index = 2;
        for (int i = 0; i < 13; i++)
        {
            cards.put(cardsArray[i], index);
            index++;
        }
    }
}
