import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

import java.rmi.MarshalledObject;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 11/10/2015.
 */
public class CyclesInAGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> currentGraph = new LinkedHashMap<>();

        Pattern edgePattern = Pattern.compile("([a-zA-Z]+)[ -]+([a-zA-Z]+)");
        while (true){
            String currentLine = scanner.nextLine();

            if (currentLine.equals("")){
                break;
            }

            Matcher matcher = edgePattern.matcher(currentLine);
            if (matcher.find()) {
                String firstNode = matcher.group(1);
                String secondNode = matcher.group(2);

                if (!currentGraph.containsKey(firstNode)){
                    currentGraph.put(firstNode, new ArrayList<>());
                }

                if (!currentGraph.containsKey(secondNode)){
                    currentGraph.put(secondNode, new ArrayList<>());
                }

                currentGraph.get(firstNode).add(secondNode);
                currentGraph.get(secondNode).add(firstNode);
            }
        }

        if (IsCyclic(currentGraph)){
            System.out.println("Acyclic: No");
        } else {
            System.out.println("Acyclic: Yes");
        }
    }

    private static boolean IsCyclic(Map<String, List<String>> currentGraph) {
        List<Map.Entry<String, List<String>>> entries = new ArrayList<>(currentGraph.entrySet());
        HashSet<String> visited = new HashSet<>();
        for (int i = 0; i < entries.size(); i++) {
            if (!visited.contains(entries.get(i).getKey())){
                if (HasCycle(currentGraph, entries.get(i).getKey(), visited, "0")){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean HasCycle(Map<String, List<String>> currentGraph, String currentNode, HashSet<String> visited, String parent) {
        visited.add(currentNode);

        for (String adjacentNode : currentGraph.get(currentNode)){
            if (visited.contains(adjacentNode)){
                if (!adjacentNode.equals(parent)){
                    return true;
                }
            } else {
                if (HasCycle(currentGraph, adjacentNode, visited, currentNode)){
                 return true;
                }
            }
        }

        return false;
    }
}
