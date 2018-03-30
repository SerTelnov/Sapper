package com.sapper.android.sapperandroidversion

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class StartActivity : AppCompatActivity() {

    private lateinit var mStartGameButton : Button
    private lateinit var mStatsButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        mStartGameButton = findViewById(R.id.bt_start_game)
        mStartGameButton.setOnClickListener({
            startActivity(Intent(this, LevelActivity::class.java))
        })

        mStatsButton = findViewById(R.id.bt_stats)
        mStatsButton.setOnClickListener({
            startActivity(Intent(this, ScoresActivity::class.java))
        })
    }
}
