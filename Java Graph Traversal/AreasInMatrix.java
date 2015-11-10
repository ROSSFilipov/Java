import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by user on 11/10/2015.
 */
public class AreasInMatrix {
    private static int areaSizeCounter = 0;
    private static char markSymbol = '$';
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        Pattern inputPattern = Pattern.compile(".*?([0-9])");
        Matcher matcher = inputPattern.matcher(firstLine);
        matcher.find();

        int numberOfRows = Integer.parseInt(matcher.group(1));

        char[][] matrix = new char[numberOfRows][];

        FillMatrix(matrix, numberOfRows, scanner);

        Map<Character, List<Integer>> areasData = new LinkedHashMap<>();

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != markSymbol){
                    char currentChar = matrix[i][j];
                    DFS(matrix, i, j, matrix[i][j]);
                    if (!areasData.containsKey(currentChar)){
                        areasData.put(currentChar, new ArrayList<>());
                    }

                    areasData.get(currentChar).add(areaSizeCounter);
                    areaSizeCounter = 0;
                }
            }
        }

        for (Map.Entry<Character, List<Integer>> currentArea : areasData.entrySet()){
            System.out.printf("Current symbol: %c, area sizes: %s.\n",
                    currentArea.getKey(), currentArea.getValue());
        }
    }

    private static void DFS(char[][] matrix, int i, int j, char currentCell) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length){
            return;
        }

        if (matrix[i][j] == currentCell){
            areaSizeCounter++;
        }

        if (matrix[i][j] != currentCell){
            return;
        }

        matrix[i][j] = markSymbol;

        DFS(matrix, i, j + 1, currentCell);
        DFS(matrix, i, j - 1, currentCell);
        DFS(matrix, i - 1, j, currentCell);
        DFS(matrix, i + 1, j, currentCell);
    }

    private static void FillMatrix(char[][] matrix, int numberOfRows, Scanner scanner) {
        for (int i = 0; i < numberOfRows; i++) {
            String currentLine = scanner.nextLine();
            int currentLength = currentLine.length();
            matrix[i] = new char[currentLength];
            for (int j = 0; j < currentLength; j++) {
                matrix[i][j] = currentLine.charAt(j);
            }
        }
    }
}
