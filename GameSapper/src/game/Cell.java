package game;

/**
 * Created by Sergey on 07.07.2017.
 */

public class Cell {
    public Cell(final int row, final int column) {
        this.row = row;
        this.column = column;
    }
    public Cell(final int number) {
        this.number = number;
        this.row = 0;
        this.column = 0;
    }
    private int number;
    public final int row, column;
    public boolean isOpened;
    public boolean isTagged;
    public int getNumber() { return number; }
    public boolean isBomb() { return number == 9; }
    public boolean isEmptyPoint() { return number == 0; }
    public void setBomb() { number = 9; }
    public boolean isNumber() {
        return number >= 1 && number <= 8;
    }
    public void incNumber() { number++; }
    public void openCell() { isOpened = true; }
}