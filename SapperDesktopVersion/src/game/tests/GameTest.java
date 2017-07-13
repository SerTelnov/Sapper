package game.tests;

import game.Cell;
import game.IGameListener;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Sergey on 12.07.2017.
 */

public class GameTest implements IGameListener {
    public void run() {
        int counter = 1;
        for (int[][] curTest : FieldTest.tests) {
            bombIndexes = getBombIndexes(curTest);
            curGame = new GameTester(curTest.length, curTest[0].length, bombIndexes);
            curGame.addListener(this);
            try {
                runTest();
            } catch (RuntimeException e) {
                throw new RuntimeException("game test #" + counter + " failed\n" + e.getMessage());
            }
            counter++;
        }
        System.out.println("Game tests passed");
    }
    private ArrayList<Pair<Integer, Integer>> bombIndexes;
    private GameTester curGame;

    private int gameOverCounter, cellChangeCounter, scoreChangeCounter;

    private void setCounterZero() {
        gameOverCounter = 0;
        cellChangeCounter = 0;
        scoreChangeCounter = 0;
    }

    private void runTest() {
        int counterGameOver = 0;
        int counterScoreChange = 0;
        int counterCellChange = 0;
        setCounterZero();
        Cell[][] curField = curGame.getField();
        for (int i = 0; i != curField.length; i++) {
            for (int j = 0; j != curField[i].length; j++) {
                Cell curCell = curField[i][j];
                if (curCell.isBomb()) {
                    if (counterGameOver == 0) {
                        curGame.openCell(i, j);
                        counterGameOver++;
                    } else {
                        curGame.putTagged(i, j);
                        counterCellChange++;
                        counterScoreChange++;
                    }
                } else if (curCell.isNumber()) {
                    curGame.openCell(i, j);
                    counterCellChange++;
                }
            }
        }
        if (counterGameOver != gameOverCounter) {
            throw new RuntimeException("counterGameOver != gameOverCounter");
        }
        if (counterCellChange != cellChangeCounter) {
            throw new RuntimeException("counterCellChange != cellChangeCounter");
        }
        if (counterScoreChange != scoreChangeCounter) {
            throw new RuntimeException("counterScoreChange != scoreChangeCounter");
        }
    }

    private ArrayList<Pair<Integer, Integer>> getBombIndexes(int[][] field) {
        ArrayList<Pair<Integer, Integer>> bombIndexes = new ArrayList<>();
        for (int i = 0; i != field.length; i++) {
            for (int j = 0; j != field[i].length; j++) {
                if (field[i][j] == 9) {
                    bombIndexes.add(new Pair<>(i, j));
                }
            }
        }
        return bombIndexes;
    }

    @Override
    public void cellChange(Cell c) {
        cellChangeCounter++;
    }

    @Override
    public void gameOver(boolean isWin) {
        gameOverCounter++;
    }

    @Override
    public void scoreChange(int counter) {
        scoreChangeCounter++;
    }
}
