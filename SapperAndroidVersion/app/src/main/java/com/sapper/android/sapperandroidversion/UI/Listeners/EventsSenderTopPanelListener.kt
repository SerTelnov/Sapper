package com.sapper.android.sapperandroidversion.UI.Listeners

/**
 * Created by Sergey on 01.02.2018.
 */

interface EventsSenderTopPanelListener {
    fun gameStart()
    fun clickField()
    fun gameOver(isWin: Boolean)
    fun scoreChanged(newScore: Int)
    fun startNewGame(countOfBomb: Int)
    fun makePause()
}