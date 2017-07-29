package tests;

import game.Cell;
import game.IGameListener;

/**
 * Created by Sergey on 12.07.2017.
 */

public class ActionFieldTest implements IGameListener {

    private final ActionFieldTestInfo[] tests = {
            new ActionFieldTestInfo(new CellInfo(0, 0), new CellInfo(3, 3), 4, 4),
            new ActionFieldTestInfo(new CellInfo(2, 2), new CellInfo(5, 5), 9, 9),
            new ActionFieldTestInfo(new CellInfo(0, 0), new CellInfo(2, 2), 4, 4),
    };

    private ActionFieldTester curActionTest;
    private int gameOverCounter, setFlagCounter;
    private CellInfo startCell, bombLocation;

    public void run() {
        int counterTest = 1;
        for (ActionFieldTestInfo curTest : tests) {
            curActionTest = new ActionFieldTester(curTest);
            curActionTest.addListener(this);
            this.startCell = curTest.startCellLocation;
            this.bombLocation = curTest.bombLocation;
            try {
                runTest();
            } catch (RuntimeException e) {
                throw new RuntimeException("action test #" + counterTest + " faild\n" + e.getMessage());
            }
        }
        System.out.println("Action field tests passed");
    }

    private void runTest() {
        runTestSetFlag();
        runTestOpenCell();
        runTestStartCell();
        runTestGameOver();
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
        curActionTest.openCell(this.bombLocation.row, this.bombLocation.column);
        if (gameOverCounter == 0) {
            throw new RuntimeException("game over test failed");
        }
    }

    @Override
    public void cellChange(Cell c) {

    }

    @Override
    public void gameOver(boolean isWin) {
        gameOverCounter++;
    }

    @Override
    public void scoreChange(final int score) {
        setFlagCounter++;
    }
}
