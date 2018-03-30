package com.sapper.android.sapperandroidversion

import com.sapper.android.sapperandroidversion.UI.EventsSender
import com.sapper.android.sapperandroidversion.UI.Listeners.EventsSenderActionFieldListener
import com.sapper.android.sapperandroidversion.UI.Listeners.EventsSenderTopPanelListener
import game.ActionField
import game.Cell
import org.junit.Assert.assertEquals
import org.junit.Test
import tests.CustomObject.CustomActionField

/**
* Created by Telnov Sergey on 02.02.2018.
*/

class UIUnitTest {
    @Test
    fun senderEventOpenCellTest() {
        val game = ActionField(5, 5, 1)
        val sender = EventsSender(game)

        val tpListener = SenderTPListenerTest()
        val afListener = SenderAFListenerTest()

        sender.addTopPanelListener(tpListener)
        sender.addActionFieldListeners(afListener)

        game.openCell(0, 0)
        assertEquals(true, tpListener.wasStartNewGameEvent)
        assertEquals(true, afListener.wasStartNewGameEvent)
    }

    @Test
    fun senderEventPutTaggedTest() {
        val customGame = CustomActionField(9, 9, CustomActionField.FieldType.EMPTY_FIELD)
        val sender = EventsSender(customGame)
        val listener = SenderTPListenerTest()

        sender.addTopPanelListener(listener)

        customGame.putTagged(1, 1)
        assertEquals(true, listener.wasScoreChangedEvent)
    }

    @Test
    fun senderEventGameOverTest() {
        val customGame = CustomActionField(9, 9, CustomActionField.FieldType.BOMB_FIELD)
        val sender = EventsSender(customGame)

        val tpListener = SenderTPListenerTest()
        val afListener = SenderAFListenerTest()

        sender.addTopPanelListener(tpListener)
        sender.addActionFieldListeners(afListener)

        customGame.openCell(0, 0)
        assertEquals(true, tpListener.wasGameOverEvent)
        assertEquals(true, afListener.wasGameOverEvent)
    }

    class SenderTPListenerTest : EventsSenderTopPanelListener {
        override fun makePause() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        public var wasGameStartEvent = false
        public var wasClickFieldEvent = false
        public var wasGameOverEvent = false
        public var wasScoreChangedEvent = false
        public var wasStartNewGameEvent = false

        override fun gameStart() { wasGameStartEvent = true }
        override fun clickField() { wasClickFieldEvent = true }
        override fun gameOver(isWin: Boolean) { wasGameOverEvent = true }
        override fun scoreChanged(newScore: Int) { wasScoreChangedEvent = true }
        override fun startNewGame(countOfBomb: Int) { wasStartNewGameEvent = true }
    }

    class SenderAFListenerTest : EventsSenderActionFieldListener {
        var wasGameOverEvent = false
        var wasCellsChangedEvent = false
        var wasStartNewGameEvent = false

        override fun gameOver(isWin: Boolean, cells: Array<Array<Cell>>) { wasGameOverEvent = true }
        override fun cellsChanged(cells: Array<Array<Cell>>) { wasCellsChangedEvent = true }
        override fun startNewGame(cells: Array<Array<Cell>>) { wasStartNewGameEvent = true }
    }
}