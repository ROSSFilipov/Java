import java.util.*;

/**
 * Created by user on 10/24/2015.
 */
public class SchoolSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        TreeMap<String, TreeMap<String, ArrayList<Double>>> dataCollection = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] currentLine = scanner.nextLine().split("\\s+");
            String currentName = currentLine[0] + " " + currentLine[1];
            String currentSubject = currentLine[2];
            Double currentGrade = Double.parseDouble(currentLine[3]);

            if (!dataCollection.containsKey(currentName)){
                dataCollection.put(currentName, new TreeMap<>());
            }

            if (!dataCollection.get(currentName).containsKey(currentSubject)) {
                dataCollection.get(currentName).put(currentSubject, new ArrayList<>());
            }

            dataCollection.get(currentName).get(currentSubject).add(currentGrade);
        }

        for (Map.Entry pair : dataCollection.entrySet()){
            System.out.print(pair.getKey() + ": ");
            ArrayList<String> gradesList = new ArrayList<>();
            for (Map.Entry innerPair : dataCollection.get(pair.getKey()).entrySet()){
                ArrayList<Double> currentGrades = dataCollection.get(pair.getKey()).get(innerPair.getKey());
                Double sum = 0.0;
                for (Double currentGrade : currentGrades) {
                    sum += currentGrade;
                }

                Double currentAverage = sum / currentGrades.size();
                gradesList.add(String.format("%s - %.2f", innerPair.getKey(), currentAverage));
            }
            System.out.println(gradesList);
        }
    }
}
