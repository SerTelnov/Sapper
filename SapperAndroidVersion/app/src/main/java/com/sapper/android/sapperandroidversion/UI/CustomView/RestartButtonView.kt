package com.sapper.android.sapperandroidversion.UI.CustomView

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.ViewManager
import android.widget.ImageView
import com.sapper.android.sapperandroidversion.R
import com.sapper.android.sapperandroidversion.UI.Listeners.EventsSenderTopPanelListener
import com.sapper.android.sapperandroidversion.UI.Listeners.RestartButtonListener
import org.jetbrains.anko.custom.ankoView

/**
 * Created by Sergey on 01.02.2018.
 */

class RestartButtonView : ImageView, EventsSenderTopPanelListener {

    private val listeners = ArrayList<RestartButtonListener>()

    private val mHandler = Handler()

    private val timer = Runnable {
        setImageResource(R.drawable.smile)
    }

    constructor(ctx: Context) : super(ctx) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init()
    }

    private fun init() {
        id = R.id.tp_restart_button

        setImageResource(R.drawable.smile)
        scaleType = ImageView.ScaleType.FIT_CENTER

        setOnClickListener {
            listeners.forEach {
                it.restartGame()
            }
        }
    }

    public fun addListener(listener: RestartButtonListener) {
        listeners.add(listener)
    }

    override fun startNewGame(countOfBomb: Int) {
        setImageResource(R.drawable.smile)
    }

    override fun clickField() {
        setImageResource(R.drawable.bored)

        mHandler.postDelayed(timer, 150)
    }

    override fun gameOver(isWin: Boolean) {
        mHandler.removeCallbacks(timer)

        if (isWin) {
            setImageResource(R.drawable.happy)
        } else {
            setImageResource(R.drawable.sad)
        }
    }

    override fun scoreChanged(newScore: Int) { }
    override fun gameStart() { }
    override fun makePause() { }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.restartButtonView(theme: Int = 0) = restartButtonView({}, theme)
inline fun ViewManager.restartButtonView(init: RestartButtonView.() -> Unit, theme: Int = 0) = ankoView(::RestartButtonView, theme, init)
