package game.tests;

import game.*;

/**
 * Created by Sergey on 07.07.2017.
 */

public class CellTest {
    private void checkPoint(int number) {
        Cell cell;
        try {
            cell = new Cell(number);
        } catch (RuntimeException e) {
            if (number < 1 || number > 9) {
                return;
            } else {
                throw new RuntimeException("this point should have been created. " +
                        "Number: '" + number + "'");
            }
        }
        if (cell.getNumber() != number) {
            throw new RuntimeException("wrong point: expected '" + number +
                    "', but found '" + cell.getNumber() + "'");
        }
        if (number >= 1 && number <= 8) {
            if (cell.isBomb()) {
                throw new RuntimeException("wrong point: expected safe point, but found bomb");
            }
        } else if (number == 9) {
            if (!cell.isBomb()) {
                throw new RuntimeException("wrong point: expected bomb");
            }
        } else {
            throw new RuntimeException("this point should not have been created. " +
                    "Number: '" + number + "'");
        }
    }

    public void run() {
        for (int i = 1; i != 10; i++) {
            try {
                checkPoint(i);
            } catch (RuntimeException e) {
                throw new RuntimeException("game.Cell game.tests failed " + e.getMessage());
            }
        }
        System.out.println("Cell tests passed");
    }
}