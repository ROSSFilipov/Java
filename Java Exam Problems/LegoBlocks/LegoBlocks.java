import com.sun.javafx.image.IntPixelGetter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by user on 10/31/2015.
 */
public class LegoBlocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> normalLines = new ArrayList<>();
        List<List<Integer>> reversedLines = new ArrayList<>();
        TakeInput(normalLines, scanner, n);
        TakeInput(reversedLines, scanner, n);

        List<List<Integer>> joinedRows = new ArrayList<>();

        JoinRows(normalLines, reversedLines,joinedRows);

        if (IsMatrix(joinedRows)){
            for (int i = 0; i < joinedRows.size(); i++) {
                System.out.println(joinedRows.get(i));
            }
        } else {
            int numberOfCells = 0;
            for (int i = 0; i < joinedRows.size(); i++) {
                numberOfCells += joinedRows.get(i).size();
            }
            System.out.printf("The total number of cells is: %d", numberOfCells);
        }
    }

    private static void TakeInput(List<List<Integer>> currentArray, Scanner scanner, int n) {
        for (int i = 0; i < n; i++) {
            currentArray.add(Arrays.asList(scanner.nextLine().split("\\s+"))
                    .stream()
                    .filter(s -> !s.equals(""))
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList()));
        }
    }

    private static void JoinRows(List<List<Integer>> normalLines, List<List<Integer>> reversedLines, List<List<Integer>> joinedRows) {
        for (int i = 0; i < normalLines.size(); i++) {
            Collections.reverse(reversedLines.get(i));
            normalLines.get(i).addAll(reversedLines.get(i));
            joinedRows.add(normalLines.get(i));
        }
    }

    private static boolean IsMatrix(List<List<Integer>> joinedRows) {
        for (int i = 0; i < joinedRows.size() - 1; i++) {
            if (joinedRows.get(i).size() != joinedRows.get(i + 1).size()){
                return false;
            }
        }

        return true;
    }
}

