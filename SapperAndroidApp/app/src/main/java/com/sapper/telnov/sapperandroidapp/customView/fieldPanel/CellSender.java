package com.sapper.telnov.sapperandroidapp.customView.fieldPanel;

import java.util.ArrayList;
import java.util.List;

import game.Cell;

/**
 * Created by Sergey on 31.07.2017.
 */

public class CellSender {
    public boolean isWin = false;
    private boolean isFlagMode = false;
    private boolean gameFinished = false;

    private List<ICellListener> listeners = new ArrayList<>();

    public void setFlagMode() {
        isFlagMode = !isFlagMode;
    }

    public boolean isGameFinished() { return gameFinished; }

    public boolean isSetFlagMode() { return isFlagMode; }
    public void restartCellSender() {
        isFlagMode = false;
        isWin = false;
    }

    public void setGameOver(boolean isWin) {
        this.gameFinished = true;
        this.isWin = isWin;
    }

    public void addListener(ICellListener listener) {
        listeners.add(listener);
    }

    public void sayOpenCell(Cell cell) {
        for (ICellListener listener : listeners) {
            listener.openCell(cell);
        }
    }

    public void sayPutTagged(Cell cell) {
        for (ICellListener listener : listeners) {
            listener.putTagged(cell);
        }
    }
}
