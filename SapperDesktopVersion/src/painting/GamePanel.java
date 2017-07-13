package painting;

import game.Cell;
import game.Game;
import game.IGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sergey on 10.07.2017.
 */

public class GamePanel extends JPanel implements IGameListener {
    private FieldPainter fieldPainter;
    private ScorePainter scorePainter;
    private Game game;
    private boolean isWin;
    private boolean gameFinished;
    public GamePanel(final int row, final int column, final int counterBomb) {
        createGame(row, column, counterBomb);

        JButton restartButton = new JButton("Restart");
        ActionListener rectDrawListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        };
        restartButton.addActionListener(rectDrawListener);
        add(restartButton);

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!gameFinished) {
                    int row = (e.getX() - fieldPainter.START_X) / Cell.WIDTH;
                    int column = (e.getY() - fieldPainter.START_Y) / Cell.HEIGHT;
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        game.openCell(row, column);
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        game.putTagged(row, column);
                    }
                }
            }
        });
    }

    private void createGame(final int row, final int column, final int counterBomb) {
        scorePainter = new ScorePainter(counterBomb);
        game = new Game(row, column, counterBomb);
        game.addListener(this);
        gameFinished = false;
        isWin = false;
    }

    private void restartGame() {
        createGame(game.getRow(), game.getColumn(), game.getCounterBomb());
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        fieldPainter = new FieldPainter(game, isWin, gameFinished);
        g.fillRect(0, 0, this.getHeight(), this.getWidth());
        if (!gameFinished) {
            g.setColor(Color.BLACK);
            g.drawString("This is game Sapper!", 10, 20);
        } else if (isWin) {
            g.setColor(Color.GREEN);
            g.drawString("You WIN!!! :)", 10, 20);
        } else {
            g.setColor(Color.RED);
            g.drawString("You LOST!!! :(", 10, 20);
        }
        if (!gameFinished) {
            fieldPainter.drawField(g, game.getField());
        } else {
            fieldPainter.drawFinishedGame(g, game.getField(), isWin);
        }
        scorePainter.drawScore(g);
    }

    @Override
    public void cellChange(Cell c) {
        repaint(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void gameOver(boolean isWin) {
        this.isWin = isWin;
        this.gameFinished = true;
        repaint(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void scoreChange(final int score) {
        scorePainter.changeScore(score);
    }
}