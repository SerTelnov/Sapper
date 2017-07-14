package game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Collections.shuffle;

/**
 * Created by Sergey on 07.07.2017.
 */

public class Field {
    private final int counterBomb;
    protected Cell[][] field;
    protected final int row, column;
    private List<Pair<Integer, Integer>> indexes = new ArrayList<>();

    public Field(final int row, final int column, final int counterBomb) {
        this.counterBomb = counterBomb;
        this.row = row;
        this.column = column;
        onFieldCreated(row, column);
    }

    public Cell getCell(final int row, final int column) { return field[row][column]; }

    public Field(final Field fl) {
        this.counterBomb = fl.getCounterBomb();
        this.row = fl.row;
        this.column = fl.column;
        onFieldCreated(this.row, this.column);
    }

    public int getRow() { return row; }
    public int getColumn() { return column; }
    public Cell[][] getField() { return field; }
    public int getCounterBomb() { return counterBomb; }
    public int getNumber(final int row, final int column) { return field[row][column].getNumber(); }
    public boolean isBomb(final int row, final int column) { return field[row][column].isBomb(); }
    public boolean isOutOfBounds(final int row, final int column) {
        return row < 0 || column < 0 ||
                row >= field.length ||
                column >= field[0].length;
    }

    public void setTagged(final int row, final int column) {
        field[row][column].isOpened = true;
        field[row][column].isTagged = true;
    }
    public void removeTagged(final int row, final int column) {
        field[row][column].isOpened = false;
        field[row][column].isTagged = false;
    }

    protected void onFieldCreated(final int row, final int column) {
        setField(row, column, counterBomb);
    }

    private void incPoint(final int row, final int column) {
        if (isOutOfBounds(row, column) || isBomb(row, column))
            return;
        field[row][column].incNumber();
    }

    private void incPoints(final int row, final int column) {
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                if (rowIndex != 0 || columnIndex != 0) {
                    incPoint(row + rowIndex, column + columnIndex);
                }
            }
        }
    }

    protected Pair<Integer, Integer> getBombLocation(int bombIndex) {
        return indexes.remove(0);
    }

    private void setBombs(int counterBomb) {
        while (counterBomb != 0) {
            Pair<Integer, Integer> pr = getBombLocation(counterBomb - 1);
            int i = pr.getKey();
            int j = pr.getValue();
            field[i][j].setBomb();
            incPoints(i, j);
            counterBomb--;
        }
    }

    protected void setField(final int row, final int column, int counterBomb) {
        assert row * column < counterBomb : "too many bombs";
        field = new Cell[row][column];
        for (int i = 0; i != row; i++) {
            for (int j = 0; j != column; j++) {
                indexes.add(new Pair<>(i, j));
                field[i][j] = new Cell(i, j);
            }
        }
        shuffle(indexes);
        setBombs(counterBomb);
        indexes.clear();
    }
}