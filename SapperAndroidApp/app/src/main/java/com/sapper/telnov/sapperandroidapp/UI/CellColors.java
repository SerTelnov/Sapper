package com.sapper.telnov.sapperandroidapp.UI;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Sergey on 29.07.2017.
 */

public class CellColors {
    private static class MyPaints {
        public Paint cellPaint;
        public Paint linePaint;
        public Paint bombPaint;
        public Paint emptyCellPaint;
        public Paint numberPaint;
        public Paint taggedPaint;
        public Paint deActivateBombPaint;

        public final int CLOSE_CELL_COLOR = Color.parseColor("#2979FF");

        public MyPaints() {
            cellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            cellPaint.setColor(CLOSE_CELL_COLOR);
            linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            linePaint.setColor(Color.BLACK);
            bombPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            bombPaint.setColor(Color.RED);
            emptyCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            emptyCellPaint.setColor(Color.LTGRAY);
            numberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            numberPaint.setTextSize(48f);
            taggedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            taggedPaint.setColor(Color.YELLOW);
            deActivateBombPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            deActivateBombPaint.setColor(Color.GREEN);
        }
    }

    private static MyPaints colors = new MyPaints();
    public static final Paint CELL_PAINT = colors.cellPaint;
    public static final Paint LINE_PAINT = colors.linePaint;
    public static final Paint BOMB_PAINT = colors.bombPaint;
    public static final Paint EMPTY_CELL_PAINT = colors.emptyCellPaint;
    public static final Paint NUMBER_PAINT = colors.numberPaint;
    public static final Paint TAGGED_PAINT = colors.taggedPaint;
    public static final Paint DE_ACTIVATE_BOMB_PAINT = colors.deActivateBombPaint;
    public static final int CELL_WIDTH = 65;
    public static final int CELL_HEIGHT = 65;
}

