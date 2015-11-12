import java.util.*;

/**
 * Created by user on 11/11/2015.
 */
public class Salaries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Integer>[] currentGraph = new List[n];

        AssembleGraph(currentGraph, n, scanner);

        CalculateSalaries(currentGraph, n);
    }

    private static void CalculateSalaries(List<Integer>[] currentGraph, int n) {
        int[] salaries = new int[n];

        for (int i = 0; i < n; i++) {
            if (salaries[i] == 0) {
                salaries[i] = DFS(currentGraph, i, salaries);
            }
        }
        
        int totalSalaries = 0;
        for (int i = 0; i < n; i++) {
            totalSalaries += salaries[i];
        }

        System.out.println(totalSalaries);
    }

    private static int DFS(List<Integer>[] currentGraph, int currentEmployee, int[] salaries) {
        if (currentGraph[currentEmployee].size() == 0){
            return 1;
        } else {
            int currentSalary = 0;
            for (int i = 0; i < currentGraph[currentEmployee].size(); i++) {
                if (salaries[currentGraph[currentEmployee].get(i)] == 0) {
                    currentSalary += DFS(currentGraph, currentGraph[currentEmployee].get(i), salaries);
                } else currentSalary += salaries[currentGraph[currentEmployee].get(i)];
            }

            return currentSalary;
        }
    }

    private static void AssembleGraph(List<Integer>[] currentGraph, int n, Scanner scanner) {
        for (int i = 0; i < n; i++) {
            String currentLine = scanner.nextLine();
            currentGraph[i] = new ArrayList<>();
            for (int j = 0; j < currentLine.length(); j++) {
                if (currentLine.charAt(j) == 'Y'){
                    currentGraph[i].add(j);
                }
            }
        }
    }
}
