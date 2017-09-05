package UI.panels;

import UI.UIElements.LevelDifficulty;

/**
 * Created by @author Telnov Sergey on 05.09.2017.
 */

public interface IScorePanelListener {
    void newScore(long score, LevelDifficulty levelDifficulty);
}
