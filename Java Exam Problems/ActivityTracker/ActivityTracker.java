import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 10/25/2015.
 */
public class ActivityTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        TreeMap<Integer, TreeMap<String, Integer>> dataCollection = new TreeMap<>();
        Pattern pattern = Pattern.compile("[0-9]+\\/([0-9]+)\\/[0-9]+\\s([a-zA-Z]+)\\s([0-9]+)");
        for (int i = 0; i < n; i++) {
            String currentLine = scanner.nextLine();
            Matcher matcher = pattern.matcher(currentLine);
            matcher.find();

            Integer currentMonth = Integer.parseInt(matcher.group(1));
            String currentUser = matcher.group(2);
            Integer currentActivity = Integer.parseInt(matcher.group(3));

            if (!dataCollection.containsKey(currentMonth)){
                dataCollection.put(currentMonth, new TreeMap<>());
            }

            if (!dataCollection.get(currentMonth).containsKey(currentUser)){
                dataCollection.get(currentMonth).put(currentUser, 0);
            }

            Integer newActivity = dataCollection.get(currentMonth).get(currentUser) + currentActivity;

            dataCollection.get(currentMonth).put(currentUser, newActivity);
        }

        for (Map.Entry entry : dataCollection.entrySet()){
            System.out.printf("%d: ", entry.getKey());
            ArrayList<String> currentUsers = new ArrayList<>();
            for (Map.Entry innerEntry : dataCollection.get(entry.getKey()).entrySet()){
                currentUsers.add(String.format("%s(%d)", innerEntry.getKey(), innerEntry.getValue()));
            }
            System.out.printf("%s\n", String.join(", ", currentUsers));
        }
    }
}
