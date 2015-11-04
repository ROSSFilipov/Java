import javafx.util.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 11/4/2015.
 */
public class Tasks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Comparator<Pair<String, Integer>> customComparator = new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())){
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return o1.getValue().compareTo(o2.getValue());
                }
            }
        };

        ArrayList<Pair<String, Integer>> taskList = new ArrayList<>();
        Pattern inputPattern = Pattern.compile("(Solve|New)\\s*([0-9]+)*\\s*([a-zA-Z0-9-\\s]+)*");
        for (int i = 0; i < n; i++) {
            String currentLine = scanner.nextLine().trim();
            Matcher matcher = inputPattern.matcher(currentLine);
            if (matcher.find()){
                if (matcher.group(1).equals("Solve")){
                    SolveTask(taskList, customComparator);
                } else {
                    AddNewTask(taskList, matcher.group(2), matcher.group(3));
                }
            }
        }
    }

    private static void AddNewTask(ArrayList<Pair<String, Integer>> taskList, String number, String taskName) {
        int taskComplexity = Integer.parseInt(number);
        taskList.add(new Pair<String, Integer>(taskName, taskComplexity));
    }

    private static void SolveTask(ArrayList<Pair<String, Integer>> taskList, Comparator<Pair<String, Integer>> customComparator) {
        if (taskList.size() == 0) {
            System.out.println("Rest");
        } else {
            Collections.sort(taskList, customComparator);
            System.out.println(taskList.get(0).getKey());
            taskList.remove(0);
        }
    }
}
