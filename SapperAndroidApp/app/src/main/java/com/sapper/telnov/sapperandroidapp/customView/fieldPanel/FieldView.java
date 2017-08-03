package com.sapper.telnov.sapperandroidapp.customView.fieldPanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.sapper.telnov.sapperandroidapp.customView.topPanel.ITopPanelListener;
import com.sapper.telnov.sapperandroidapp.customView.topPanel.TopPanelSender;

import java.util.ArrayList;
import java.util.List;

import game.ActionField;
import game.Cell;
import game.IGameListener;

/**
 * Created by Sergey on 27.07.2017.
 */

public class FieldView extends ViewGroup implements IGameListener, ICellListener, ITopPanelListener {
    private ActionField field;

    private List<CellView> cellViews = new ArrayList<>();

    private CellSender cellSender;

    private final int CELL_HEIGHT = 65;
    private final int CELL_WIDTH = 65;

    private Context context;

    public FieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public FieldView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    /**
     * Any layout manager that doesn't scroll will want this.
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int width = 0;
        int height = 0;
        for (int i = 0; i != count; i++) {
            final View child = getChildAt(i);

            measureChildWithMargins(child, widthMeasureSpec, CELL_WIDTH, heightMeasureSpec, CELL_HEIGHT);

            width += child.getMeasuredWidth();
            height += child.getMeasuredHeight();
        }

        setMeasuredDimension(
                resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }


    private Rect setRect(final int cellWidth, final int cellHeight, final int rowIndex, final int columnIndex) {
        Rect rect = new Rect();
        rect.left = columnIndex * cellWidth;
        rect.right = rect.left + cellWidth;
        rect.top = rowIndex * cellHeight;
        rect.bottom = rect.top + cellHeight;
        return rect;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int childCount = getChildCount();

        for (int i = 0; i != childCount; i++) {
            final CellView child = (CellView) getChildAt(i);

            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();
            Rect mTmpChildRect = setRect(width, height, child.getRow(), child.getColumn());

            child.layout(mTmpChildRect.left, mTmpChildRect.top,
                        mTmpChildRect.right, mTmpChildRect.bottom);
        }
    }

    private void init() {
        createField();
        createChildrenView();
        TopPanelSender.addListener(this);
    }

    private void createField() {
        this.field = new ActionField(10, 10, 10);
        field.addListener(this);
    }

    private void createNewGame() {
        createField();
        changeChildrenView();
    }

    private void changeChildrenView() {
        for (CellView cellView : cellViews) {
            final int row = cellView.getRow();
            final int column = cellView.getColumn();
            cellView.changeCell(field.getCell(row, column));
        }
    }

    private void createChildrenView() {
        cellSender = new CellSender();
        cellSender.addListener(this);
        int row = field.getRow();
        int column = field.getColumn();
        for (int i = 0; i != row; i++) {
            for (int j = 0; j != column; j++) {
                CellView cellView = new CellView(context, field.getCell(i, j), cellSender);
                this.addView(cellView, CELL_WIDTH, CELL_HEIGHT);
                cellViews.add(cellView);
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FieldView.LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    public void restartGame() {
        createNewGame();
        cellSender.restartCellSender();
        this.invalidate();
    }

    @Override
    public void setFlagMode() {
        cellSender.setFlagMode();
    }

    /**
     * Custom per-child layout information.
     */
    private static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        final int counterChildren = getChildCount();
        for (int i = 0; i != counterChildren; i++) {
            CellView children = (CellView) getChildAt(i);
            children.invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        invalidate();
    }

    @Override
    public void cellChange(Cell cell) {
        this.invalidate();
    }

    @Override
    public void gameOver(boolean isWin) {
        FieldSender.sayGameOver(isWin);
        cellSender.setGameOver(isWin);
        this.invalidate();
    }

    @Override
    public void scoreChange(int score) {
        FieldSender.sayScoreChanged(score);
    }

    @Override
    public void openCell(Cell c) {
        field.openCell(c.row, c.column);
    }

    @Override
    public void putTagged(Cell c) {
        field.putTagged(c.row, c.column);
    }
}
