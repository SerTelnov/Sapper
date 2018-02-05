package com.sapper.android.sapperandroidversion.UI.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewManager
import com.sapper.android.sapperandroidversion.R
import com.sapper.android.sapperandroidversion.UI.*
import com.sapper.android.sapperandroidversion.UI.Listeners.ActionFieldListener
import com.sapper.android.sapperandroidversion.UI.Listeners.EventsSenderActionFieldListener
import game.Cell
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip
import org.jetbrains.anko.sp

/**
 * Created by Sergey on 23.01.2018.
 */

class FieldView : View, EventsSenderActionFieldListener {

    private lateinit var table: Array<Array<Rect>>
    private var cells: Array<Array<Cell>>? = null
    private var gameFinished = false
    private var isWin = false

    private val listeners = ArrayList<ActionFieldListener>()

    private val CELL_SIZE = dip(resources.getDimension(R.dimen.activeField_cell_size))
    private val EMPTY_SPACE_SIZE = dip(resources.getDimension(R.dimen.activeField_square_empty_space_size))
    private val DIGIT_SIZE = sp(resources.getDimension(R.dimen.activeField_digit_size)).toFloat()

    constructor(ctx: Context) : super(ctx) {
        init(9, 9)
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init(9, 9)
    }

    private fun init(row: Int, column: Int) {

        table = Array<Array<Rect>>(row) {
            val i = it

            Array<Rect>(column) {
                Rect(i * CELL_SIZE + EMPTY_SPACE_SIZE,
                        it * CELL_SIZE + EMPTY_SPACE_SIZE,
                        (i + 1) * CELL_SIZE,
                        (it + 1) * CELL_SIZE
                )}
        }

        for (digit in 0..8) {
            getDigitPaint(digit).textSize = DIGIT_SIZE
        }
    }

    public fun addListener(listener: ActionFieldListener) {
        listeners.add(listener)
    }

    private val gestureDetector = GestureDetector(object : GestureDetector.SimpleOnGestureListener() {

        override fun onLongPress(e: MotionEvent) {
            val row = (e.x / CELL_SIZE).toInt()
            val column = (e.y / CELL_SIZE).toInt()

            listeners.forEach {
                it.longClickCell(row, column)
            }
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val row = (e.x / CELL_SIZE).toInt()
            val column = (e.y / CELL_SIZE).toInt()

            listeners.forEach {
                it.clickCell(row, column)
            }
            return super.onSingleTapUp(e)
        }
    })

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun startNewGame(cells: Array<Array<Cell>>) {
        this.cells = null

        gameFinished = false

        invalidate()
    }

    override fun gameOver(isWin: Boolean, cells: Array<Array<Cell>>) {
        this.cells = cells

        gameFinished = true
        this.isWin = isWin

        invalidate()
    }

    override fun cellsChanged(cells: Array<Array<Cell>>) {
        this.cells = cells

        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        setBackgroundColor(Color.GRAY)

//        TODO("fixed cells draw!, finish game mode don't work yet")

        if (cells == null) {
            for (i in 0 until table.size) {
                for (j in 0 until table[0].size) {
                    canvas.drawRect(table[i][j], CLOSE_CELL)
                }
            }
        } else {
            for (i in 0 until table.size) {
                for (j in 0 until table[0].size) {
                    val currCell = cells!![i][j]
                    if (!currCell.isOpened) {
                        canvas.drawRect(table[i][j], CLOSE_CELL)
                    } else if (currCell.isTagged) {
                        canvas.drawRect(table[i][j], TAGGED_PAINT)
                    } else if (currCell.isNumber) {
                        canvas.drawRect(table[i][j], EMPTY_CELL_PAINT)
                        canvas.drawText((currCell.number).toString(),
                                table[i][j].exactCenterX(),
                                table[i][j].exactCenterY(),
                                getDigitPaint(currCell.number))
                    } else if (currCell.isEmptyPoint) {
                        canvas.drawRect(table[i][j], EMPTY_CELL_PAINT)
                    } else if (currCell.isBomb) {
                        canvas.drawRect(table[i][j], BOMB_PAINT)
                    }
                }
            }
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.fieldView(theme: Int = 0) = fieldView({}, theme)
inline fun ViewManager.fieldView(init: FieldView.() -> Unit, theme: Int = 0) =
        ankoView(::FieldView, theme, init)
