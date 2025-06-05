package io.nology;

import java.util.Scanner;
import io.nology.classes.Cell;
import io.nology.classes.TextColor;
import io.nology.utils.Utils;

public class Main {
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner inputScanner = new Scanner(System.in);
        int rows = 0;
        int columns = 0;
        int mines = 0;
        int rowGuess = 0;
        int columnGuess = 0;
        int spacesRemaining = 0;
        int guessReturnValue = 0;
        boolean gameEnd = false;

        System.out.printf(
                "%n%n%n    Welcome to Minesweeper! %nThe game requires you to pick a number of rows, columns and mines(X) to begin.%n%n");

        // USER INPUTS
        rows = Utils.inputAndValidation(rows, "rows", 1, 0, inputScanner, "(2-20 recommended)", TextColor.CYAN);
        columns = Utils.inputAndValidation(columns, "columns", (rows == 1 ? 2 : 1), 0, inputScanner,
                "(2-20 recommended)",
                TextColor.PURPLE);
        mines = Utils.inputAndValidation(mines, "mines", 1, columns * rows - 1, inputScanner,
                "(1 min, " + (rows * columns - 1) + " max)", TextColor.WHITE);

        // GAME START
        int[][] mineCoordinates = Utils.mineCoordinates(rows, columns, mines);
        Cell[][] board = Utils.boardInititation(rows, columns, mineCoordinates);
        spacesRemaining = columns * rows - mines;
        Utils.boardPrint(board, gameEnd);

        // GUESSING
        boolean firstGuess = true;
        while (gameEnd == false) {
            rowGuess = 0;
            columnGuess = 0;
            guessReturnValue = 0;

            System.out.printf("Clear spaces remaining: " + spacesRemaining + "%nMines: " + mines + "%n%n");
            rowGuess = Utils.inputAndValidation(rowGuess, "the row to guess", 1, rows, inputScanner,
                    "between 1 and " + rows, TextColor.CYAN) - 1;
            columnGuess = Utils.inputAndValidation(columnGuess, "the column to guess", 1, columns, inputScanner,
                    "between 1 and " + columns, TextColor.PURPLE) - 1;

            if (firstGuess == true && board[rowGuess][columnGuess].getHasMine() == true) {
                Utils.moveMine(rowGuess, columnGuess, board);
            }
            firstGuess = false;

            guessReturnValue = Utils.guess(rowGuess, columnGuess, board);

            Utils.boardPrint(board, gameEnd);

            if (guessReturnValue == -1) {
                gameEnd = true;
                System.out.printf("%n\u001B[31mBoom!%n%n\u001B[0m");
            } else {
                spacesRemaining -= guessReturnValue;
            }
            if (spacesRemaining <= 0) {
                gameEnd = true;
                System.out.printf("%n\u001B[32mCongratulations!%n%n\u001B[0m");
            }
        }
        Utils.boardPrint(board, gameEnd);
        inputScanner.close();
    }

}

// GameInfo gameInfo = new GameInfo(rows, columns, mines, mineCoordinates);