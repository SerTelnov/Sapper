package painting;

import game.Cell;
import game.Game;
import game.IGameListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sergey on 10.07.2017.
 */

public class GamePanel extends JPanel implements IGameListener {
    private FieldPainter fieldPainter;
    private ScorePainter scorePainter;
    private Game game;
    private boolean isWin;
    private boolean gameFinished;
    private boolean isSetFlagMode = false;
    private Images images = new Images();
    private JButton restartButton;
    private Calendar startTime;
    private Font font = new Font("Calibri", Font.BOLD, 20);

    public GamePanel(final int row, final int column, final int counterBomb) {
        createGame(row, column, counterBomb);

        restartButton = new JButton(images.getSmile());
        restartButton.setLocation(40, 30);
        ActionListener rectDrawListener = e -> restartGame();
        restartButton.addActionListener(rectDrawListener);
        add(restartButton);

        JButton flagModeButton = new JButton(images.getFlagIcon());
        flagModeButton.setLayout(null);
        ActionListener flagModeListener = e -> isSetFlagMode = !isSetFlagMode;
        flagModeButton.addActionListener(flagModeListener);
        add(flagModeButton);
        flagModeButton.setBounds(50, 30, Cell.WIDTH, Cell.HEIGHT);


        JLabel time = new JLabel();
        add(time);
        time.setFont(font);
        time.setLayout(null);
        time.setBounds(100, 70, Cell.WIDTH, Cell.HEIGHT);

        ActionListener listener = event -> {
            if (!gameFinished) {
                Calendar current = Calendar.getInstance();
                current.setTime(new Date());
                MyTimer curTime = new MyTimer(startTime, current);
                String timeStr = String.format("%d:%02d:%02d",
                        curTime.getHour(),
                        curTime.getMinute(),
                        curTime.getSecond()
                );
                time.setText(timeStr);
            }
        };

        javax.swing.Timer timer = new javax.swing.Timer(500, listener);
        timer.setInitialDelay(0);
        timer.start();

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!gameFinished) {
                    int row = (e.getX() - fieldPainter.START_X) / Cell.WIDTH;
                    int column = (e.getY() - fieldPainter.START_Y) / Cell.HEIGHT;
                    if (SwingUtilities.isLeftMouseButton(e) && !isSetFlagMode) {
                        game.openCell(row, column);
                    } else if (isSetFlagMode || SwingUtilities.isRightMouseButton(e)) {
                        game.putTagged(row, column);
                    }
                }
            }
        });
    }

    private class MyTimer {
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
        private int hour, min, sec;

        public int getHour() { return hour; }
        public int getMinute() { return min; }
        public int getSecond() { return sec; }
    }

    private void createGame(final int row, final int column, final int counterBomb) {
        scorePainter = new ScorePainter(counterBomb);
        game = new Game(row, column, counterBomb);
        game.addListener(this);
        gameFinished = false;
        isWin = false;
        isSetFlagMode = false;
        startTime = Calendar.getInstance();
        startTime.setTime(new Date());
    }

    public void restartGame(final int row, final int column, final int counterBomb) {
        game = new Game(row, column, counterBomb);
        restartGame();
    }

    private void restartGame() {
        createGame(game.getRow(), game.getColumn(), game.getCounterBomb());
        restartButton.setIcon(images.getSmile());
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);
        g.setFont( font );
        fieldPainter = new FieldPainter(game, isWin);
        if (gameFinished) {
            fieldPainter.drawFinishGame(g);
        } else {
            fieldPainter.drawField(g);
        }
        scorePainter.drawScore(g);
    }

    @Override
    public void cellChange(Cell c) {
        repaint();
    }

    @Override
    public void gameOver(boolean isWin) {
        if (!isWin) {
            restartButton.setIcon(images.getSad());
        }
        this.isWin = isWin;
        this.gameFinished = true;
        repaint(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void scoreChange(final int score) {
        scorePainter.changeScore(score);
    }

    public static BufferedImage loadImage(String address) throws IOException {
        try {
            return ImageIO.read(GamePanel.class.getResource("/" + address));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}