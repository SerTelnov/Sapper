package tests;

import game.Field;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Sergey on 08.07.2017.
 */

public class FieldTester extends Field {
    protected List<Pair<Integer, Integer>> bombs;
    public FieldTester(int row, int column, List<Pair<Integer, Integer>> bombs) {
        super(row, column, bombs.size());
        this.bombs = bombs;
        this.createField();
    }

    @Override
    protected Pair<Integer, Integer> getBombLocation(int bombIndex) {
        return bombs.get(bombIndex);
    }
}
