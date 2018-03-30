package com.sapper.android.sapperandroidversion.UI.CustomView.scores

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.sapper.android.sapperandroidversion.R

/**
 * Created by Telnov Sergey on 13.03.2018.
 */

class ScoreViewHolder public constructor(v: View?) : RecyclerView.ViewHolder(v) {

    val mTextHint : TextView = v!!.findViewById(R.id.tv_score_hint)
    private val mScoreNumber: TextView = v!!.findViewById(R.id.tv_item_score)

    fun bind(position: Int) {
        mScoreNumber.text = position.toString()
    }
}