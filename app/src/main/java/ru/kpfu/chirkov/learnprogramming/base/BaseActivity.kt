package ru.kpfu.chirkov.learnprogramming.base

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

/**
 * @author ilya
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    override fun showError(error: String) {
        toast(error)
    }
}