package game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Sergey on 07.07.2017.
 */

public class Game extends Field {
    private List<IGameListener> listeners = new ArrayList<>();
    private int counterTagged;
    private int counterCorrectTagged;

    public Game(final int row, final int column, final int counterBomb) {
        super(row, column, counterBomb);
        counterTagged = 0;
        counterCorrectTagged = 0;
    }

    public void addListener (IGameListener listener) {
        listeners.add(listener);
    }

    private void sayGameOver(boolean isWin) {
        for (IGameListener listener : listeners) {
            listener.gameOver(isWin);
        }
    }

    private void sayCellChange(Cell c) {
        for (IGameListener listener : listeners) {
            listener.cellChange(c);
        }
    }

    private void sayCounterChange() {
        for (IGameListener listener : listeners) {
            listener.scoreChange(Math.max(0, getCounterBomb() - counterTagged));
        }
    }

    private void openEmptyCells(Cell c) {
        Queue<Cell> q = new ArrayDeque<>();
        q.add(c);
        field[c.row][c.column].isOpened = true;
        while (!q.isEmpty()) {
            Cell cur = q.remove();
            for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
                for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                    int row = cur.row + rowIndex;
                    int column = cur.column + columnIndex;
                    if (rowIndex == 0 && columnIndex == 0 || isOutOfBounds(row, column))
                        continue;
                    Cell next = field[row][column];
                    if (!next.isOpened && !next.isBomb()) {
                        field[row][column].isOpened = true;
                        if (next.isEmptyPoint()) {
                            q.add(next);
                        }
                    }
                }
            }
        }
    }

    private void openCell(Cell c) {
        if (c.isNumber()) {
            if (!c.isOpened) {
                field[c.row][c.column].isOpened = true;
            }
        } else {
            openEmptyCells(c);
        }
    }

    private boolean isCorrectBombCounter(Cell c) {
        int counter = 0;
        boolean haveWrongTagged = false;
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                int row = c.row + rowIndex;
                int column = c.column + columnIndex;
                if (columnIndex == 0 && rowIndex == 0 || isOutOfBounds(row, column))
                    continue;
                Cell cur = field[row][column];
                if (cur.isTagged) {
                    if (!cur.isBomb()) {
                        haveWrongTagged = true;
                    }
                    counter++;
                }
            }
        }
        if (counter >= c.getNumber()) {
            if (haveWrongTagged) {
                sayGameOver(false);
                return false;
            }
            return true;
        }
        return false;
    }

    private void openAdjacentCells(Cell c) {
        if (!isCorrectBombCounter(c))
            return;
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                int row = c.row + rowIndex;
                int column = c.column + columnIndex;
                if (columnIndex == 0 && rowIndex == 0 || isOutOfBounds(row, column))
                    continue;
                Cell cur = field[row][column];
                if (cur.isEmptyPoint()) {
                    openEmptyCells(cur);
                } else if (cur.isNumber()) {
                    cur.isOpened = true;
                }
            }
        }
        sayCellChange(c);
    }

    public void openCell(final int row, final int column) {
        if (isOutOfBounds(row, column)) return;
        Cell c = field[row][column];
        if (c.isOpened) {
            if (c.isNumber() && !c.isTagged) {
                openAdjacentCells(c);
            }
        } else if (c.isBomb()) {
            field[c.row][c.column].isOpened = true;
            sayGameOver(false);
        } else {
            openCell(c);
            sayCellChange(c);
        }
    }

    public void putTagged(final int row, final int column) {
        if (isOutOfBounds(row, column)) return;
        Cell c = field[row][column];
        if (!c.isTagged && c.isOpened) return;
        int add;
        if (!c.isTagged) {
            add = 1;
            setTagged(c.row, c.column);
        } else {
            add = -1;
            removeTagged(c.row, c.column);
        }
        counterTagged += add;
        if (c.isBomb())
            counterCorrectTagged += add;
        if (counterCorrectTagged == getCounterBomb() &&
                counterCorrectTagged == counterTagged) {
            sayGameOver(true);
        }
        sayCounterChange();
        sayCellChange(c);
    }
}