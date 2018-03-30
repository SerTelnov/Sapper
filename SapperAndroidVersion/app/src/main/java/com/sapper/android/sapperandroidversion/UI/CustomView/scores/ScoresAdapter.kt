package com.sapper.android.sapperandroidversion.UI.CustomView.scores

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sapper.android.sapperandroidversion.R

/**
 * Created by Telnov Sergey on 13.03.2018.
 */

open class ScoresAdapter : RecyclerView.Adapter<ScoreViewHolder>() {

    private var viewHolderCount = 0
    private val countOfItems = 50

    override fun onBindViewHolder(holder: ScoreViewHolder?, position: Int) {
        holder!!.bind(position)
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScoreViewHolder {
        val context = parent!!.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.score_list_item, parent, false)
        val viewHolder = ScoreViewHolder(view)
        viewHolderCount++

        return viewHolder
    }

    override fun getItemCount() = countOfItems
}
