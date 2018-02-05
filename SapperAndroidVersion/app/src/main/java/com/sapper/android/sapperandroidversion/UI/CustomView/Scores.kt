package com.sapper.android.sapperandroidversion.UI.CustomView

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.util.AttributeSet
import android.view.ViewManager
import android.widget.TextView
import com.sapper.android.sapperandroidversion.R
import com.sapper.android.sapperandroidversion.UI.Listeners.EventsSenderTopPanelListener
import org.jetbrains.anko.custom.ankoView

/**
 * Created by Sergey on 02.02.2018.
 */

class ScoresView : TextView, EventsSenderTopPanelListener {

    private var numberOfBombs = 15
    private val HINT = resources.getString(R.string.score_hint)

    constructor(ctx: Context) : super(ctx) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        id = R.id.tp_scores

        scoreChanged(0)
    }

    @SuppressLint("SetTextI18n")
    override fun scoreChanged(newScore: Int) {
        text = "$HINT $newScore/$numberOfBombs"
    }

    @SuppressLint("SetTextI18n")
    override fun startNewGame(countOfBomb: Int) {
        numberOfBombs = countOfBomb

        scoreChanged(0)
    }

    override fun gameStart() { }
    override fun clickField() { }
    override fun gameOver(isWin: Boolean) { }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.scoresView(theme: Int = 0) = scoresView({}, theme)
inline fun ViewManager.scoresView(init: ScoresView.() -> Unit, theme: Int = 0) =
        ankoView(::ScoresView, theme, init)

class StopWatchView : TextView, EventsSenderTopPanelListener {

    private val mHandler = Handler()
    private var mTime = 0L
    private val START_HINT = resources.getString(R.string.stopwatch_start_hint)
    private val HINT = resources.getString(R.string.stopwatch_hint)

    private val timeUpdaterRunnable = object : Runnable {

        @SuppressLint("SetTextI18n")
        override fun run() {
            val start = mTime

            val millis = SystemClock.uptimeMillis() - start
            var sec = (millis / 1000).toInt()

            var min = sec / 60
            sec %= 60

            val hour = min / 60
            min %= 60

            text = "$HINT $hour:" + String.format("%02d", min) + ":" + String.format("%02d", sec)

            mHandler.postDelayed(this, 1000)
        }
    }

    constructor(ctx: Context) : super(ctx) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        id = R.id.tp_timer

        text = START_HINT
    }

    override fun gameStart() {
        mTime = SystemClock.uptimeMillis()
        mHandler.removeCallbacks(timeUpdaterRunnable)

        mHandler.postDelayed(timeUpdaterRunnable, 100)
    }

    override fun gameOver(isWin: Boolean) {
        mHandler.removeCallbacks(timeUpdaterRunnable)
    }

    override fun startNewGame(countOfBomb: Int) {
        text = START_HINT

        mTime = 0L
        mHandler.removeCallbacks(timeUpdaterRunnable)
    }

    override fun scoreChanged(newScore: Int) { }
    override fun clickField() { }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.stopWatchView(theme: Int = 0) = stopWatchView({}, theme)
inline fun ViewManager.stopWatchView(init: StopWatchView.() -> Unit, theme: Int = 0) =
        ankoView(::StopWatchView, theme, init)
