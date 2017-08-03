package com.sapper.telnov.sapperandroidapp.customView.topPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 02.08.2017.
 */

public class TopPanelSender {
    private static List<ITopPanelListener> listeners = new ArrayList<>();

    public static void addListener(ITopPanelListener listener) {
        listeners.add(listener);
    }

    public static void sayRestartGame() {
        for (ITopPanelListener listener : listeners) {
            listener.restartGame();
        }
    }

    public static void saySetFlagMode() {
        for (ITopPanelListener listener : listeners) {
            listener.setFlagMode();
        }
    }
}
