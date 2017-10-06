package tests;

import game.ActionField;
import game.Cell;
import javafx.util.Pair;

/**
 * Created by Sergey on 13.07.2017.
 */

public class ActionFieldTester extends ActionField {

    private Cell[] bombsLocation;
    private int bombCounter = 0;
    private ActionFieldTestInfo testInfo;

    public ActionFieldTester(ActionFieldTestInfo testInfo) {
        super(testInfo.row, testInfo.column, 1);

        this.testInfo = testInfo;
        this.bombsLocation = new Cell[testInfo.bombsLocation.length];

        for (int i = 0; i != bombsLocation.length; i++) {
            this.bombsLocation[i] = getCell(testInfo.bombsLocation[i].row,
                    testInfo.bombsLocation[i].column);
        }
        openFirstCell(testInfo.startCellLocation.row, testInfo.startCellLocation.column);
    }

    public ActionFieldTester recreate() {
        return new ActionFieldTester(this.testInfo);
    }

    @Override
    protected void setGameFinished() { }

    @Override
    protected Cell getBombLocation(int bombIndex) {
        return this.bombsLocation[bombCounter++];
    }
}
