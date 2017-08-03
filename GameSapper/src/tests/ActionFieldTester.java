package tests;

import game.ActionField;
import game.Cell;
import javafx.util.Pair;

/**
 * Created by Sergey on 13.07.2017.
 */

public class ActionFieldTester extends ActionField {

    private Cell bombLocation;

    public ActionFieldTester(ActionFieldTestInfo testInfo) {
        super(testInfo.row, testInfo.column, 1);
        this.bombLocation = getCell(testInfo.bombLocation.row, testInfo.bombLocation.column);
        openFirstCell(testInfo.startCellLocation.row, testInfo.startCellLocation.column);
    }

    @Override
    protected void setGameFinished() { }

    @Override
    protected Cell getBombLocation(int bombIndex) {
        return bombLocation;
    }
}
