package UI.panels;

import UI.UIElements.GameTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Sergey on 18.07.2017.
 */

public class ScorePanel extends JPanel {

    private final String SCORE_TEXT = "Flags: ";
    private final String TIME_TEXT = "Time: ";
    private JLabel score;
    private Timer clock;
    private boolean gameFinished;
    private int counterBomb;
    private GameTimer gameTimer;

    public ScorePanel(final int counterBomb, GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        this.counterBomb = counterBomb;
        this.setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        score = new JLabel();
        add(score, BorderLayout.PAGE_START);
        score.setFont( GamePanel.font );

        JLabel time = new JLabel();
        add(time, BorderLayout.PAGE_END);
        time.setFont( GamePanel.font );

        ActionListener timeListener = event -> {
            if (!gameFinished) {
                time.setText(TIME_TEXT + this.gameTimer.getCurrentTimeText());
            }
        };

        clock = new Timer(1000, timeListener);
        createPanel();
    }

    private void createPanel() {
        gameFinished = false;
        score.setText(SCORE_TEXT + "0/" + counterBomb);

        gameTimer.setZeroTime();

        clock.setInitialDelay(0);
        clock.start();
    }

    public void startTime() {
        gameTimer.setNewTimer();

        clock.setInitialDelay(0);
        clock.start();
    }

    public void recreatePanel(final int counterBomb) {
        this.counterBomb = counterBomb;
        createPanel();
    }

    public void stopTime() {
        gameFinished = true;
    }

    public void scoreChange(int score) {
        this.score.setText(SCORE_TEXT + score + "/" + counterBomb);
    }
}
