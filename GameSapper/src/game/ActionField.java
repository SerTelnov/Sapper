package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Sergey on 07.07.2017.
 */

public class ActionField extends Field implements IGame {
    private List<IGameListener> listeners = new ArrayList<>();
    private int counterTagged = 0;
    private int counterCorrectTagged = 0;
    private boolean gameFinished = false;
    private List<Cell> currChangedCells = new ArrayList<>();

    public ActionField(final int row, final int column, final int counterBomb) {
        super(row, column, counterBomb);
    }

    public void openCell(final int row, final int column) {
        if (gameFinished || isOutOfBounds(row, column)) return;
        if (!fieldCreated) {
            openFirstCell(row, column);
            sayGameStart();
        }
        Cell cell = this.getCell(row, column);
        if (cell.isOpened) {
            if (cell.isNumber() && !cell.isTagged) {
                openAdjacentCells(cell);
            }
        } else if (cell.isBomb()) {
            openBombCell(cell);
        } else if (cell.isNumber()) {
            openCell(cell);
        } else if (cell.isEmptyPoint()) {
            openEmptyCells(cell);
        }
        sayCellsChanged();
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

        this.currChangedCells.add(cell);

        counterTagged += add;
        if (cell.isBomb())
            counterCorrectTagged += add;
        if (counterCorrectTagged == getCounterBomb() &&
                counterCorrectTagged == counterTagged) {
            sayGameOver(true);
        }
        sayScoreChange();
        sayCellsChanged();
    }

    public void addListener (IGameListener listener) {
        listeners.add(listener);
    }

    protected void setGameFinished() { gameFinished = true; }

    private void sayGameOver(boolean isWin) {
        setGameFinished();
        for (IGameListener listener : listeners) {
            listener.gameOver(isWin);
        }
    }

    private void sayCellsChanged() {
        if (currChangedCells.isEmpty())
            return;
        for (IGameListener listener : listeners) {
            listener.cellsChanged(currChangedCells);
        }
        currChangedCells.clear();
    }

    private void sayScoreChange() {
        for (IGameListener listener : listeners) {
            listener.scoreChange(counterTagged);
        }
    }

    private void sayGameStart() {
        for (IGameListener listener : listeners) {
            listener.gameStart();
        }
    }

    private HashSet<Cell> getStartCells(final int row, final int column) {
        HashSet<Cell> startCells = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int curRow = row + i;
                int curColumn = column + j;
                if (!isOutOfBounds(curRow, curColumn)) {
                    startCells.add(getCell(curRow, curColumn));
                }
            }
        }
        return startCells;
    }

    protected void openFirstCell(final int row, final int column) {
        HashSet<Cell> startCells = getStartCells(row, column);
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
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                int curRow = cell.row + rowIndex;
                int curColumn = cell.column + columnIndex;
                if (columnIndex == 0 && rowIndex == 0 || isOutOfBounds(curRow, curColumn))
                    continue;
                Cell cur = getCell(curRow, curColumn);
                if (cur.isOpened)
                    continue;
                if (cur.isEmptyPoint()) {
                    openEmptyCells(cur);
                } else if (cur.isNumber()) {
                    openCell(cur);
                }
            }
        }
    }

    private void openEmptyCells(Cell cell) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int curRow = cell.row + i;
                int curColumn = cell.column + j;
                if (isOutOfBounds(curRow, curColumn))
                    continue;
                Cell curCell = getCell(curRow, curColumn);
                if (curCell.isOpened)
                    continue;
                openCell(curCell);
                if (curCell.isEmptyPoint()) {
                    openEmptyCells(curCell);
                }
            }
        }
    }

    private void openCell(Cell cell) {
        this.currChangedCells.add(cell);
        cell.openCell();
    }

    private void openBombCell(Cell cell) {
        openCell(cell);
        sayGameOver(false);
    }
}