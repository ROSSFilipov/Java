import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 10/23/2015.
 */
public class LogsAggregator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        TreeMap<String, Integer> durations = new TreeMap<>();
        HashMap<String, TreeSet<String>> IPaddresses = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] currentLine = scanner.nextLine().split("\\s+");
            String currentIP = currentLine[0];
            String name = currentLine[1];
            Integer currentDuration = Integer.parseInt(currentLine[2]);

            if (!durations.containsKey(name)){
                durations.put(name, 0);
            }

            if (!IPaddresses.containsKey(name)){
                IPaddresses.put(name, new TreeSet<>());
            }

            Integer newDuration = durations.get(name) + currentDuration;
            durations.put(name, newDuration);

            IPaddresses.get(name).add(currentIP);
        }

        for (Map.Entry pair : durations.entrySet()){
            System.out.printf("%s: %d %s\n", pair.getKey(), pair.getValue(), IPaddresses.get(pair.getKey()));
        }
    }
}
