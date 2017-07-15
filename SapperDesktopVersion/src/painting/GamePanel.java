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
    private Images images = new Images();
    private JButton restartButton;

    public GamePanel(final int row, final int column, final int counterBomb) {
        createGame(row, column, counterBomb);

        restartButton = new JButton(images.getSmile());
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
        restartButton.setIcon(images.getSmile());
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);
        Font font = new Font("Calibri", Font.BOLD, 20);
//        Font font = g.getFont().deriveFont( 20.0f );
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
}