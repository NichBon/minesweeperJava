package io.nology;

import java.util.Scanner;
import java.util.Set;

import io.nology.classes.Board;
import io.nology.classes.Cell;
import io.nology.classes.GameInfo;
import io.nology.utils.Utils;

public class Main {
    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);
        String userInput;
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
        rows = Utils.inputAndValidation(rows, "rows", 1, 0, inputScanner, "(2-20 recommended)");
        columns = Utils.inputAndValidation(columns, "columns", 1, 0, inputScanner, "(2-20 recommended)");
        mines = Utils.inputAndValidation(mines, "mines", 1, columns * rows - 1, inputScanner,
                "(1 min, " + (rows * columns - 1) + " max)");

        // GAME START
        int[][] mineCoordinates = Utils.mineCoordinates(rows, columns, mines);
        Cell[][] board = Utils.boardInititation(rows, columns, mineCoordinates);
        spacesRemaining = columns * rows - mines;
        Utils.boardPrint(board, gameEnd);

        // GUESSING
        while (gameEnd == false) {
            rowGuess = 0;
            columnGuess = 0;
            guessReturnValue = 0;

            System.out.printf("Clear spaces remaining: " + spacesRemaining + "%n%n");
            rowGuess = Utils.inputAndValidation(rowGuess, "the row to guess", 1, rows, inputScanner,
                    "(minimum 1, maximum " + rows + ")");
            columnGuess = Utils.inputAndValidation(columnGuess, "the column to guess", 1, columns, inputScanner,
                    "(minimum 1, maximum " + columns + ")");

            guessReturnValue = Utils.guess(rowGuess, columnGuess, board);

            Utils.boardPrint(board, gameEnd);

            if (guessReturnValue == -1) {
                gameEnd = true;
                System.out.printf("%nboom! %n%n");
            } else {
                spacesRemaining -= guessReturnValue;
            }
            if (spacesRemaining <= 0) {
                gameEnd = true;
                System.out.printf("%nCongratulations! %n%n");
            }
        }
        Utils.boardPrint(board, gameEnd);
        inputScanner.close();
    }

}

// GameInfo gameInfo = new GameInfo(rows, columns, mines, mineCoordinates);