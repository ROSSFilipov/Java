import javafx.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 10/17/2015.
 */
public class CognateWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(input);

        ArrayList<String> allWords = new ArrayList<String>();
        while (matcher.find()){
            String word = matcher.group();
            allWords.add(word);
        }

        HashSet<String> set = new HashSet<>();
        int size = allWords.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (i != j && ((allWords.get(i) + allWords.get(j)).equals(allWords.get(k)))){
                        if (!set.contains(allWords.get(k))){
                            System.out.printf("%s|%s=%s\n", allWords.get(i), allWords.get(j), allWords.get(k));
                            set.add(allWords.get(k));
                        }
                    }
                }
            }
        }

        if (set.isEmpty()){
            System.out.println("No");
        }
    }
}
