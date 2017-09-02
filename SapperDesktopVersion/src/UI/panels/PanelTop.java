package UI.panels;

import UI.UIElements.GameTimer;
import UI.UIElements.ImagesGetter;
import game.ActionField;
import game.Cell;
import game.IGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Sergey on 16.07.2017.
 */

public class PanelTop extends JPanel implements IGameListener, IGamePanelListener {
    private boolean isSetFlagMode = false;
    private PanelTopListener topListener;
    private GameInfo gameInfo;
    private ScorePanel scorePanel;
    private RestartButtonPanel restartButton;
    private JButton flagModeButton;
    private GamePanelListener gamePanelListener;

    public PanelTop(ActionField actionField, PanelTopListener topListener, GamePanelListener gamePanelListener, GameTimer gameTimer) {
        this.setFont(GamePanel.font);
        this.setBackground(Color.GRAY);
        this.topListener = topListener;
        this.gamePanelListener = gamePanelListener;
        this.gamePanelListener.addListener(this);
        setLayout(new BorderLayout());

        restartButton = new RestartButtonPanel(this);
        add(restartButton, BorderLayout.CENTER);

        flagModeButton = buttonFactory(ImagesGetter.FLAG_ICON);
        add(flagModeButton, BorderLayout.LINE_START);
        ActionListener flagModeListener = e -> changeSetFlagMode();
        flagModeButton.addActionListener(flagModeListener);

        scorePanel = new ScorePanel(actionField.getCounterBomb(), gameTimer);
        add(scorePanel, BorderLayout.LINE_END);
        createPanel(actionField);
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

    public void recreatePanel(ActionField newActionField) {
        restartGame(newActionField);
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

    private void createPanel(ActionField actionField) {
        restartButton.setSmileIcon();

        gameInfo = new GameInfo(actionField);
        actionField.addListener(this);
    }

    private void restartGame(ActionField newActionField) {
        flagModeButton.setIcon(ImagesGetter.FLAG_ICON);
        scorePanel.recreatePanel(newActionField.getCounterBomb());
        scoreChange(0);
        topListener.sayRestartGame(newActionField);
        createPanel(newActionField);
    }

    public void restartGame() {
        restartGame(new ActionField(gameInfo.ROW, gameInfo.COLUMN, gameInfo.COUNTER_BOMB));
    }

    @Override
    public void touchField() {
        restartButton.setBoredIcon();
    }

    @Override
    public void gameStart() {
        this.scorePanel.startTime();
    }

    private class GameInfo {
        public final int ROW, COLUMN, COUNTER_BOMB;
        public GameInfo(ActionField actionField) {
            ROW = actionField.getRow();
            COLUMN = actionField.getColumn();
            COUNTER_BOMB = actionField.getCounterBomb();
        }
    }
}
