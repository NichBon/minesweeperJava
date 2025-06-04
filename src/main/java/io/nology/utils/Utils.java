package io.nology.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import io.nology.classes.Cell;
import io.nology.classes.TextColor;

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

        int[][] coordinates = mineCoordinates.toArray(new int[mineCoordinates.size()][]);
        return coordinates;
    }

    public static Cell[][] boardInititation(int rows, int columns, int[][] mineCoordinates) {
        Cell[][] board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell();
            }
        }
        for (int[] mine : mineCoordinates) {
            board[mine[0]][mine[1]].setHasMine(true);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = Utils.evaluateCell(i, j, board);
                if (board[i][j].getHasMine() == true) {
                    board[i][j].setValue('X');
                } else {
                    board[i][j].setValue(Integer.toString(value).charAt(0));
                }
            }
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

    public static void boardPrint(Cell[][] board, boolean gameEnd) {
        int rows = board.length;
        int columns = board[0].length;
        char value;
        System.out.printf("%n");
        System.out.printf(TextColor.PURPLE + " ");
        for (int i = 0; i < columns; i++) {
            System.out.printf(" " + (i + 1));
        }
        System.out.printf(TextColor.RESET + "%n");
        printRowDivider(columns);
        for (int i = 0; i < rows; i++) {
            System.out.print(TextColor.CYAN + (i + 1) + TextColor.RESET);
            System.out.printf("|");
            for (int j = 0; j < columns; j++) {
                if (gameEnd == false && board[i][j].getWasChecked() == false) {
                    System.out.print(TextColor.BLUE + "?" + TextColor.RESET);
                } else {
                    value = board[i][j].getValue();
                    switch (value) {
                        case 'X':
                            System.out.print(TextColor.RED + value + TextColor.RESET);
                            break;
                        case '0':
                            System.out.print(TextColor.WHITE + value + TextColor.RESET);
                            break;
                        default:
                            System.out.print(TextColor.YELLOW + value + TextColor.RESET);
                    }
                }
                System.out.printf("|");
            }
            System.out.printf("%n");
            printRowDivider(columns);
        }
        System.out.printf("%n");
    }

    public static void printRowDivider(int columns) {
        System.out.printf(" +");
        for (int i = 0; i < columns; i++) {
            System.out.printf("-+");
        }
        System.out.printf("%n");
    }

    public static int guess(int row, int column, Cell[][] board) {

        if (board[row][column].getValue() == 'X') {
            board[row][column].setWasChecked(true);
            return -1;
        }

        ArrayList<int[]> guessQueue = new ArrayList<>();
        guessQueue.add(new int[] { row, column });
        int rowPosition;
        int columnPosition;
        int checkedCount = 0;

        while (guessQueue.size() > 0) {
            rowPosition = guessQueue.get(0)[0];
            columnPosition = guessQueue.get(0)[1];
            // end early if mine

            // full check
            if (board[rowPosition][columnPosition].getWasChecked() == false) {
                board[rowPosition][columnPosition].setWasChecked(true);
                checkedCount += 1;
            }

            guessQueue.remove(0);
            if (board[rowPosition][columnPosition].getValue() == '0') {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((i == 0 && j == 0) || rowPosition + i < 0 || rowPosition + i > board.length - 1
                                || columnPosition + j < 0 || columnPosition + j > board[0].length - 1)
                            continue;

                        if (board[rowPosition + i][columnPosition + j].getWasChecked() == false) {
                            if (board[rowPosition + i][columnPosition + j].getValue() == '0') {
                                guessQueue.add(new int[] { rowPosition + i, columnPosition + j });
                            }
                            board[rowPosition + i][columnPosition + j].setWasChecked(true);
                            checkedCount += 1;
                        }
                    }
                }
            }
        }
        return checkedCount;

    }

    public static int inputAndValidation(int value, String valueName, int lower, int upper,
            Scanner inputScanner,
            String validInputDescription,
            String color) {
        while (value < lower || (upper > lower && value > upper)) {
            System.out.println("Please enter the number of " + color + valueName + TextColor.RESET + " "
                    + validInputDescription + ":");
            String userInput = inputScanner.nextLine();
            if (userInput.matches("^\\d+$") && !userInput.equals("0")) {
                value = Integer.parseInt(userInput);
                System.out.printf(valueName + ": " + value + "%n%n");
            } else {
                System.out.printf(
                        "Invalid input. Please enter a valid input (must be a number). "
                                + validInputDescription + ") %n%n");
            }
        }

        return value;
    }

    public static void moveMine(int rowGuess, int columnGuess, Cell[][] board) {
        board[rowGuess][columnGuess].setHasMine(false);
        int mineRow = rowGuess;
        int mineColumn = columnGuess;
        boolean moveMine = true;
        while (moveMine == true) {
            if (mineRow < board.length - 1) {
                mineRow += 1;
                if (board[mineRow][mineColumn].getHasMine() == false) {
                    board[mineRow][mineColumn].setHasMine(true);
                    board[mineRow][mineColumn].setValue('X');
                    board[rowGuess][columnGuess].setValue(
                            Integer.toString(Utils.evaluateCell(rowGuess, columnGuess, board)).charAt(0));
                    moveMine = false;

                } else if (mineColumn < board[0].length - 1) {
                    mineColumn += 1;
                    mineRow = 0;
                    if (board[mineRow][mineColumn].getHasMine() == false) {
                        board[mineRow][mineColumn].setHasMine(true);
                        board[mineRow][mineColumn].setValue('X');
                        board[rowGuess][columnGuess].setValue(
                                Integer.toString(Utils.evaluateCell(rowGuess, columnGuess, board)).charAt(0));
                        moveMine = false;
                    }

                } else {
                    mineColumn = 0;
                    mineRow = 0;
                    if (board[mineRow][mineColumn].getHasMine() == false) {
                        board[mineRow][mineColumn].setHasMine(true);
                        board[mineRow][mineColumn].setValue('X');
                        board[rowGuess][columnGuess].setValue(
                                Integer.toString(Utils.evaluateCell(rowGuess, columnGuess, board)).charAt(0));
                        moveMine = false;
                    }
                }
            }
        }
    }
}