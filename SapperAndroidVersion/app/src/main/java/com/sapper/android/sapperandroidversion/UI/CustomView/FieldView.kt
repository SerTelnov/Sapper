package com.sapper.android.sapperandroidversion.UI.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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

    private var cells: Array<Array<Cell>>? = null
    private var gameFinished = false
    private var isWin = false

    private val listeners = ArrayList<ActionFieldListener>()

    private val CELL_SIZE = dip(resources.getDimension(R.dimen.activeField_cell_size))
    private val EMPTY_SPACE_SIZE = dip(resources.getDimension(R.dimen.activeField_square_empty_space_size))
    private val DIGIT_SIZE = sp(resources.getDimension(R.dimen.activeField_digit_size)).toFloat()

    private val DIGIT_X = dip(resources.getDimension(R.dimen.af_digit_start_x)).toFloat()
    private val DIGIT_Y = dip(resources.getDimension(R.dimen.af_digit_start_y)).toFloat()

    private var startWidth = 0
    private var startHeight = 0

    constructor(ctx: Context) : super(ctx) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init()
    }

    private fun init() {
        for (digit in 0..8) {
            getDigitPaint(digit).textSize = DIGIT_SIZE
        }
    }

    public fun addListener(listener: ActionFieldListener) {
        listeners.add(listener)
    }

    private val gestureDetector = GestureDetector(object : GestureDetector.SimpleOnGestureListener() {

        fun isNotOutOfBound(row: Int, column: Int) =
                row < cells?.size ?: 9 && column < cells?.get(0)?.size ?: 9

        override fun onLongPress(e: MotionEvent) {
            val row = ((e.x - startWidth) / CELL_SIZE).toInt()
            val column = ((e.y - startHeight) / CELL_SIZE).toInt()

            if (isNotOutOfBound(row, column)) {
                listeners.forEach { it.longClickCell(row, column) }
            }
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val row = ((e.x - startWidth) / CELL_SIZE).toInt()
            val column = ((e.y - startHeight) / CELL_SIZE).toInt()

            if (isNotOutOfBound(row, column)) {
                listeners.forEach { it.clickCell(row, column) }
            }
            return super.onSingleTapUp(e)
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    })

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun startNewGame(cells: Array<Array<Cell>>) {
        gameFinished = false

        cellsChanged(cells)
    }

    override fun gameOver(isWin: Boolean, cells: Array<Array<Cell>>) {
        gameFinished = true
        this.isWin = isWin

        cellsChanged(cells)
    }

    override fun cellsChanged(cells: Array<Array<Cell>>) {
        this.cells = cells

        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (startHeight == 0 && startWidth == 0) {
            val fieldWidth = if (cells != null) cells!!.size else 9 * CELL_SIZE.toInt()
            val fieldHeath = if (cells != null) cells!![0].size else 9 * CELL_SIZE.toInt()

            startWidth = (measuredWidth - fieldWidth) / 2
            startHeight = (measuredHeight - fieldHeath) / 4
        }
    }

    private fun Canvas.drawRect(i: Int, j: Int, paint: Paint) {
        drawRect((i * CELL_SIZE + EMPTY_SPACE_SIZE + startWidth).toFloat(),
                (j * CELL_SIZE + EMPTY_SPACE_SIZE + startHeight).toFloat(),
                ((i + 1) * CELL_SIZE + startWidth).toFloat(),
                ((j + 1) * CELL_SIZE + startHeight).toFloat(), paint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        setBackgroundColor(Color.GRAY)

//        TODO("fixed cells draw! finish game mode don't work yet")

        if (cells == null) {
            for (i in 0 until 9) {
                for (j in 0 until 9) {
                    canvas.drawRect(i, j, CLOSE_CELL)
                }
            }
        } else {
            for (i in 0 until cells!!.size) {
                for (j in 0 until cells!![0].size) {
                    val currCell = cells!![i][j]
                    if (!currCell.isOpened) {
                        canvas.drawRect(i, j, CLOSE_CELL)
                    } else if (currCell.isTagged) {
                        canvas.drawRect(i, j, TAGGED_PAINT)
                    } else if (currCell.isNumber) {
                        canvas.drawRect(i, j, EMPTY_CELL_PAINT)
                        canvas.drawText((currCell.number).toString(),
                                i * CELL_SIZE + startWidth + DIGIT_X,
                                j * CELL_SIZE + startHeight + DIGIT_Y,
                                getDigitPaint(currCell.number))
                    } else if (currCell.isEmptyPoint) {
                        canvas.drawRect(i, j, EMPTY_CELL_PAINT)
                    } else if (currCell.isBomb) {
                        canvas.drawRect(i, j, BOMB_PAINT)
                    }
                }
            }
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.fieldView(theme: Int = 0) = fieldView({}, theme)
inline fun ViewManager.fieldView(init: FieldView.() -> Unit, theme: Int = 0) = ankoView(::FieldView, theme, init)
