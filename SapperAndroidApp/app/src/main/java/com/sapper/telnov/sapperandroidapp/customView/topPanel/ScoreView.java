package com.sapper.telnov.sapperandroidapp.customView.topPanel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.sapper.telnov.sapperandroidapp.customView.fieldPanel.FieldSender;
import com.sapper.telnov.sapperandroidapp.customView.fieldPanel.IFieldListener;

/**
 * Created by Sergey on 03.08.2017.
 */

public class ScoreView extends android.support.v7.widget.AppCompatTextView implements IFieldListener, ITopPanelListener {
    private final String SCORE_TEXT = "Score: ";
    private final int BOMB_COUNTER = 10;


    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScoreView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setStartScore();
        TopPanelSender.addListener(this);
        FieldSender.addListener(this);
    }

    private void setStartScore() {
        this.setText(SCORE_TEXT + "0/" + BOMB_COUNTER);
    }

    @Override
    public void touchField() { }

    @Override
    public void gameOver(boolean b) { }

    @Override
    public void scoreChanged(int score) {
        this.setText(SCORE_TEXT + score + "/" + BOMB_COUNTER);
    }

    @Override
    public void restartGame() {
        setStartScore();
    }

    @Override
    public void setFlagMode() { }
}
