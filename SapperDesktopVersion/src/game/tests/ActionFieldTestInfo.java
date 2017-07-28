package game.tests;

import javafx.util.Pair;

import java.awt.event.ActionListener;

/**
 * Created by @author Telnov Sergey on 28.07.2017.
 */

public class ActionFieldTestInfo {
    public final CellInfo bombLocation;
    public final CellInfo startCellLocation;
    public final int row, column;
    public ActionFieldTestInfo(final CellInfo bombLocation, final CellInfo startCellLocation, final int row, final int column) {
        this.bombLocation = bombLocation;
        this.startCellLocation = startCellLocation;
        this.row = row;
        this.column = column;
    }
}
