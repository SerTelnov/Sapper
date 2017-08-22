package com.sapper.telnov.sapperandroidapp.customView.topPanel;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.sapper.telnov.sapperandroidapp.customView.fieldPanel.FieldSender;
import com.sapper.telnov.sapperandroidapp.customView.fieldPanel.IFieldListener;

/**
 * Created by Sergey on 03.08.2017.
 */

public class TimerView extends android.support.v7.widget.AppCompatTextView
        implements IFieldListener, ITopPanelListener {

    private GameTimer clock = new GameTimer();
    private CountDownTimer timer;
    private boolean isGameFinished = false;
    private final String TIMER_TEXT = "Time: ";

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        TopPanelSender.addListener(this);
        FieldSender.addListener(this);
        timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (!isGameFinished) {
                    changeTime();
                    this.start();
                }
            }
        };
        changeTime();
        timer.start();
    }

    private void changeTime() {
        this.setText(TIMER_TEXT + "" + clock.getCurrentTimeText());
    }

    @Override
    public void restartGame() {
        isGameFinished = false;
        clock.setNewTimer();
        changeTime();
        timer.start();
    }

    @Override
    public void setFlagMode() { }

    @Override
    public void touchField() { }

    @Override
    public void gameOver(boolean isWin) {
        isGameFinished = true;
    }

    @Override
    public void scoreChanged(int score) { }
}
