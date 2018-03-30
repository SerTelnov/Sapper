package com.sapper.android.sapperandroidversion

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class LevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        val clickListener = fun (_: View) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<Button>(R.id.bt_easy_level).setOnClickListener(clickListener)
        findViewById<Button>(R.id.bt_medium_level).setOnClickListener(clickListener)
        findViewById<Button>(R.id.bt_hard_level).setOnClickListener(clickListener)
    }
}
