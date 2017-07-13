package painting;

import game.Cell;
import game.Field;

import java.awt.*;

/**
 * Created by Sergey on 10.07.2017.
 */

public class FieldPainter {
    final int START_X = 50;
    final int START_Y = 50;
    final Images images = new Images();
    private Field field;
    private boolean isWin, gameFinished;

    public FieldPainter(final Field field) {
        this.field = field;
        isWin = false;
        gameFinished = false;
    }

    public FieldPainter(final Field field, final boolean isWin, final boolean gameFinished) {
        this.field = field;
        this.isWin = isWin;
        this.gameFinished = gameFinished;
    }

    public void drawField(Graphics g, Cell[][] field) {
        for (int i = 0; i != field.length; i++) {
            for (int j = 0; j != field[i].length; j++) {
                drawCell(g, getX(i), getY(j), field[i][j]);
            }
        }
    }

    public void drawFinishedGame(Graphics g, Cell[][] field, boolean isWin) {
        for (int i = 0; i != field.length; i++) {
            for (int j = 0; j != field[i].length; j++) {
                int x = getX(i);
                int y = getY(j);
                Cell cell = field[i][j];
                if (cell.isBomb()) {
                    drawBomb(g, x, y, cell.isTagged);
                } else if (isWin) {
                    drawOpenCell(g, x, y, cell);
                } else {
                    drawCell(g, x, y, cell);
                }
            }
        }
    }

    private void drawNumber(Graphics g, final Integer number, final int x, final int y) {
        g.clearRect(x, y, Cell.WIDTH, Cell.HEIGHT);
        g.drawString(number.toString(), x + 2, y + Cell.HEIGHT / 2);
        drawRect(g, x, y);
    }

    private void drawRect(Graphics g, final int x, final int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, Cell.WIDTH, Cell.HEIGHT);
    }

    private void fillRect(Graphics g, final int x, final int y) {
        g.fillRect(x, y, Cell.WIDTH, Cell.HEIGHT);
        drawRect(g, x, y);
    }

    private void drawBomb(Graphics g, final int x, final int y, boolean isTagged) {
        if (isTagged) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.red);
        }
        fillRect(g, x, y);
    }

    private void drawTagged(Graphics g, final int x, final int y) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, Cell.WIDTH, Cell.HEIGHT);
        g.setColor(Color.RED);
        g.drawString("?", x + 2, y + Cell.HEIGHT / 2);
        drawRect(g, x, y);
    }

    private void drawOpenCell(Graphics g, final int x, final int y, Cell p) {
        if (p.isTagged) {
            drawTagged(g, x, y);
        } else if (p.isEmptyPoint()) {
            g.setColor(Color.WHITE);
            fillRect(g, x, y);
        } else if (p.isNumber()) {
            drawNumber(g, p.getNumber(), x, y);
        }
    }

    private void drawCell(Graphics g, final int x, final int y, Cell cell) {
        if (!cell.isOpened) {
            g.setColor(Color.darkGray);
            fillRect(g, x, y);
        } else {
            drawOpenCell(g, x, y, cell);
        }
    }

    private int getX(int index) { return index * Cell.WIDTH + this.START_X; }
    private int getY(int index) { return index * Cell.HEIGHT + this.START_Y; }
}
