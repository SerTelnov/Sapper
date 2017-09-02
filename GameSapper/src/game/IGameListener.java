package game;

/**
 * Created by Sergey on 09.07.2017.
 */

public interface IGameListener {
    void cellChange(Cell c);
    void gameOver(boolean isWin);
    void scoreChange(final int counter);
    void gameStart();
}
