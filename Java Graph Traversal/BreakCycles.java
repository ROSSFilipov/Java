import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collector;

/**
 * Created by user on 11/11/2015.
 */
public class BreakCycles {
    private static Map<Integer, List<Integer>> intGraph = new LinkedHashMap<Integer, List<Integer>>();
    private static Map<Character, List<Character>> charGraph = new TreeMap<>();
    public static void main(String[] args) {
        intGraph.put(1, Arrays.asList(2, 5, 4));
        intGraph.put(2, Arrays.asList(1, 3));
        intGraph.put(3, Arrays.asList(2, 5));
        intGraph.put(4, Arrays.asList(1));
        intGraph.put(5, Arrays.asList(1, 3));
        intGraph.put(6, Arrays.asList(7, 8));
        intGraph.put(7, Arrays.asList(6, 8));
        intGraph.put(8, Arrays.asList(6, 7));

        charGraph.put('K', new ArrayList<>());
        charGraph.get('K').add('X');
        charGraph.get('K').add('J');
        charGraph.put('J', new ArrayList<>());
        charGraph.get('J').add('K');
        charGraph.get('J').add('N');
        charGraph.put('N', new ArrayList<>());
        charGraph.get('N').add('J');
        charGraph.get('N').add('X');
        charGraph.get('N').add('L');
        charGraph.get('N').add('M');
        charGraph.put('X', new ArrayList<>());
        charGraph.get('X').add('K');
        charGraph.get('X').add('N');
        charGraph.get('X').add('Y');
        charGraph.put('M', new ArrayList<>());
        charGraph.get('M').add('N');
        charGraph.get('M').add('I');
        charGraph.put('Y', new ArrayList<>());
        charGraph.get('Y').add('X');
        charGraph.get('Y').add('L');
        charGraph.put('L', new ArrayList<>());
        charGraph.get('L').add('N');
        charGraph.get('L').add('I');
        charGraph.get('L').add('Y');
        charGraph.put('I', new ArrayList<>());
        charGraph.get('I').add('M');
        charGraph.get('I').add('L');
        charGraph.put('A', new ArrayList<>());
        charGraph.get('A').add('Z');
        charGraph.get('A').add('Z');
        charGraph.get('A').add('Z');
        charGraph.put('Z', new ArrayList<>());
        charGraph.get('Z').add('A');
        charGraph.get('Z').add('A');
        charGraph.get('Z').add('A');
        charGraph.put('F', new ArrayList<>());
        charGraph.get('F').add('E');
        charGraph.get('F').add('B');
        charGraph.get('F').add('P');
        charGraph.put('E', new ArrayList<>());
        charGraph.get('E').add('F');
        charGraph.get('E').add('P');
        charGraph.put('P', new ArrayList<>());
        charGraph.get('P').add('B');
        charGraph.get('P').add('F');
        charGraph.get('P').add('E');
        charGraph.put('B', new ArrayList<>());
        charGraph.get('B').add('F');
        charGraph.get('B').add('P');

        SortCharGraph();

        List<Pair<Character, Character>> edgesList = new ArrayList<>();

        AssembleListOfEdges(edgesList);

        List<Pair<Character, Character>> removedEdges = new ArrayList<>();

        for (int i = 0; i < edgesList.size(); i++) {
            Pair<Character, Character> currentEdge = edgesList.get(i);
            if (ClosesCycle(currentEdge)){
                removedEdges.add(currentEdge);
            } else {
            }
        }

        System.out.println("Edges to be removed:");
        for (Pair<Character, Character> currentEdge : removedEdges) {
            System.out.println(currentEdge.getKey() + " " + currentEdge.getValue());
        }
    }

    private static boolean ClosesCycle(Pair<Character, Character> currentEdge) {
        Character firstNode = currentEdge.getKey();
        Character secondNode = currentEdge.getValue();

        charGraph.get(firstNode).remove(secondNode);
        charGraph.get(secondNode).remove(firstNode);

        Queue<Character> queue = new LinkedList<>();
        HashSet<Character> visited = new HashSet<>();

        queue.add(firstNode);
        visited.add(firstNode);

        while (!queue.isEmpty()){
            Character node = queue.remove();

            if (node == secondNode){
                return true;
            }

            for (Character adjacentNode : charGraph.get(node)){
                if (!visited.contains(adjacentNode)){
                    queue.add(adjacentNode);
                    visited.add(adjacentNode);
                }
            }
        }

        return false;
    }

    private static void AssembleListOfEdges(List<Pair<Character, Character>> edgesList) {
        for (Map.Entry<Character, List<Character>> currentNode : charGraph.entrySet()){
            for (Character adjacentNode : charGraph.get(currentNode.getKey())){
                edgesList.add(new Pair<Character, Character>(currentNode.getKey(), adjacentNode));
            }
        }
    }

    private static void SortCharGraph() {
        charGraph.entrySet().stream().sorted((x,y) -> x.getKey().compareTo(y.getKey()));

        for (Map.Entry<Character, List<Character>> currentNode : charGraph.entrySet()){
            Collections.sort(currentNode.getValue());
        }
    }
}
