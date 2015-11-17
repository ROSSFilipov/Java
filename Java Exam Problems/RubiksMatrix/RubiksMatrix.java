import com.sun.javafx.image.IntPixelGetter;

import java.util.Scanner;

/**
 * Created by user on 11/15/2015.
 */
public class RubiksMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        int[][] matrix = new int[rows][cols];

        FillMatrix(matrix, rows, cols);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] currentCommand = scanner.nextLine().split("\\s+");
            int num1 = Integer.parseInt(currentCommand[0]);
            int moves = Integer.parseInt(currentCommand[2]);

            switch (currentCommand[1]){
                case "up": UpCommand(matrix, num1, moves, rows);
                    break;
                case "down": DownCommand(matrix, num1, moves, rows);
                    break;
                case "left": LeftCommand(matrix, num1, moves, cols);
                    break;
                case "right": RightCommand(matrix, num1, moves, cols);
                    break;
            }
        }

        /*for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }*/

        RearrangeMatrix(matrix, rows, cols);
    }

    private static void RearrangeMatrix(int[][] matrix, int rows, int cols) {
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != counter){
                    Swap(matrix, i, j, counter, rows, cols);
                } else {
                    System.out.println("No swap required");
                }
                counter++;
            }
        }
    }

    private static void Swap(int[][] matrix, int i, int j, int counter, int rows, int cols) {
        for (int k = 0; k < rows; k++) {
            for (int l = 0; l < cols; l++) {
                if (matrix[k][l] == counter){
                    matrix[k][l] ^= matrix[i][j];
                    matrix[i][j] ^= matrix[k][l];
                    matrix[k][l] ^= matrix[i][j];
                    System.out.printf("Swap (%d, %d) with (%d, %d)\n", i, j, k, l);
                }
            }
        }
    }

    private static void RightCommand(int[][] matrix, int num1, int moves, int cols) {
        int[] tempArray = new int[cols];
        int[] matrixLine = new int[cols];

        for (int i = 0; i < cols; i++) {
            matrixLine[i] = matrix[num1][i];
        }

        for (int i = 0; i < cols; i++) {
            int position = (i - moves) % cols;
            if (position < 0){
                position = cols + position;
            }
            tempArray[i] = matrixLine[position];
        }

        for (int i = 0; i < cols; i++) {
            matrix[num1][i] = tempArray[i];
        }
    }

    private static void LeftCommand(int[][] matrix, int num1, int moves, int cols) {
        int[] tempArray = new int[cols];
        int[] matrixLine = new int[cols];

        for (int i = cols - 1; i >= 0; i--) {
            matrixLine[i] = matrix[num1][i];
        }

        for (int i = cols - 1; i >= 0; i--) {
            int position = (i + moves) % cols;
            if (position < 0){
                position = cols + position;
            }
            tempArray[i] = matrixLine[position];
        }

        for (int i = cols - 1; i >= 0; i--) {
            matrix[num1][i] = tempArray[i];
        }
    }

    private static void DownCommand(int[][] matrix, int num1, int moves, int rows) {
        int[] tempArray = new int[rows];
        int[] matrixLine = new int[rows];

        for (int i = rows - 1; i >= 0; i--) {
            matrixLine[i] = matrix[i][num1];
        }

        for (int i = rows - 1; i >= 0; i--) {
            int position = (i + moves) % rows;
            if (position < 0){
                position = rows + position;
            }
            tempArray[position] = matrixLine[i];
        }

        for (int i = rows - 1; i >= 0; i--) {
            matrix[i][num1] = tempArray[i];
        }
    }

    private static void UpCommand(int[][] matrix, int num1, int moves, int rows) {
        int[] tempArray = new int[rows];
        int[] matrixLine = new int[rows];

        for (int i = 0; i < rows; i++) {
            matrixLine[i] = matrix[i][num1];
        }

        for (int i = 0; i < rows; i++) {
            int position = (i + moves) % rows;
            tempArray[i] = matrixLine[position];
        }

        for (int i = 0; i < rows; i++) {
            matrix[i][num1] = tempArray[i];
        }
    }

    private static void FillMatrix(int[][] matrix, int rows, int cols) {
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = counter;
                counter++;
            }
        }
    }
}
