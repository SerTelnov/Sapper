package com.sapper.android.sapperandroidversion

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.sapper.android.sapperandroidversion.UI.CustomView.*
import com.sapper.android.sapperandroidversion.UI.EventsSender
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }
}

class MainActivityUI : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        val sender = EventsSender()

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
