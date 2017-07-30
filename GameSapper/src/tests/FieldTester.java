package tests;

import game.Cell;
import game.Field;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Sergey on 08.07.2017.
 */

public class FieldTester extends Field {
    protected List<Cell> bombs;
    public FieldTester(int row, int column, List<Cell> bombs) {
        super(row, column, bombs.size());
        this.bombs = bombs;
        this.createField();
    }

    @Override
    protected Cell getBombLocation(int bombIndex) {
        return bombs.get(bombIndex);
    }
}
