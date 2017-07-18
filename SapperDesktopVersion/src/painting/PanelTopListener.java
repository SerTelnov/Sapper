package painting;

import game.Game;

import java.util.ArrayList;
import java.util.List;

interface IPanelTopListener {
    void restartGame(Game newGame);
    void changeFlagMode(boolean isSetFlagMode);
}

public class PanelTopListener {
    private List<IPanelTopListener> listeners = new ArrayList<>();

    public void addListener(IPanelTopListener listener) {
        listeners.add(listener);
    }

    public void sayRestartGame(Game game) {
        for (IPanelTopListener listener : listeners) {
            listener.restartGame(game);
        }
    }

    public void sayChangeFlagMode(boolean isSetFlagMode) {
        for (IPanelTopListener listener : listeners) {
            listener.changeFlagMode(isSetFlagMode);
        }
    }
}
