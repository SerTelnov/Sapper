package com.sapper.telnov.sapperandroidapp.customView.fieldPanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import com.sapper.telnov.sapperandroidapp.UI.CellColors;

import game.Cell;

/**
 * Created by Sergey on 31.07.2017.
 */

public class CellView extends View {
    private Cell cell;
    private CellSender cellSender;
    private CellCoordinate coordinate;

    public CellView(Context context, Cell c, CellSender sender) {
        super(context);
        init(c, sender);
    }

    private void init(Cell c, CellSender sender) {
        this.coordinate = new CellCoordinate();
        this.cellSender = sender;
        this.cell = c;

        this.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cellSender.sayPutTagged(cell);
                return true;
            }
        });
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cellSender.isSetFlagMode()) {
                    cellSender.sayPutTagged(cell);
                } else {
                    cellSender.sayOpenCell(cell);
                }
                if (!cellSender.isGameFinished()) {
                    FieldSender.sayTouchField();
                }
            }
        });
    }

    public int getRow() { return cell.row; }
    public int getColumn() { return cell.column; }

    public void changeCell(Cell newCell) {
        this.cell = newCell;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (cellSender.isWin) {
            drawOpenCell(canvas);
        } else if (!cell.isOpened) {
            drawCloseCell(canvas);
        } else if (cell.isTagged) {
            drawTagged(canvas);
        } else if (cellSender.isGameFinished() && cell.isBomb()) {
            drawBomb(canvas);
        }
        else {
            drawOpenCell(canvas);
        }
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        canvas.drawLine(
                coordinate.left, coordinate.top,
                coordinate.right, coordinate.top,
                CellColors.LINE_PAINT);
        canvas.drawLine(
                coordinate.left, coordinate.bottom,
                coordinate.right, coordinate.bottom,
                CellColors.LINE_PAINT);
        canvas.drawLine(
                coordinate.left, coordinate.top,
                coordinate.left, coordinate.bottom,
                CellColors.LINE_PAINT);
        canvas.drawLine(
                coordinate.right, coordinate.top,
                coordinate.right, coordinate.bottom,
                CellColors.LINE_PAINT);
    }

    private void drawCloseCell(Canvas canvas) {
        canvas.drawRect(
                coordinate.left, coordinate.top,
                coordinate.right, coordinate.bottom,
                CellColors.CELL_PAINT);
    }

    private void drawOpenCell(Canvas canvas) {
        if (cell.isNumber()) {
            drawNumber(canvas);
        } else if (cell.isBomb()) {
            drawBomb(canvas);
        } else {
            drawEmptyCell();
        }
    }

    private void drawEmptyCell() {
        this.setBackgroundColor(Color.LTGRAY);
    }

    private void drawNumber(Canvas canvas) {
        this.setBackgroundColor(Color.LTGRAY);
        canvas.drawText(Integer.valueOf(cell.getNumber()).toString(),
                coordinate.x,
                coordinate.y,
                CellColors.NUMBER_PAINT);
    }

    private void drawBomb(Canvas canvas) {
        if (cell.isTagged) {
            canvas.drawRect(coordinate.left, coordinate.top,
                    coordinate.right, coordinate.bottom,
                    CellColors.DE_ACTIVATE_BOMB_PAINT);
        } else {
            canvas.drawRect(coordinate.left, coordinate.top,
                    coordinate.right, coordinate.bottom,
                    CellColors.BOMB_PAINT);
        }
    }

    private void drawTagged(Canvas canvas) {
        canvas.drawRect(
                coordinate.left, coordinate.top,
                coordinate.right, coordinate.bottom,
                CellColors.TAGGED_PAINT);
    }

    private class CellCoordinate {
        public float left, right, top, bottom, x, y;

        public CellCoordinate() {
            this.left = 0;
            this.right = CellColors.CELL_WIDTH;
            this.top = 0;
            this.bottom = CellColors.CELL_HEIGHT;
            this.x = CellColors.CELL_HEIGHT / 2 + 5;
            this.y = 4 * CellColors.CELL_WIDTH / 7 + 2;
        }
    }
}
