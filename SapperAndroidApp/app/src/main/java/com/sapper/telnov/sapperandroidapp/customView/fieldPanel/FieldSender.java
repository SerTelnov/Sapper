package com.sapper.telnov.sapperandroidapp.customView.fieldPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 03.08.2017.
 */

public class FieldSender {
    private static List<IFieldListener> listeners = new ArrayList<>();

    public static void addListener(IFieldListener listener) {
        listeners.add(listener);
    }

    public static void sayTouchField() {
        for (IFieldListener listener : listeners) {
            listener.touchField();
        }
    }

    public static void sayGameOver(boolean isWin) {
        for (IFieldListener listener : listeners) {
            listener.gameOver(isWin);
        }
    }

    public static void sayScoreChanged(final int score) {
        for (IFieldListener listener : listeners) {
            listener.scoreChanged(score);
        }
    }
}
