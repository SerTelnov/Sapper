package UI.panels;

import game.ActionField;

import java.util.ArrayList;
import java.util.List;

interface IPanelTopListener {
    void restartGame(ActionField newActionField);
    void changeFlagMode(boolean isSetFlagMode);
}

public class PanelTopListener {
    private List<IPanelTopListener> listeners = new ArrayList<>();

    public void addListener(IPanelTopListener listener) {
        listeners.add(listener);
    }

    public void sayRestartGame(ActionField actionField) {
        for (IPanelTopListener listener : listeners) {
            listener.restartGame(actionField);
        }
    }

    public void sayChangeFlagMode(boolean isSetFlagMode) {
        for (IPanelTopListener listener : listeners) {
            listener.changeFlagMode(isSetFlagMode);
        }
    }
}
