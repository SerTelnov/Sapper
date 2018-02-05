package com.sapper.android.sapperandroidversion.UI

import android.graphics.Color
import android.graphics.Paint

/**
 * Created by Sergey on 24.01.2018.
 */

private val colors = MyPaints()

public val CLOSE_CELL = colors.closeCell
public val LINE_PAINT = colors.linePaint
public val BOMB_PAINT = colors.bombPaint
public val EMPTY_CELL_PAINT = colors.emptyCellPaint
public val NUMBER_PAINT = colors.numberPaint
public val TAGGED_PAINT = colors.taggedPaint
public val DE_ACTIVATE_BOMB_PAINT = colors.deActivateBombPaint

public val NUMBER1 = colors.number1
public val NUMBER2 = colors.number2
public val NUMBER3 = colors.number3
public val NUMBER4 = colors.number4
public val NUMBER5 = colors.number5
public val NUMBER6 = colors.number6
public val NUMBER7 = colors.number7
public val NUMBER8 = colors.number8

public fun getDigitPaint(number: Int) = when(number) {
    1 -> NUMBER1
    2 -> NUMBER2
    3 -> NUMBER3
    4 -> NUMBER4
    5 -> NUMBER5
    6 -> NUMBER6
    7 -> NUMBER7
    8 -> NUMBER8
    else -> NUMBER_PAINT
}

private class MyPaints {
    val closeCell: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val linePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val bombPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val emptyCellPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val numberPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val taggedPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val deActivateBombPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    val number1 = Paint(Paint.ANTI_ALIAS_FLAG)
    val number2 = Paint(Paint.ANTI_ALIAS_FLAG)
    val number3 = Paint(Paint.ANTI_ALIAS_FLAG)
    val number4 = Paint(Paint.ANTI_ALIAS_FLAG)
    val number5 = Paint(Paint.ANTI_ALIAS_FLAG)
    val number6 = Paint(Paint.ANTI_ALIAS_FLAG)
    val number7 = Paint(Paint.ANTI_ALIAS_FLAG)
    val number8 = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        closeCell.color = Color.parseColor("#2979FF")

        linePaint.color = Color.BLACK

        bombPaint.color = Color.RED

        emptyCellPaint.color = Color.LTGRAY

        taggedPaint.color = Color.YELLOW

        deActivateBombPaint.color = Color.GREEN

        number1.color = Color.parseColor("#01579B")
        number2.color = Color.parseColor("#1B5E20")
        number3.color = Color.parseColor("#B71C1C")
        number4.color = Color.parseColor("#1A237E")
        number5.color = Color.parseColor("#1A237E")
        number6.color = Color.parseColor("#00BCD4")
        number7.color = Color.parseColor("#1A237E")
        number8.color = Color.parseColor("#AA00FF")
    }
}
