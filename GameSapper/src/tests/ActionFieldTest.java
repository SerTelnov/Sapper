package tests;

import game.Cell;
import game.IGameListener;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Sergey on 12.07.2017.
 */

public class ActionFieldTest implements IGameListener {

    private final ActionFieldTestInfo[] tests = {
            new ActionFieldTestInfo(
                    new CellInfo(0, 0),
                    new CellInfo(3, 3),
                    4, 4),
            new ActionFieldTestInfo(
                    new CellInfo(2, 2),
                    new CellInfo(5, 5),
                    9, 9),
            new ActionFieldTestInfo(
                    new CellInfo(0, 0),
                    new CellInfo(2, 2),
                    4, 4),
            new ActionFieldTestInfo(
                    new CellInfo[]{
                            new CellInfo(0, 0),
                            new CellInfo(2, 3)
                    },
                    new CellInfo(2, 0),
                    4, 4
            ),
            new ActionFieldTestInfo(
                    new CellInfo[]{
                            new CellInfo(0, 0),
                            new CellInfo(2, 3)
                    },
                    new CellInfo(2, 0),
                    4, 4
            )
    };

    private ActionFieldTester curActionTest;
    private int gameOverCounter, setFlagCounter, changedCellsCounter;
    private CellInfo startCell;
    private CellInfo[] bombLocation;

    public void run() {
        int counterTest = 1;
        for (ActionFieldTestInfo curTest : tests) {
            curActionTest = new ActionFieldTester(curTest);
            curActionTest.addListener(this);
            this.startCell = curTest.startCellLocation;
            this.bombLocation = curTest.bombsLocation;
            try {
                runTest();
            } catch (RuntimeException e) {
                throw new RuntimeException("action test #" + counterTest + " faild\n" + e.getMessage());
            }
            counterTest++;
        }
        System.out.println("Action field tests passed");
    }

    private void runTest() {
        runTestSetFlag();
        runTestOpenCell();
        runTestStartCell();
        runTestGameOver();
        runOpenCellCounterTest();
    }

    private HashSet<Cell> used = new HashSet<>();

    private int getNearCellCounter(final int cellRow, final int cellColumn) {
        int counter = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                int row = cellRow + i;
                int column = cellColumn + j;
                if (curActionTest.isOutOfBounds(row, column))
                    continue;
                Cell curr = curActionTest.getCell(row, column);
                if (used.contains(curr))
                    continue;
                used.add(curr);
                if (!curr.isOpened) {
                    if (curr.isEmptyPoint()) {
                        counter += getNearCellCounter(row, column) + 1;
                    } else if (curr.isNumber()) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    private int getStartCellCounter(final int cellRow, final int cellColumn) {
        if (curActionTest.isOutOfBounds(cellRow, cellColumn))
            return 0;
        Cell curr = curActionTest.getCell(cellRow, cellColumn);
        if (!curr.isOpened) {
            used.add(curr);
            if (curr.isNumber()) {
                return 1;
            } else if (curr.isEmptyPoint()) {
                return getNearCellCounter(cellRow, cellColumn) + 1;
            }
        }
        return 0;
    }

    private void runOpenCellCounterTest() {
        this.curActionTest = this.curActionTest.recreate();
        curActionTest.addListener(this);
        this.changedCellsCounter = 0;

        final int startCellsCounter = getStartCellCounter(startCell.row, startCell.column);
        curActionTest.openCell(startCell.row, startCell.column);
        if (startCellsCounter != changedCellsCounter) {
            throw new RuntimeException("change cell test failed:\n" +
                    "wrong open start cell counter");
        }
        for (int i = 0; i != curActionTest.getRow(); i++) {
            for (int j = 0; j != curActionTest.getColumn(); j++) {
                if (!curActionTest.isBomb(i, j)) {
                    this.changedCellsCounter = 0;
                    used.clear();

                    int currChangedCells = getStartCellCounter(i, j);
                    curActionTest.openCell(i, j);

                    if (currChangedCells != this.changedCellsCounter) {
                        throw new RuntimeException("change cell test failed:\n" +
                                "wrong open cell counter");
                    }
                }
            }
        }

    }

    private void runTestOpenCell() {
        for (int i = 0; i != curActionTest.getRow(); i++) {
            for (int j = 0; j != curActionTest.getColumn(); j++) {
                if (!curActionTest.isBomb(i, j)) {
                    curActionTest.openCell(i, j);
                    if (!curActionTest.getCell(i, j).isOpened) {
                        throw new RuntimeException("open cell test failed\n" +
                                "cell didn't open");
                    }
                }
            }
        }
//        else if (this.setFlagCounter != 0) {
//            throw new RuntimeException("open cell test failed\n" +
//                    "set flag on open cell");
//        }
    }

    private void runTestSetFlag() {
        int counterFlags = 0;
        this.setFlagCounter = 0;
        for (int i = 0; i != curActionTest.getRow(); i++) {
            for (int j = 0; j != curActionTest.getColumn(); j++) {
                curActionTest.putTagged(i, j);
                counterFlags += 2;
                curActionTest.putTagged(i, j);
            }
        }
        if (counterFlags != this.setFlagCounter) {
            throw new RuntimeException("set flag test failed\n");
        }
    }

    private void runTestStartCell() {
        this.gameOverCounter = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                curActionTest.openCell(this.startCell.row + i, this.startCell.column + j);
            }
        }
        if (gameOverCounter != 0) {
            throw new RuntimeException("start cell failed\n" +
                    "there is bomb around start cell");
        }
    }

    private void runTestGameOver() {
        curActionTest.openCell(this.bombLocation[0].row, this.bombLocation[0].column);
        if (gameOverCounter == 0) {
            throw new RuntimeException("game over test failed");
        }
    }

    @Override
    public void cellsChanged(List<Cell> cells) {
        changedCellsCounter = cells.size();
    }

    @Override
    public void gameOver(boolean isWin) {
        gameOverCounter++;
    }

    @Override
    public void scoreChange(final int score) {
        setFlagCounter++;
    }

    @Override
    public void gameStart() {

    }
}
