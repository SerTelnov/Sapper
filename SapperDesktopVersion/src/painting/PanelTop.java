package painting;

import game.Cell;
import game.Game;
import game.IGameListener;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Sergey on 16.07.2017.
 */

public class PanelTop extends JPanel implements IGameListener {
    private JButton restartButton;
    private Calendar startTime;
    private Font font = new Font("Calibri", Font.BOLD, 20);
    private boolean gameFinished = false;
    private boolean isSetFlagMode = false;
    private PanelTopListener topListener;
    private GameInfo gameInfo;
    private final String SCORE_TEXT = "Flags: ";
    private final String TIME_TEXT = "Time: ";
    private JLabel score;
    private Timer timer;

    public PanelTop(Game game, PanelTopListener topListener) {
        this.setBackground(Color.LIGHT_GRAY);
        this.topListener = topListener;
        setLayout(new BorderLayout());

        restartButton = buttonFactory(ImagesGetter.SMILE_ICON);
        add(restartButton, BorderLayout.NORTH);
        ActionListener restartButtonListener = e -> restartGame();
        restartButton.addActionListener(restartButtonListener);

        JButton flagModeButton = buttonFactory(ImagesGetter.FLAG_ICON);
        add(flagModeButton, BorderLayout.CENTER);
        ActionListener flagModeListener = e -> changeSetFlagMode();
        flagModeButton.addActionListener(flagModeListener);

        score = new JLabel();
        add(score, BorderLayout.EAST);
        score.setFont( font );

        JLabel time = new JLabel();
        add(time, BorderLayout.WEST);
        time.setFont( font );

        ActionListener timeListener = event -> {
            if (!gameFinished) {
                Calendar current = Calendar.getInstance();
                current.setTime(new Date());
                MyTimer curTime = new MyTimer(startTime, current);
                String timeStr = String.format(TIME_TEXT + "%d:%02d:%02d",
                        curTime.getHour(),
                        curTime.getMinute(),
                        curTime.getSecond()
                );
                time.setText(timeStr);
            }
        };

        timer = new Timer(500, timeListener);
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
//        gameInfo = new GameInfo(newGame);
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
    }

    @Override
    public void scoreChange(int score) {
        this.score.setText(SCORE_TEXT + score + "/" + gameInfo.COUNTER_BOMB);
    }

    private void createPanel(Game game) {
        restartButton.setIcon(ImagesGetter.SMILE_ICON);

        gameInfo = new GameInfo(game);
        game.addListener(this);

        score.setText(SCORE_TEXT + "0/" + gameInfo.COUNTER_BOMB);

        startTime = Calendar.getInstance();
        startTime.setTime(new Date());

        timer.setInitialDelay(0);
        timer.start();
    }

    private void restartGame(Game newGame) {
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

    private class MyTimer {

        private int hour, min, sec;

        public MyTimer(Calendar start, Calendar cur) {
            hour = cur.get(Calendar.HOUR) - start.get(Calendar.HOUR);
            min = cur.get(Calendar.MINUTE) - start.get(Calendar.MINUTE);
            sec = cur.get(Calendar.SECOND) - start.get(Calendar.SECOND);
            if (sec < 0) {
                sec += 60;
                min--;
            }
            if (min < 0) {
                min += 60;
                hour--;
            }
        }

        public int getHour() { return hour; }
        public int getMinute() { return min; }
        public int getSecond() { return sec; }
    }
}
