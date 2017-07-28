package game;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Sergey on 07.07.2017.
 */

public class ActionField extends Field {
    private List<IGameListener> listeners = new ArrayList<>();
    private int counterTagged = 0;
    private int counterCorrectTagged = 0;

    public ActionField(final int row, final int column, final int counterBomb) {
        super(row, column, counterBomb);
    }

    public void openCell(final int row, final int column) {
        if (isOutOfBounds(row, column)) return;
        if (!fieldCreated) {
            openFirstCell(row, column);
        }
        Cell cell = this.getCell(row, column);
        if (cell.isOpened) {
            if (cell.isNumber() && !cell.isTagged) {
                openAdjacentCells(cell);
            }
        } else if (cell.isBomb()) {
            openBombCell(cell);
        } else if (cell.isNumber()) {
            openNumberCell(cell);
            sayCellChange(cell);
        } else if (cell.isEmptyPoint()) {
            openEmptyCell(cell);
            sayCellChange(cell);
        }
    }

    public void putTagged(final int row, final int column) {
        if (isOutOfBounds(row, column)) return;
        Cell cell = field[row][column];
        if (!cell.isTagged && cell.isOpened) return;
        int add = 1;
        if (!cell.isTagged) {
            setTagged(cell.row, cell.column);
        } else {
            add = -1;
            removeTagged(cell.row, cell.column);
        }
        counterTagged += add;
        if (cell.isBomb())
            counterCorrectTagged += add;
        if (counterCorrectTagged == getCounterBomb() &&
                counterCorrectTagged == counterTagged) {
            sayGameOver(true);
        }
        sayScoreChange();
        sayCellChange(cell);
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

    private void sayScoreChange() {
        for (IGameListener listener : listeners) {
            listener.scoreChange(counterTagged);
        }
    }

    private HashSet<Pair<Integer, Integer>> getStartCells(final int row, final int column) {
        HashSet<Pair<Integer, Integer>> startCells = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int curRow = row + i;
                int curColumn = column + j;
                if (!isOutOfBounds(curRow, curColumn)) {
                    startCells.add(new Pair<>(curRow, curColumn));
                }
            }
        }
        return startCells;
    }

    protected void openFirstCell(final int row, final int column) {
        HashSet<Pair<Integer, Integer>> startCells = getStartCells(row, column);
        createField(startCells);
    }

    private boolean isCorrectFlagsCounter(Cell cell) {
        int counterFlags = 0;
        boolean hasWrongFlag = false;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int curRow = cell.row + i;
                int curColumn = cell.column + j;
                if (isOutOfBounds(curRow, curColumn) || (i == 0 && j == 0))
                    continue;
                Cell curCell = getCell(curRow, curColumn);
                if (curCell.isTagged) {
                    if (!curCell.isBomb()) {
                        hasWrongFlag = true;
                    }
                    counterFlags++;
                }
            }
        }
        if (counterFlags == cell.getNumber()) {
            if (hasWrongFlag) {
                sayGameOver(false);
            }
            return !hasWrongFlag;
        }
        return false;
    }

    private void openAdjacentCells(Cell cell) {
        if (!isCorrectFlagsCounter(cell)) return;
        boolean cellChanged = false;
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                int curRow = cell.row + rowIndex;
                int curColumn = cell.column + columnIndex;
                if (columnIndex == 0 && rowIndex == 0 || isOutOfBounds(curRow, curColumn))
                    continue;
                Cell cur = getCell(curRow, curColumn);
                if (cur.isOpened)
                    continue;
                cellChanged = true;
                if (cur.isEmptyPoint()) {
                    openEmptyCell(cur);
                } else if (cur.isNumber()) {
                    openNumberCell(cur);
                }
            }
        }
        if (cellChanged) {
            sayCellChange(cell);
        }
    }

    private void openEmptyCells(Cell cell) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int curRow = cell.row + i;
                int curColumn = cell.column + j;
                if ((i == 0 && j == 0) || isOutOfBounds(curRow, curColumn))
                    continue;
                Cell curCell = getCell(curRow, curColumn);
                if (curCell.isOpened)
                    continue;
                curCell.openCell();
                if (curCell.isEmptyPoint()) {
                    openEmptyCell(curCell);
                } else if (curCell.isNumber()) {
                    openNumberCell(curCell);
                }
            }
        }
    }

    private void openEmptyCell(Cell cell) {
        openEmptyCells(cell);
    }

    private void openNumberCell(Cell cell) {
        cell.openCell();
    }

    private void openBombCell(Cell cell) {
        cell.openCell();
        sayGameOver(false);
    }

}