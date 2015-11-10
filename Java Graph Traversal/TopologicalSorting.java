import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by user on 11/9/2015.
 */
public class TopologicalSorting {
    private static int graphSize;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> currentGraph = new LinkedHashMap<>();

        graphSize = Integer.parseInt(scanner.nextLine());

        AssembleGraph(currentGraph, scanner);

        String[] values = new String[graphSize];

        List<Map.Entry<String, List<String>>> graphEntries = new ArrayList<>(currentGraph.entrySet());

        GetValues(graphEntries, values);

        Map<Integer, List<Integer>> reformattedGraph = new LinkedHashMap<>();

        ReassembleGraph(graphEntries, reformattedGraph, values);

        Map<Integer, Integer> predecessorsMap = new LinkedHashMap<>();

        DistributePredecessors(reformattedGraph, predecessorsMap);

        System.out.println("Topological sorting:");
        TopSort(reformattedGraph, predecessorsMap, values);
    }

    private static void AssembleGraph(Map<String, List<String>> currentGraph, Scanner scanner) {
        Pattern inputPattern = Pattern.compile("\"([a-zA-Z]+)\"\\s*->(.+)?");
        for (int i = 0; i < graphSize; i++) {
            String currentLine = scanner.nextLine();
            Matcher matcher = inputPattern.matcher(currentLine);
            if (matcher.find()){
                currentGraph.put(matcher.group(1), new ArrayList<>());
                if (matcher.group(2) != null){
                    currentGraph.get(matcher.group(1)).addAll(Arrays.asList(matcher.group(2).split("[ ,\"]+"))
                            .stream()
                            .filter(s -> !s.equals(""))
                            .collect(Collectors.toList()));
                }
            }
        }
    }

    private static void GetValues(List<Map.Entry<String, List<String>>> graphEntries, String[] values) {
        for (int i = 0; i < graphEntries.size(); i++) {
            values[i] = graphEntries.get(i).getKey();
        }
    }

    private static Integer GetIndexOf(String currentValue, String[] values) {
        for (int i = 0; i < graphSize; i++) {
            if (values[i].equals(currentValue)){
                return i;
            }
        }

        return -1;
    }

    private static void ReassembleGraph(List<Map.Entry<String, List<String>>> graphEntries, Map<Integer, List<Integer>> reformattedGraph, String[] values) {
        for (int i = 0; i < graphSize; i++) {
            int currentKey = (GetIndexOf(graphEntries.get(i).getKey(), values));
            if (!reformattedGraph.containsKey(currentKey)){
                reformattedGraph.put(currentKey, new ArrayList<>());
            }

            for (int j = 0; j < graphEntries.get(i).getValue().size(); j++) {
                reformattedGraph.get(currentKey).add(GetIndexOf(graphEntries.get(i).getValue().get(j), values));
            }
        }
    }

    private static void DistributePredecessors(Map<Integer, List<Integer>> reformattedGraph, Map<Integer, Integer> predecessorsMap) {
        for (Map.Entry currentNode : reformattedGraph.entrySet()){
            if (!predecessorsMap.containsKey(currentNode.getKey())){
                predecessorsMap.put((int)currentNode.getKey(), 0);
            }

            for (Integer currentChild : reformattedGraph.get(currentNode.getKey())){
                if (!predecessorsMap.containsKey(currentChild)){
                    predecessorsMap.put(currentChild, 0);
                }

                int predecessorsCount = predecessorsMap.get(currentChild) + 1;
                predecessorsMap.put(currentChild, predecessorsCount);
            }
        }
    }

    private static void TopSort(Map<Integer, List<Integer>> reformattedGraph, Map<Integer, Integer> predecessorsMap, String[] values) {
        if (predecessorsMap.entrySet().size() == 0){
            return;
        }

        int nodeToRemove = GetNodeToBeRemoved(predecessorsMap);

        if (nodeToRemove == -1){
            System.out.println("Cycle detected !");
            return;
        }

        for (int i = 0; i < reformattedGraph.get(nodeToRemove).size(); i++) {
            int value = predecessorsMap.get(reformattedGraph.get(nodeToRemove).get(i)) - 1;
            predecessorsMap.put(reformattedGraph.get(nodeToRemove).get(i), value);
        }

        System.out.print(values[nodeToRemove] + " ");
        predecessorsMap.remove(nodeToRemove);

        TopSort(reformattedGraph, predecessorsMap, values);
    }

    private static int GetNodeToBeRemoved(Map<Integer, Integer> predecessorsMap) {
        for (Map.Entry<Integer, Integer> currentNode : predecessorsMap.entrySet()){
            if (currentNode.getValue() == 0){
                return currentNode.getKey();
            }
        }

        return -1;
    }
}
