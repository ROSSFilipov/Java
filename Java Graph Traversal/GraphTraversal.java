import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by user on 11/9/2015.
 */
public class GraphTraversal {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int graphSize = Integer.parseInt(scanner.nextLine());

        List<Integer>[] currentGraph = new List[graphSize];

        AssembleGraph(currentGraph, graphSize, scanner);

        boolean[] used = new boolean[graphSize];
        for (int i = 0; i < graphSize; i++) {
            used[i] = false;
        }

        for (int node = 0; node < graphSize; node++) {
            if (!used[node]) {
                System.out.print("Connected components: ");
                DFS(currentGraph, node, used);
                System.out.println();
            }
        }
    }

    private static void DFS(List<Integer>[] currentGraph, int node, boolean[] used) {
        if (used[node]){
            return;
        }

        System.out.print(node + " ");
        used[node] = true;

        for (int connectedNode = 0; connectedNode < currentGraph[node].size(); connectedNode++) {
            DFS(currentGraph, currentGraph[node].get(connectedNode), used);
        }
    }

    private static void AssembleGraph(List<Integer>[] currentGraph, int graphSize, Scanner scanner) {
        for (int i = 0; i < graphSize; i++) {
            currentGraph[i] = new ArrayList<>();
            List<Integer> currentLine = Arrays.asList(scanner.nextLine().split("\\s+"))
                    .stream()
                    .filter(s -> !s.equals(""))
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            currentGraph[i].addAll(currentLine);
        }
    }
}
