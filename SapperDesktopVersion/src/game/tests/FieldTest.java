package game.tests;

import game.Cell;
import game.Field;
import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Created by Sergey on 07.07.2017.
 */

public class FieldTest {

    private int neerPointIsBomb(final Field fl, final int row, final int column) {
        if (fl.isOutOfBounds(row, column))
            return 0;
        return fl.isBomb(row, column) ? 1 : 0;
    }

    private void checkPoint(final Field fl, final int row, final int column) {
        int counter = 0;
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                if (rowIndex == 0 && columnIndex == 0)
                    continue;
                counter += neerPointIsBomb(fl, row + rowIndex, column + columnIndex);
            }
        }
        if (counter != fl.getNumber(row, column)) {
            throw new RuntimeException("incorrect counter of bombs in point: '" +
                    row + "; " + column + "'");

        }
    }

    private void checkTest(final int row, final int column, final int[][] curTest) {
        ArrayList<Pair<Integer, Integer>> bombs = countBomb(curTest);
        int counterBomb = bombs.size();
        Field fl;
        try {
            fl = new FieldTester(row, column, bombs);
        } catch (RuntimeException e) {
            throw new RuntimeException("something wrong with initialization field");
        }
        int counter = 0;
        for (int i = 0; i != row; i++) {
            for (int j = 0; j != column; j++) {
                if (fl.isBomb(i, j)) {
                    counter++;
                } else {
                    checkPoint(fl, i, j);
                }
            }
        }
        if (counter != counterBomb) {
            throw new RuntimeException("number points in field != input number");
        }
        Cell[][]field = fl.getField();
        for (int i = 0; i != curTest.length; i++) {
            for (int j = 0; j != curTest[0].length; j++) {
                if (curTest[i][j] != field[i][j].getNumber()) {
                    throw new RuntimeException("wrong point: [" + i + "; " + j + "] " +
                            "expected: '" + curTest[i][j] + "', but found: '" + field[i][j].getNumber() + "'");
                }
            }
        }
    }

    public void run() {
        int counter = 1;
        for (int[][] curTest : tests) {
            try {
                checkTest(curTest.length, curTest[0].length, curTest);
            } catch (RuntimeException e) {
                throw new RuntimeException("game.Field game.tests failed #: " +
                        "'" + counter + "' "
                        + e.getMessage());
            }
            counter++;
        }
        System.out.println("Field tests passed");
    }

    private ArrayList<Pair<Integer, Integer>> countBomb(final int[][] curTest) {
        ArrayList<Pair<Integer, Integer>> prs = new ArrayList<>();
        for (int i = 0; i != curTest.length; i++) {
            for (int j = 0; j != curTest[i].length; j++) {
                if (curTest[i][j] == 9) {
                    prs.add(new Pair<>(i, j));
                }
            }
        }
        return prs;
    }

    static public final int[][][] tests = {
            {{9, 9, 9, 9},
            {3, 9, 4, 2}},
            {{9, 4, 9, 9},
            {9, 9, 3, 2}},
            {{9, 9, 9, 9, 9},
            {9, 7, 6, 7, 9},
            {9, 9, 9, 9, 9}},
            {{0, 0, 0, 0, 0},
            {1, 2, 3, 2, 1},
            {2, 9, 9, 9, 2},
            {2, 9, 9, 9, 2},
            {1, 2, 3, 2, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}},
            {{9, 1, 0, 1, 9},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 0},
            {2, 9, 1, 0, 0},
            {9, 3, 1, 0, 0},
            {9, 3, 1, 2, 2},
            {9, 2, 1, 9, 9}},
            {{9, 9, 9},
            {9, 5, 9}}};
    private final int[][] first = {
            {9, 9, 9, 9},
            {3, 9, 4, 2}};
    private final int[][] second = {
            {9, 4, 9, 9},
            {9, 9, 3, 2}};
    private final int[][] third = {
            {9, 9, 9, 9, 9},
            {9, 7, 6, 7, 9},
            {9, 9, 9, 9, 9}};
    private final int[][] four = {
            {0, 0, 0, 0, 0},
            {1, 2, 3, 2, 1},
            {2, 9, 9, 9, 2},
            {2, 9, 9, 9, 2},
            {1, 2, 3, 2, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}};
    private final int[][] five = {
            {9, 1, 0, 1, 9},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 0},
            {2, 9, 1, 0, 0},
            {9, 3, 1, 0, 0},
            {9, 3, 1, 2, 2},
            {9, 2, 1, 9, 9}};
    private final int[][] six = {
            {9, 9, 9},
            {9, 5, 9}};
}