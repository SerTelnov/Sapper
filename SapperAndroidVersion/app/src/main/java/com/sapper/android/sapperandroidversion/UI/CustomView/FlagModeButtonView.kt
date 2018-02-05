package com.sapper.android.sapperandroidversion.UI.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.ViewManager
import android.widget.ImageView
import com.sapper.android.sapperandroidversion.R
import com.sapper.android.sapperandroidversion.UI.Listeners.EventsSenderTopPanelListener
import com.sapper.android.sapperandroidversion.UI.Listeners.FlagModeButtonListener
import org.jetbrains.anko.custom.ankoView

/**
 * Created by Sergey on 01.02.2018.
 */

class FlagModeButtonView : ImageView, EventsSenderTopPanelListener {

    private var onFlagMode = false
    private val listeners = ArrayList<FlagModeButtonListener>()

    constructor(ctx: Context) : super(ctx) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init()
    }

    private fun init() {
        id = R.id.tp_flag_mode_button

        setImageResource(R.drawable.flag)
        scaleType = ImageView.ScaleType.FIT_CENTER

        setOnClickListener {
            onFlagMode = !onFlagMode
            if (onFlagMode) {
                setImageResource(R.drawable.flag_tagged)
            } else {
                setImageResource(R.drawable.flag)
            }

            listeners.forEach {
                it.changeFlagMode(onFlagMode)
            }
        }
    }

    public fun addListener(listener: FlagModeButtonListener) {
        listeners.add(listener)
    }

    override fun gameOver(isWin: Boolean) {
        startNewGame(0)
    }

    override fun startNewGame(countOfBomb: Int) {
        setImageResource(R.drawable.flag)
        onFlagMode = false
    }

    override fun scoreChanged(newScore: Int) { }
    override fun gameStart() { }
    override fun clickField() { }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.flagModeButtonView(theme: Int = 0) = flagModeButtonView({}, theme)
inline fun ViewManager.flagModeButtonView(init: FlagModeButtonView.() -> Unit, theme: Int = 0) = ankoView(::FlagModeButtonView, theme, init)
