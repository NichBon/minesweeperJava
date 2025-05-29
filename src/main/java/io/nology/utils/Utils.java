package io.nology.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.nology.classes.Cell;

public class Utils {
    public static int[][] mineCoordinates(int rows, int columns, int mines) {
        // mines number validated before passing in
        Set<int[]> mineCoordinates = new HashSet<int[]>();

        while (mineCoordinates.size() < mines) {
            boolean add = true;
            int rowRef = (int) Math.floor(Math.random() * rows);
            int columnRef = (int) Math.floor(Math.random() * columns);
            for (int[] mine : mineCoordinates) {
                if (mine[0] == rowRef && mine[1] == columnRef) {
                    add = false;
                }
            }
            if (add == true) {
                mineCoordinates.add(new int[] { rowRef, columnRef });
            }
        }

        System.out.println(Arrays.deepToString(mineCoordinates.toArray()));
        int[][] coordinates = mineCoordinates.toArray(new int[mineCoordinates.size()][]);
        return coordinates;
    }

    public static Cell[][] boardInititation(int rows, int columns, int[][] mineCoordinates) {
        Cell[][] board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell(); // Initialize each Cell
            }
        }
        for (int[] mine : mineCoordinates) {
            board[mine[0]][mine[1]].setHasMine(true);
            board[mine[0]][mine[1]].setValue('X');
        }
        return board;
    }

    public static int evaluateCell(int rowPosition, int columnPosition, Cell[][] board) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i == 0 && j == 0) || rowPosition + i < 0 || rowPosition + i > board.length - 1
                        || columnPosition + j < 0 || columnPosition + j > board[0].length - 1)
                    continue;
                if (board[rowPosition + i][columnPosition + j].getHasMine() == true)
                    count += 1;
            }
        }
        return count;
    }

}
