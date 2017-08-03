package com.sapper.telnov.sapperandroidapp.customView.fieldPanel;

/**
 * Created by Sergey on 03.08.2017.
 */

public interface IFieldListener {
    void touchField();
    void gameOver(boolean isWin);
    void scoreChanged(final int score);
}
