package painting;

import game.Cell;
import game.Game;
import game.IGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Elena on 18.07.2017.
 */
public class ScorePanel extends JPanel {

    private Font font = new Font("Calibri", Font.BOLD, 20);
    private final String SCORE_TEXT = "Flags: ";
    private final String TIME_TEXT = "Time: ";
    private JLabel score;
    private Timer timer;
    private Calendar startTime;
    private boolean gameFinished;
    private int counterBomb;

    public ScorePanel(final int counterBomb) {
        this.counterBomb = counterBomb;
        this.setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        score = new JLabel();
        add(score, BorderLayout.PAGE_START);
        score.setFont( font );

        JLabel time = new JLabel();
        add(time, BorderLayout.PAGE_END);
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
        createPanel();
    }

    private void createPanel() {
        gameFinished = false;
        score.setText(SCORE_TEXT + "0/" + counterBomb);

        startTime = Calendar.getInstance();
        startTime.setTime(new Date());

        timer.setInitialDelay(0);
        timer.start();
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
