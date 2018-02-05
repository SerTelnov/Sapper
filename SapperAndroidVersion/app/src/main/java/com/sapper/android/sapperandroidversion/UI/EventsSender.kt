package com.sapper.android.sapperandroidversion.UI

import com.sapper.android.sapperandroidversion.UI.Listeners.*
import game.ActionField
import game.Cell
import game.IGameListener

/**
 * Created by Sergey on 01.02.2018.
 */

class EventsSender : ActionFieldListener, IGameListener,
        RestartButtonListener, FlagModeButtonListener {

    public val numberOfTopPanelListeners
        get() = topPanelListeners.size

    public val numberOfActionFieldListeners
        get() = actionFieldListeners.size

    private lateinit var currGame: ActionField
    private var onFlagMode = false
    private var isGameOver = false

    private val topPanelListeners = ArrayList<EventsSenderTopPanelListener>()
    private val actionFieldListeners = ArrayList<EventsSenderActionFieldListener>()

    public constructor() {
        init(9, 9, 15)
    }

    public constructor(afRowCounter: Int, afColumnCounter: Int, afCounterOfBomb: Int) {
        init(afRowCounter, afColumnCounter, afCounterOfBomb)
    }

//    this constructor for tests
    public constructor(game: ActionField) {
        currGame = game

        currGame.addListener(this)
    }

    private fun init(row: Int, column: Int, counterOfBombs: Int) {
        currGame = ActionField(row, column, counterOfBombs)

        currGame.addListener(this)
    }

    public fun addTopPanelListener(listener: EventsSenderTopPanelListener) {
        topPanelListeners.add(listener)
    }

    public fun addActionFieldListeners(listener: EventsSenderActionFieldListener) {
        actionFieldListeners.add(listener)
    }

////////    ActionField events:
    override fun clickCell(row: Int, column: Int) {
        if (isGameOver)
            return
        else if (onFlagMode) {
            currGame.putTagged(row, column)
        } else {
            topPanelListeners.forEach {
                it.clickField()
            }

            currGame.openCell(row, column)
        }
    }

    override fun longClickCell(row: Int, column: Int) {
        currGame.putTagged(row, column)
    }

////////    Game events:
    override fun gameOver(isWin: Boolean) {
        isGameOver = true

        topPanelListeners.forEach {
            it.gameOver(isWin)
        }

        actionFieldListeners.forEach {
            it.gameOver(isWin, currGame.field)
        }
    }

    override fun gameStart() {
        isGameOver = false

        topPanelListeners.forEach {
            it.gameStart()
        }
    }

    override fun scoreChange(newScore: Int) {
        topPanelListeners.forEach { it.scoreChanged(newScore) }
    }

    override fun cellsChanged(p0: MutableList<Cell>?) {
        actionFieldListeners.forEach { it.cellsChanged(currGame.field) }
    }

////////    TopPanel events:
    override fun restartGame() {
        currGame = ActionField(currGame.row, currGame.column, currGame.counterBomb)
        currGame.addListener(this)

        isGameOver = false

        topPanelListeners.forEach { it.startNewGame(currGame.counterBomb) }
        actionFieldListeners.forEach { it.startNewGame(currGame.field) }
    }

    override fun changeFlagMode(onFlagMode: Boolean) {
        this.onFlagMode = onFlagMode
    }
}