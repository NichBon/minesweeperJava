package io.nology.classes;

public class Board {
    private byte rows;
    private byte columns;
    private byte mines;
    private byte[][] mineLocations;

    public Board(byte rows, byte columns, byte mines, byte[][] mineLocations) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.mineLocations = mineLocations;
    }

}
