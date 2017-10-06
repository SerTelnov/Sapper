package tests;

import game.Cell;

/**
 * Created by @author Telnov Sergey on 28.07.2017.
 */

public class ActionFieldTestInfo {
    public final CellInfo[] bombsLocation;
    public final CellInfo startCellLocation;
    public final int row, column;

    public ActionFieldTestInfo(final CellInfo bombLocation, final CellInfo startCellLocation,
            final int row, final int column) {

        this.bombsLocation = new CellInfo[] {
            bombLocation
        };
        this.startCellLocation = startCellLocation;
        this.row = row;
        this.column = column;
    }

    public ActionFieldTestInfo(final CellInfo[] bombsLocation, final CellInfo startCellLocation,
            final int row, final int column) {
        this.bombsLocation = bombsLocation;
        this.startCellLocation = startCellLocation;
        this.row = row;
        this.column = column;
    }
}
