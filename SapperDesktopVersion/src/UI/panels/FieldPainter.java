package UI.panels;

import game.Cell;
import game.Field;
import UI.UIElements.ImagesGetter;

import java.awt.*;

/**
 * Created by Sergey on 10.07.2017.
 */

public class FieldPainter {
    final int START_X = 60;
    final int START_Y = 30;
    private final int ARC_SIZE = 3;
    private Field field;
    private boolean isWin;
    private final Color CELL_COLOR = new Color(2003199);
    private boolean gameFinished;

    public FieldPainter(final Field field, final boolean isWin, final boolean gameFinished) {
        this.field = field;
        this.isWin = isWin;
        this.gameFinished = gameFinished;
    }

    public void drawField(Graphics g) {
        for (int i = 0; i != field.getRow(); i++) {
            for (int j = 0; j != field.getColumn(); j++) {
                drawCell(g, getX(i), getY(j), field.getCell(i, j));
            }
        }
    }

    public void drawFinishGame(Graphics g) {
        for (int i = 0; i != field.getRow(); i++) {
            for (int j = 0; j != field.getColumn(); j++) {
                int x = getX(i);
                int y = getY(j);
                Cell cell = field.getCell(i, j);
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

    private void chooseColor(Graphics g, final int number) {
        Color color;
        switch (number) {
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = new Color(25600);
                break;
            case 3:
                color = Color.RED;
                break;
            case 4:
                color = new Color(139);
                break;
            case 5:
                color = new Color(9109504);
                break;
            case 6:
                color = new Color(11591910);
                break;
            case 7:
                color = new Color(16729344);
                break;
            case 8:
                color = new Color(14315734);
                break;
            default: color = Color.BLACK;

        }
        g.setColor(color);
    }

    private void drawNumber(Graphics g, final Integer number, final int x, final int y) {
        g.setColor(Color.lightGray);
        fillRect(g, x, y);

        chooseColor(g, number);
        g.drawString(number.toString(), x + 2 * Cell.WIDTH / 7 + 2, y + Cell.HEIGHT / 2 + 5);
        drawRect(g, x, y);
    }

    private void drawRect(Graphics g, final int x, final int y) {
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, Cell.WIDTH - ARC_SIZE,Cell.HEIGHT - ARC_SIZE, ARC_SIZE, ARC_SIZE);
    }

    private void fillRect(Graphics g, final int x, final int y) {
        g.fillRoundRect(x, y, Cell.WIDTH - ARC_SIZE, Cell.HEIGHT - ARC_SIZE, ARC_SIZE, ARC_SIZE);
    }

    private void drawBomb(Graphics g, final int x, final int y, boolean isTagged) {
        if (isTagged) {
            g.setColor(Color.GREEN);
            fillRect(g, x, y);
            g.drawImage(ImagesGetter.BOMB_IMAGE, x, y, Cell.WIDTH - ARC_SIZE, Cell.HEIGHT - ARC_SIZE, null);
        } else {
            g.setColor(Color.DARK_GRAY);
            fillRect(g, x, y);
            g.drawImage(ImagesGetter.EXPLOSION, x, y, Cell.WIDTH - ARC_SIZE, Cell.HEIGHT - ARC_SIZE, null);
        }
        drawRect(g, x, y);
    }

    private void drawTagged(Graphics g, final int x, final int y, boolean isBomb) {
        if (!gameFinished) {
            g.setColor(Color.ORANGE);
            fillRect(g, x, y);
            g.drawImage(ImagesGetter.FLAG_IMAGE, x, y, Cell.WIDTH - ARC_SIZE, Cell.HEIGHT - ARC_SIZE, null);
        } else if (isWin) {
            drawBomb(g, x, y, true);
        } else {
            g.setColor(Color.DARK_GRAY);
            fillRect(g, x, y);
            g.drawImage(ImagesGetter.FAKE_BOMB, x, y, Cell.WIDTH - ARC_SIZE, Cell.HEIGHT - ARC_SIZE, null);
        }
        drawRect(g, x, y);
    }

    private void drawOpenCell(Graphics g, final int x, final int y, Cell p) {
        if (p.isTagged) {
            drawTagged(g, x, y, p.isBomb());
        } else if (p.isEmptyPoint()) {
            g.setColor(Color.LIGHT_GRAY);
            fillRect(g, x, y);
            drawRect(g, x, y);
        } else if (p.isNumber()) {
            drawNumber(g, p.getNumber(), x, y);
        }
    }

    private void drawCell(Graphics g, final int x, final int y, Cell cell) {
        if (!cell.isOpened) {
            g.setColor(CELL_COLOR);
            fillRect(g, x, y);
            drawRect(g, x, y);
        } else {
            drawOpenCell(g, x, y, cell);
        }
    }

    private int getX(int index) { return index * Cell.WIDTH + this.START_X; }
    private int getY(int index) { return index * Cell.HEIGHT + this.START_Y; }
}
