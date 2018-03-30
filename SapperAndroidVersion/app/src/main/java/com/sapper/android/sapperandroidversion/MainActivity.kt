package com.sapper.android.sapperandroidversion

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.sapper.android.sapperandroidversion.UI.CustomView.*
import com.sapper.android.sapperandroidversion.UI.EventsSender
import org.jetbrains.anko.*

private val sender = EventsSender()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onPause() {
        super.onPause()
        sender.sayMakePause();
    }

//    override fun onResume() {
//        super.onResume()
//        sender.restartGame()
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.menu_item_pause) {
            sender.sayMakePause()
        } else if (id == R.id.menu_to_start_activity) {
            startActivity(Intent(this, StartActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}

class MainActivityUI : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            relativeLayout {
                id = R.id.cv_top_panel

                setBackgroundColor(Color.GRAY)

                val mRestartButton = restartButtonView()
                        .lparams(wrapContent, matchParent) {
                            centerHorizontally()
                            padding = dip(5)
                }

                mRestartButton.addListener(sender)

                val mFlagModeButton = flagModeButtonView()
                        .lparams(wrapContent, matchParent) {
                            leftOf(R.id.tp_restart_button)
                            padding = dip(5)
                }

                mFlagModeButton.addListener(sender)

                relativeLayout {
                    id = R.id.rl_scores

                    val scores = scoresView()
                            .lparams(wrapContent, wrapContent) {
                                alignParentTop()
                                gravity = Gravity.END
                            }

                    val timer = stopWatchView()
                            .lparams(wrapContent, wrapContent) {
                                alignParentBottom()
                                gravity = Gravity.END
                            }

                    sender.addTopPanelListener(scores)
                    sender.addTopPanelListener(timer)
                }.lparams(wrapContent, matchParent) {
                    rightOf(R.id.tp_restart_button)
                }

                sender.addTopPanelListener(mFlagModeButton)
                sender.addTopPanelListener(mRestartButton)
            }.lparams(matchParent, dip(resources.getDimension(R.dimen.topPanel_height_size))) {
                alignParentTop()
            }

            val mActionField = fieldView({
                id = R.id.cv_action_field
                lparams(wrapContent, wrapContent) {
                    below(R.id.cv_top_panel)
                }
            })

            mActionField.addListener(sender)
            sender.addActionFieldListeners(mActionField)
        }
    }
}
