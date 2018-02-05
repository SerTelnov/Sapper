package game;

/**
 * Created by @author Telnov Sergey on 29.07.2017.
 */

public interface IGame {
    void openCell(final int row, final int column);

    void putTagged(final int row, final int column);

    void addListener(IGameListener listener);
}
