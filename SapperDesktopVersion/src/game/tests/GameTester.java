package game.tests;

import game.Game;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Sergey on 13.07.2017.
 */

public class GameTester extends FieldTester {

    public GameTester(int row, int column, ArrayList<Pair<Integer, Integer>> bombs) {
        super(row, column, bombs);
    }
}
