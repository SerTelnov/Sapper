package painting.panels;

import game.Cell;
import game.Game;
import game.IGameListener;
import painting.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Sergey on 16.07.2017.
 */

public class PanelTop extends JPanel implements IGameListener {
    private boolean isSetFlagMode = false;
    private PanelTopListener topListener;
    private GameInfo gameInfo;
    private ScorePanel scorePanel;
    private RestartButtonPanel restartButton;
    private JButton flagModeButton;

    public PanelTop(Game game, PanelTopListener topListener) {
        this.setFont(GamePanel.font);
        this.setBackground(Color.GRAY);
        this.topListener = topListener;
        setLayout(new BorderLayout());

        restartButton = new RestartButtonPanel(this);
        add(restartButton, BorderLayout.CENTER);

        flagModeButton = buttonFactory(ImagesGetter.FLAG_ICON);
        add(flagModeButton, BorderLayout.LINE_START);
        ActionListener flagModeListener = e -> changeSetFlagMode();
        flagModeButton.addActionListener(flagModeListener);

        scorePanel = new ScorePanel(game.getCounterBomb());
        add(scorePanel, BorderLayout.LINE_END);
        createPanel(game);
    }

    static JButton buttonFactory(Icon icon) {
        JButton button = new JButton(icon);
        button.setContentAreaFilled( false );
        button.setBorderPainted( false );
        button.setFocusPainted( false );
        return button;
    }

    private void changeSetFlagMode() {
        isSetFlagMode = !isSetFlagMode;
        if (isSetFlagMode) {
            flagModeButton.setIcon(ImagesGetter.FLAG_TAGGED);
        } else {
            flagModeButton.setIcon(ImagesGetter.FLAG_ICON);
        }
        topListener.sayChangeFlagMode(isSetFlagMode);
    }

    public void recreatePanel(Game newGame) {
        restartGame(newGame);
    }

    @Override
    public void cellChange(Cell c) {}

    @Override
    public void gameOver(boolean isWin) {
        if (!isWin) {
            restartButton.setSadIcon();
        }
        scorePanel.stopTime();
    }

    @Override
    public void scoreChange(int score) {
        scorePanel.scoreChange(score);
    }

    private void createPanel(Game game) {
        restartButton.setSmileIcon();

        gameInfo = new GameInfo(game);
        game.addListener(this);
    }

    private void restartGame(Game newGame) {
        flagModeButton.setIcon(ImagesGetter.FLAG_ICON);
        scorePanel.recreatePanel(newGame.getCounterBomb());
        scoreChange(0);
        topListener.sayRestartGame(newGame);
        createPanel(newGame);
    }

    public void restartGame() {
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
