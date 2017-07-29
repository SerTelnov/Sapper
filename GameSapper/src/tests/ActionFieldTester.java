package tests;

import game.ActionField;
import javafx.util.Pair;

/**
 * Created by Sergey on 13.07.2017.
 */

public class ActionFieldTester extends ActionField {

    private Pair<Integer, Integer> bombLocation;

    public ActionFieldTester(ActionFieldTestInfo testInfo) {
        super(testInfo.row, testInfo.column, 1);
        this.bombLocation = new Pair<>(testInfo.bombLocation.row, testInfo.bombLocation.column);
        openFirstCell(testInfo.startCellLocation.row, testInfo.startCellLocation.column);
    }

    @Override
    protected Pair<Integer, Integer> getBombLocation(int bombIndex) {
        return bombLocation;
    }
}
