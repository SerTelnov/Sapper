package painting;

import game.Cell;
import game.Game;
import game.IGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Sergey on 16.07.2017.
 */

public class PanelTop extends JPanel implements IGameListener {
    private JButton restartButton;
    private Font font = new Font("Calibri", Font.BOLD, 20);
    private boolean gameFinished = false;
    private boolean isSetFlagMode = false;
    private PanelTopListener topListener;
    private GameInfo gameInfo;
    private ScorePanel scorePanel;

    public PanelTop(Game game, PanelTopListener topListener) {
        this.setBackground(Color.GRAY);
        this.topListener = topListener;
        setLayout(new BorderLayout());

        restartButton = buttonFactory(ImagesGetter.SMILE_ICON);
        add(restartButton, BorderLayout.CENTER);
        ActionListener restartButtonListener = e -> restartGame();
        restartButton.addActionListener(restartButtonListener);

        JButton flagModeButton = buttonFactory(ImagesGetter.FLAG_ICON);
        add(flagModeButton, BorderLayout.LINE_START);
        ActionListener flagModeListener = e -> changeSetFlagMode();
        flagModeButton.addActionListener(flagModeListener);

        scorePanel = new ScorePanel(game.getCounterBomb());
        add(scorePanel, BorderLayout.LINE_END);
        createPanel(game);
    }

    private JButton buttonFactory(Icon icon) {
        JButton button = new JButton(icon);
        button.setContentAreaFilled( false );
        button.setBorderPainted( false );
        return button;
    }

    private void changeSetFlagMode() {
        isSetFlagMode = !isSetFlagMode;
        topListener.sayChangeFlagMode(isSetFlagMode);
    }

    public void recreatePanel(Game newGame) {
        restartGame(newGame);
    }

    @Override
    public void cellChange(Cell c) {}

    @Override
    public void gameOver(boolean isWin) {
        gameFinished = true;
        if (!isWin) {
            restartButton.setIcon(ImagesGetter.SAD_ICON);
        }
        scorePanel.stopTime();
    }

    @Override
    public void scoreChange(int score) {
        scorePanel.scoreChange(score);
    }

    private void createPanel(Game game) {
        restartButton.setIcon(ImagesGetter.SMILE_ICON);

        gameInfo = new GameInfo(game);
        game.addListener(this);
    }

    private void restartGame(Game newGame) {
        scorePanel.recreatePanel(newGame.getCounterBomb());
        gameFinished = false;
        scoreChange(0);
        topListener.sayRestartGame(newGame);
        createPanel(newGame);
    }

    private void restartGame() {
        restartGame(new Game(gameInfo.ROW, gameInfo.COLUMN, gameInfo.COUNTER_BOMB, false));
    }

    private class GameInfo {
        public final int ROW, COLUMN, COUNTER_BOMB;
        public GameInfo(Game game) {
            ROW = game.getRow();
            COLUMN = game.getColumn();
            COUNTER_BOMB = game.getCounterBomb();
        }
    }
}
