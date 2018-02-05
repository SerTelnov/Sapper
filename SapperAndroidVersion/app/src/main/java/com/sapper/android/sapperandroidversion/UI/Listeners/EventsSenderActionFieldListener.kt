package com.sapper.android.sapperandroidversion.UI.Listeners

import game.Cell

/**
 * Created by Sergey on 01.02.2018.
 */

interface EventsSenderActionFieldListener {
    fun gameOver(isWin: Boolean, cells: Array<Array<Cell>>)
    fun cellsChanged(cells: Array<Array<Cell>>)
    fun startNewGame(cells: Array<Array<Cell>>)
}