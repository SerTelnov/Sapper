package game;

import java.util.List;

/**
 * Created by Sergey on 09.07.2017.
 */

public interface IGameListener {
    void cellsChanged(List<Cell> cells);

    void gameOver(boolean isWin);

    void scoreChange(final int counter);

    void gameStart();
}
