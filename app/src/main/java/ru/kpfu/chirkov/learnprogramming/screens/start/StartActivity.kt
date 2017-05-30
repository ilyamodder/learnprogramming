package ru.kpfu.chirkov.learnprogramming.screens.start

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import ru.kpfu.chirkov.learnprogramming.R

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StartActivityUI().setContentView(this)
    }

    class StartActivityUI : AnkoComponent<StartActivity> {
        override fun createView(ui: AnkoContext<StartActivity>) = ui.apply {
            relativeLayout {
                textView {
                    id = R.id.title
                    text = "Добро пожаловать!"
                    textColor = android.R.color.white
                    textSize = 30f
                }.lparams {
                    alignParentTop()
                    centerHorizontally()
                    topMargin = dip(72)
                }

                textView {
                    text = "Приложение Learn Programming позволит вам с легкостью изучить новые языки программирования и проверить свои знания на практике."
                    textColor = android.R.color.white
                    textSize = 20f
                }.lparams(width = matchParent) {
                    leftMargin = dip(22)
                    rightMargin = dip(22)
                    topMargin = dip(100)
                    below(R.id.title)
                }

                button("Начать").lparams {
                    centerHorizontally()
                    bottomMargin = dip(56)
                }.setOnClickListener {
                    toast("Click")
                }
            }
        }.view
    }
}
