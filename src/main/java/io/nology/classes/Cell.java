package io.nology.classes;

public class Cell {

    private boolean hasMine;
    private boolean wasChecked;
    private char value;

    public Cell(boolean hasMine) {
        this.hasMine = hasMine;
        this.wasChecked = false;
        this.value = '?';
    }

    public boolean getHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean getWasChecked() {
        return wasChecked;
    }

    public void setWasChecked(boolean wasChecked) {
        this.wasChecked = wasChecked;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

}
