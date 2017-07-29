package tests;

import game.Cell;
import game.Field;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 07.07.2017.
 */

public class FieldTest {

    private int isBombCell(final Field fl, final int row, final int column) {
        if (fl.isOutOfBounds(row, column))
            return 0;
        return fl.isBomb(row, column) ? 1 : 0;
    }

    private void checkCell(final Field field, final int row, final int column) {
        int counter = 0;
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) {
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) {
                if (rowIndex == 0 && columnIndex == 0)
                    continue;
                counter += isBombCell(field, row + rowIndex, column + columnIndex);
            }
        }
        if (counter != field.getNumber(row, column)) {
            throw new RuntimeException("incorrect counter of bombs in point: '" +
                    row + "; " + column + "'");

        }
    }

    private void checkCells(Field field, int counterBomb) {
        int counter = 0;
        for (int i = 0; i != field.getRow(); i++) {
            for (int j = 0; j != field.getColumn(); j++) {
                if (field.isBomb(i, j)) {
                    counter++;
                } else {
                    checkCell(field, i, j);
                }
            }
        }
        if (counter != counterBomb) {
            throw new RuntimeException("number points in field != input number");
        }
    }

    private void checkField(final int[][] curTest, final Cell[][] field) {
        for (int i = 0; i != curTest.length; i++) {
            for (int j = 0; j != curTest[0].length; j++) {
                if (curTest[i][j] != field[i][j].getNumber()) {
                    throw new RuntimeException("wrong point: [" + i + "; " + j + "] " +
                            "expected: '" + curTest[i][j] + "', but found: '" + field[i][j].getNumber() + "'");
                }
            }
        }
    }

    private void checkTest(final int row, final int column, final int[][] curTest) {
        List<Pair<Integer, Integer>> bombs = getBombCoordinate(curTest);
        Field fl;
        try {
            fl = new FieldTester(row, column, bombs);
        } catch (RuntimeException e) {
            throw new RuntimeException("something wrong with initialization field");
        }
        checkCells(fl, bombs.size());
        checkField(curTest, fl.getField());
    }

    public void run() {
        int counter = 1;
        for (int[][] curTest : tests) {
            try {
                checkTest(curTest.length, curTest[0].length, curTest);
            } catch (RuntimeException e) {
                throw new RuntimeException("game.game.Field game.tests failed #: " +
                        "'" + counter + "' "
                        + e.getMessage()
                        );
            }
            counter++;
        }
        System.out.println("game.Field tests passed");
    }

    private ArrayList<Pair<Integer, Integer>> getBombCoordinate(final int[][] curTest) {
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
    // private final int[][] first = {
    //         {9, 9, 9, 9},
    //         {3, 9, 4, 2}};
    // private final int[][] second = {
    //         {9, 4, 9, 9},
    //         {9, 9, 3, 2}};
    // private final int[][] third = {
    //         {9, 9, 9, 9, 9},
    //         {9, 7, 6, 7, 9},
    //         {9, 9, 9, 9, 9}};
    // private final int[][] four = {
    //         {0, 0, 0, 0, 0},
    //         {1, 2, 3, 2, 1},
    //         {2, 9, 9, 9, 2},
    //         {2, 9, 9, 9, 2},
    //         {1, 2, 3, 2, 1},
    //         {0, 0, 0, 0, 0},
    //         {0, 0, 0, 0, 0}};
    // private final int[][] five = {
    //         {9, 1, 0, 1, 9},
    //         {1, 1, 0, 1, 1},
    //         {1, 1, 1, 0, 0},
    //         {2, 9, 1, 0, 0},
    //         {9, 3, 1, 0, 0},
    //         {9, 3, 1, 2, 2},
    //         {9, 2, 1, 9, 9}};
    // private final int[][] six = {
    //         {9, 9, 9},
    //         {9, 5, 9}};
}