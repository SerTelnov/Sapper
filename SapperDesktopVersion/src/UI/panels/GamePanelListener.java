package UI.panels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author Telnov Sergey on 21.07.2017.
 */

interface IGamePanelListener {
    void touchField();
}

public class GamePanelListener {
    private List<IGamePanelListener> listeners = new ArrayList<>();

    public void addListener(IGamePanelListener listener) {
        listeners.add(listener);
    }

    public void sayTouchField() {
        for (IGamePanelListener listener : listeners) {
            listener.touchField();
        }
    }
}
