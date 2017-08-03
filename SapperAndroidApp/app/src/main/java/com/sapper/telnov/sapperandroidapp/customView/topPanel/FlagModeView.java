package com.sapper.telnov.sapperandroidapp.customView.topPanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sapper.telnov.sapperandroidapp.R;

/**
 * Created by Sergey on 03.08.2017.
 */

public class FlagModeView extends android.support.v7.widget.AppCompatButton
        implements ITopPanelListener {

    private boolean isFlagMode = false;

    public FlagModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlagModeView(Context context) {
        super(context);
        init();
    }

    private void init() {
        TopPanelSender.addListener(this);
        offFlagMode();
        this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isFlagMode) {
                    offFlagMode();
                } else {
                    onFlagMode();
                }
                TopPanelSender.saySetFlagMode();
            }
        });
    }

    private void onFlagMode() {
        isFlagMode = true;
        setBackgroundResource(R.drawable.flag_tagged);
    }

    private void offFlagMode() {
        isFlagMode = false;
        setBackgroundResource(R.drawable.flag);
    }

    @Override
    public void restartGame() {
        offFlagMode();
    }

    @Override
    public void setFlagMode() { }
}
