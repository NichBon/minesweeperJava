package io.nology;

import java.util.Scanner;
import java.util.Set;

import io.nology.classes.Board;
import io.nology.classes.Cell;
import io.nology.classes.GameInfo;
import io.nology.utils.Utils;

public class Main {
    public static void main(String[] args) {

        int rows = 3;
        int columns = 3;
        int mines = 9;
        int[][] mineCoordinates = Utils.mineCoordinates(rows, columns, mines);
        Cell[][] board = Utils.boardInititation(rows, columns, mineCoordinates);
        System.out.printf("+-+-+-+-+-+");

        for (int i = 0; i < rows; i++) {
            System.out.printf("%n|");
            for (int j = 0; j < columns; j++) {
                System.out.print(Utils.evaluateCell(i, j, board) + "|");
            }
            System.out.printf("%n+-+-+-+-+-+");
        }

        GameInfo gameInfo = new GameInfo(rows, columns, mines, mineCoordinates);

    }

}

// Scanner inputScanner = new Scanner(System.in);
// char userInput = 'a';

// while (userInput != 'n') {

// System.out.println("Enter n to end");
// userInput = inputScanner.nextLine().charAt(0);
// System.out.println("detected: " + userInput);
// }

// System.out.println("ending");

// inputScanner.close();