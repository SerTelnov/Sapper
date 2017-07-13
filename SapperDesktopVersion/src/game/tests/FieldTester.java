package game.tests;

import game.Field;
import game.Game;
import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Created by Sergey on 08.07.2017.
 */

public class FieldTester extends Game {
    protected ArrayList<Pair<Integer, Integer>> bombs;
    public FieldTester(int row, int column, ArrayList<Pair<Integer, Integer>> bombs) {
        super(row, column, bombs.size());
        this.bombs = bombs;
        this.setField(row, column, bombs.size());
    }

    @Override
    protected Pair<Integer, Integer> getBombLocation(int bombIndex) {
        return bombs.remove(bombIndex);
    }
    @Override
    protected void onFieldCreated(final int row, final int column) { }
}
