import java.util.*;

/**
 * Created by user on 11/10/2015.
 */
class Graph{
    private Map<Integer, List<Integer>> variant;
    private int currentDistance;

    public Graph(){
        this.currentDistance = 0;
    }

    public Map<Integer, List<Integer>> VariantOne(){
        this.variant = new TreeMap<Integer, List<Integer>>();
        this.variant.put(1, Arrays.asList(2));
        this.variant.put(2, new ArrayList<>());

        return this.variant;
    }

    public Map<Integer, List<Integer>> VariantTwo(){
        this.variant = new TreeMap<Integer, List<Integer>>();
        this.variant.put(1, Arrays.asList(4));
        this.variant.put(2, Arrays.asList(4));
        this.variant.put(3, Arrays.asList(4, 5));
        this.variant.put(4, Arrays.asList(6));
        this.variant.put(5, Arrays.asList(3, 7, 8));
        this.variant.put(6, new ArrayList<>());
        this.variant.put(7, Arrays.asList(8));
        this.variant.put(8, new ArrayList<>());

        return this.variant;
    }

    public Map<Integer, List<Integer>> VariantThree(){
        this.variant = new TreeMap<Integer, List<Integer>>();
        this.variant.put(11, Arrays.asList(4));
        this.variant.put(4, Arrays.asList(12, 1));
        this.variant.put(1, Arrays.asList(12, 21, 7));
        this.variant.put(7, Arrays.asList(21));
        this.variant.put(12, Arrays.asList(4, 19));
        this.variant.put(19, Arrays.asList(1, 21));
        this.variant.put(21, Arrays.asList(14, 31));
        this.variant.put(14, Arrays.asList(14));
        this.variant.put(31, new ArrayList<>());

        return this.variant;
    }

    public void getDistances(Map<Integer, List<Integer>> currentGraph){
        for (Map.Entry<Integer, List<Integer>> currentNode : currentGraph.entrySet()){
            for (Map.Entry<Integer, List<Integer>> otherNode : currentGraph.entrySet()){
                if (!currentNode.getKey().equals(otherNode.getKey())) {
                    int distance = GetMinDistance(currentGraph, currentNode.getKey(), otherNode.getKey());
                    if (distance != 0) {
                        System.out.printf("Distance between node: %d and node: %d is %d.\n",
                                currentNode.getKey(), otherNode.getKey(), distance);
                    } else {
                        System.out.printf("There is no path from node: %d to node: %d.\n",
                                currentNode.getKey(), otherNode.getKey());
                    }

                    ResetDistances();
                }
            }
        }
    }

    public void printShortestPaths(Map<Integer, List<Integer>> currentGraph){
        for (Map.Entry<Integer, List<Integer>> currentNode : currentGraph.entrySet()){
            for (Map.Entry<Integer, List<Integer>> connectedNode : currentGraph.entrySet()){
                if (!currentNode.getKey().equals(connectedNode.getKey())){
                    String currentPath = GetPath(currentGraph, currentNode.getKey(), connectedNode.getKey());
                    if (currentPath.equals("")){
                        System.out.printf("There is no path from node: %d to node: %d.\n",
                                currentNode.getKey(), connectedNode.getKey());
                    } else {
                        System.out.printf("The path from node: %d to node: %d is: %s.\n",
                                currentNode.getKey(), connectedNode.getKey(), currentPath);
                    }
                }
            }
        }
    }

    private String GetPath(Map<Integer, List<Integer>> currentGraph, Integer currentNode, Integer connectedNode) {
        int[] parents = new int[currentGraph.size() + 1];
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[currentGraph.size() + 1];

        queue.add(currentNode);
        visited[currentNode] = 1;
        parents[currentNode] = 0;

        while (!queue.isEmpty()){
            int node = queue.remove();

            if (node == connectedNode){
                break;
            }

            for (Integer childNode : currentGraph.get(node)){
                if (visited[childNode] == 0){
                    queue.add(childNode);
                    parents[childNode] = node;
                    visited[childNode] = 1;
                }
            }
        }

        if (parents[connectedNode] != 0) {
            StringBuilder sb = new StringBuilder();

            int j = connectedNode;

            while (j != 0) {
                sb.append(" ");
                sb.append(j);
                j = parents[j];
            }

            sb.reverse();

            return sb.toString();
        } else {
            return "";
        }
    }

    private int GetMinDistance(Map<Integer, List<Integer>> currentGraph, Integer currentNode, Integer otherNode) {
        List<Integer> distances = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();

        DFS(currentGraph, distances, visited, currentNode, otherNode);

        Collections.sort(distances);

        if (distances.size() == 0){
            return 0;
        } else {
            return distances.get(0);
        }
    }

    private void ResetDistances() {
        this.currentDistance = 0;
    }

    private void DFS(Map<Integer, List<Integer>> currentGraph, List<Integer> distances, HashSet<Integer> visited, Integer currentNode, Integer otherNode) {
        if (currentNode.equals(otherNode)){
            distances.add(this.currentDistance);
        }

        this.currentDistance++;
        visited.add(currentNode);

        for (Integer connectedNode : currentGraph.get(currentNode)) {
            if (!visited.contains(connectedNode)) {
                DFS(currentGraph, distances, visited, connectedNode, otherNode);
            }
        }

        visited.remove(currentNode);
        this.currentDistance--;
    }
}

public class DistanceBetweenVertices {
    public static void main(String[] args) {
        Graph currentGraph = new Graph();
        System.out.println("Variant one:");
        currentGraph.getDistances(currentGraph.VariantOne());
        System.out.println();
        //System.out.println("Variant two:");
        //currentGraph.getDistances(currentGraph.VariantTwo());
        System.out.println();
        //System.out.println("Variant three:");
        //currentGraph.getDistances(currentGraph.VariantThree());
        System.out.println("Shortest paths in variant one:");
        currentGraph.printShortestPaths(currentGraph.VariantTwo());
    }
}
