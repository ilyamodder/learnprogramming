package ru.kpfu.chirkov.learnprogramming.screens.start

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*
import org.jetbrains.anko.startActivity
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.screens.main.MainActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()

        btnStart.setOnClickListener {
            startActivity<MainActivity>();
            finish()
        }
    }
}
