package painting;

import game.Cell;
import game.Game;
import game.IGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sergey on 10.07.2017.
 */

public class GamePanel extends JPanel implements IGameListener, IPanelTopListener {
    private FieldPainter fieldPainter;
    private Game game;
    private boolean isWin;
    private boolean gameFinished;
    private boolean isSetFlagMode = false;
    private Font font = new Font("Calibri", Font.BOLD, 20);

    public GamePanel(Game newGame, PanelTopListener topListener) {
        topListener.addListener(this);
        createGame(newGame);

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!gameFinished) {
                    int row = (e.getX() - fieldPainter.START_X) / Cell.WIDTH;
                    int column = (e.getY() - fieldPainter.START_Y) / Cell.HEIGHT;
                    if (!game.isBuildField) {
                        game.setField(row, column);
                    }
                    if (SwingUtilities.isLeftMouseButton(e) && !isSetFlagMode) {
                        game.openCell(row, column);
                    } else if (isSetFlagMode || SwingUtilities.isRightMouseButton(e)) {
                        game.putTagged(row, column);
                    }
                }
            }
        });
    }

    private void createGame(Game game) {
        game.addListener(this);
        this.game = game;
        gameFinished = false;
        isWin = false;
        isSetFlagMode = false;
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
    }

    @Override
    public void cellChange(Cell c) {
        repaint();
    }

    @Override
    public void gameOver(boolean isWin) {
        this.isWin = isWin;
        gameFinished = true;
        repaint(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void scoreChange(final int score) {
    }

    @Override
    public void restartGame(Game newGame) {
        createGame(newGame);
        repaint();
    }

    @Override
    public void changeFlagMode(boolean isSetFlagMode) {
        this.isSetFlagMode = isSetFlagMode;
    }
}