import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by user on 10/17/2015.
 */
public class ExamScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();

        TreeMap<Integer, TreeMap<String, Double>> studentsCollection = new TreeMap<Integer, TreeMap<String, Double>>();

        while (true)
        {
            String[] currentLine = scanner.nextLine().split("[|]+");
            ArrayList<String> currentList = new ArrayList<>();

            for (int i = 0; i < currentLine.length; i++) {
                if (!currentLine[i].equals("") && !currentLine[i].equals(null))
                {
                    currentList.add(currentLine[i]);
                }
            }

            if (currentList.size() != 3)
            {
                break;
            }

            String student = currentList.get(0).trim();
            int score = Integer.parseInt(currentList.get(1).trim());
            double grade = Double.parseDouble(currentList.get(2).trim());

            if (!studentsCollection.containsKey(score)){
                studentsCollection.put(score, new TreeMap<String, Double>());
            }

            studentsCollection.get(score).put(student, grade);
        }

        for(Map.Entry<Integer, TreeMap<String, Double>> pair : studentsCollection.entrySet())
        {
            ArrayList<String> studentNames = new ArrayList<>();
            double allGrades = 0;
            for (Map.Entry<String, Double> student : pair.getValue().entrySet())
            {
                studentNames.add(student.getKey());
                allGrades += student.getValue();
            }

            System.out.printf("%d -> ", pair.getKey());
            System.out.print(studentNames);
            System.out.printf("; avg=%.2f\n", allGrades / studentNames.size());
        }
    }
}
