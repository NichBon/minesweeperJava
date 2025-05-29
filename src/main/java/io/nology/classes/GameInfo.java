package io.nology.classes;

import java.util.Set;

public class GameInfo {
    private int rows;
    private int columns;
    private int mines;
    private int revealedCount;
    private int[][] mineLocations;
    private boolean win;

    public GameInfo(int rows, int columns, int mines, int[][] mineLocations) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.mineLocations = mineLocations;
    }

}
