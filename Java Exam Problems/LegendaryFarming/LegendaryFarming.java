import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 10/27/2015.
 */
public class LegendaryFarming {
    private static int CraftingAmount = 250;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<String, Integer> junkCollection = new TreeMap<String, Integer>();
        TreeMap<String, Integer> legendaryCollection = new TreeMap<>();

        legendaryCollection.put("shards", 0);
        legendaryCollection.put("fragments", 0);
        legendaryCollection.put("motes", 0);

        boolean isCrafted = false;
        String legendaryObtained = "";
        while (!isCrafted){
            String[] currentLine = scanner.nextLine().toLowerCase().split("[\\s]+");
            for (int i = 1; i < currentLine.length; i += 2) {
                String currentMaterial = currentLine[i];
                Integer currentAmount = Integer.parseInt(currentLine[i - 1]);
                if (IsLegendaryMaterial(currentMaterial)){

                    int newAmount = legendaryCollection.get(currentMaterial) + currentAmount;

                    if (newAmount >= CraftingAmount) {
                        newAmount -= 250;
                        isCrafted = true;
                        legendaryObtained = GetLegendary(currentMaterial);
                    }

                    legendaryCollection.put(currentMaterial, newAmount);

                    if (isCrafted){
                        break;
                    }

                } else {
                    if (!junkCollection.containsKey(currentMaterial)){
                        junkCollection.put(currentMaterial, 0);
                    }

                    int newAmount = junkCollection.get(currentMaterial) + currentAmount;
                    junkCollection.put(currentMaterial, newAmount);
                }
            }
        }

        Comparator<Map.Entry<String, Integer>> customComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };

        List<Map.Entry<String, Integer>> entriesList = new ArrayList<>(legendaryCollection.entrySet());

        Collections.sort(entriesList, customComparator);

        PrintOutput(entriesList, junkCollection, legendaryObtained);
    }

    private static void PrintOutput(List<Map.Entry<String, Integer>> entriesList, TreeMap<String, Integer> junkCollection, String legendaryObtained) {
        System.out.printf("%s obtained!\n", legendaryObtained);
        for (Map.Entry pair : entriesList){
            System.out.printf("%s: %d\n", pair.getKey(), pair.getValue());
        }

        for (Map.Entry junkPair : junkCollection.entrySet()){
            System.out.printf("%s: %d\n", junkPair.getKey(), junkPair.getValue());
        }
    }

    private static String GetLegendary(String currentMaterial) {
        switch (currentMaterial){
            case "shards": return "Shadowmourne";
            case "motes": return "Dragonwrath";
            case "fragments": return "Valanyr";
            default: return "Invalid";
        }
    }

    private static boolean IsLegendaryMaterial(String currentMaterial) {
        switch (currentMaterial){
            case "shards": return true;
            case "motes": return true;
            case "fragments": return true;
            default: return false;
        }
    }
}

