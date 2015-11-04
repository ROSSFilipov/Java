import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 10/24/2015.
 */
public class FireTheArrows {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[n][];
        FillMatrix(matrix, n, scanner);

        while (true){
            int movesMade = 0;

            ArrayList<Pair<Integer, Integer>> currentArrows = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (IsArrow(matrix[i][j])){
                        currentArrows.add(new Pair<>(i, j));
                    }
                }
            }

            for (int i = 0; i < currentArrows.size(); i++) {
                if (MadeAMove(matrix, currentArrows.get(i).getKey(), currentArrows.get(i).getValue())){
                    movesMade++;
                }
            }

            if (movesMade == 0){
                break;
            }
        }

        PrintMatrix(matrix, n);
    }

    private static void FillMatrix(char[][] matrix, int n, Scanner scanner) {
        for (int i = 0; i < n; i++) {
            String currentLine = scanner.nextLine();
            matrix[i] = new char[currentLine.length()];
            for (int j = 0; j < currentLine.length(); j++) {
                matrix[i][j] = currentLine.charAt(j);
            }
        }
    }

    private static boolean IsArrow(char c) {
        switch (c){
            case '<': return true;
            case '>': return true;
            case 'v': return true;
            case '^': return true;
            default: return false;
        }
    }

    private static boolean MadeAMove(char[][] matrix, int i, int j) {
        switch (matrix[i][j]){
            case '<': return PositionChanged(matrix, i, j, 0, -1);
            case '>': return PositionChanged(matrix, i, j, 0, 1);
            case 'v': return PositionChanged(matrix, i, j, 1, 0);
            case '^': return PositionChanged(matrix, i, j, -1, 0);
            default: return false;
        }
    }

    private static boolean PositionChanged(char[][] matrix, int i, int j, int i1, int j1) {
        int newI = i + i1;
        int newJ = j + j1;

        boolean isInside = newI >= 0 && newI < matrix.length && newJ >= 0 && newJ < matrix[i].length;

        if (isInside){
            if (matrix[newI][newJ] != 'o'){
                return false;
            } else {
                matrix[newI][newJ] = matrix[i][j];
                matrix[i][j] = 'o';
                return true;
            }
        } else {
            return false;
        }
    }

    private static void PrintMatrix(char[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}