package com.sapper.telnov.sapperandroidapp.customView.topPanel;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import com.sapper.telnov.sapperandroidapp.R;
import com.sapper.telnov.sapperandroidapp.customView.fieldPanel.FieldSender;
import com.sapper.telnov.sapperandroidapp.customView.fieldPanel.IFieldListener;

/**
 * Created by Sergey on 02.08.2017.
 */

public class RestartButton extends android.support.v7.widget.AppCompatImageButton implements IFieldListener {

    private CountDownTimer boredModeTimer;
    private final int BORED_MODE_TIMER = 150;

    public RestartButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RestartButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        FieldSender.addListener(this);

        setSmileIcon();

        boredModeTimer = new CountDownTimer(BORED_MODE_TIMER, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                setSmileIcon();
            }
        };

        this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TopPanelSender.sayRestartGame();
                setSmileIcon();
            }
        });
    }

    private void setSmileIcon() {
        setBackgroundResource(R.drawable.smile);
    }

    private void setBoredIcon() {
        setBackgroundResource(R.drawable.sad);
    }

    @Override
    public void touchField() {
        setBackgroundResource(R.drawable.bored);
        boredModeTimer.start();
    }

    @Override
    public void gameOver(boolean isWin) {
        if (!isWin) {
            setBoredIcon();
        }
    }

    @Override
    public void scoreChanged(int score) { }
}
