package com.sapper.android.sapperandroidversion

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sapper.android.sapperandroidversion.UI.CustomView.scores.ScoresAdapter

class ScoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        val mScoresList = findViewById<RecyclerView>(R.id.rv_scores)
        mScoresList.layoutManager = LinearLayoutManager(this)
        mScoresList.setHasFixedSize(true)
        mScoresList.adapter = ScoresAdapter()
    }
}
