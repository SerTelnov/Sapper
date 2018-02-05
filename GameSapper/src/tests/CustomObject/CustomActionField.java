package tests.CustomObject;

import game.ActionField;
import game.Cell;

/**
 * Created by Telnov Sergey on 02.02.2018.
 */

public class CustomActionField extends ActionField {

    public CustomActionField(int row, int column, FieldType type) {
        super(row, column, 1);

        this.fieldCreated = true;

        Cell[][] cells = new Cell[row][column];

        if (type.equals(FieldType.FIRST_IS_BOMB_FIELD)) {
            cells[0][0] = new Cell(9);
            return;
        }

        Cell customCell;

        if (type.equals(FieldType.BOMB_FIELD)) {
            customCell = new Cell(9);
        } else if (type.equals(FieldType.DIGIT_FIELD)) {
            customCell = new Cell(1);
        } else {
            customCell = new Cell(0);
        }

        for (int i = 0; i != row; i++) {
            for (int j = 0; j != column; j++) {
                cells[i][j] = customCell;
            }
        }

        this.field = cells;
    }

    public enum FieldType {
//        all cells are bomb
        BOMB_FIELD,
//        all cells are digit
        DIGIT_FIELD,
//        all cells are empty
        EMPTY_FIELD,
//        first cell is bomb
        FIRST_IS_BOMB_FIELD
    }
}
