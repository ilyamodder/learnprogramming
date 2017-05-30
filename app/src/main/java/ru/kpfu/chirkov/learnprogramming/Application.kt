package ru.kpfu.chirkov.learnprogramming

import com.orhanobut.hawk.Hawk

/**
 * @author ilya
 */
class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}