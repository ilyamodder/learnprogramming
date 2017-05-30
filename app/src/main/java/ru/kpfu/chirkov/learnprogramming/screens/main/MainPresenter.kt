package ru.kpfu.chirkov.learnprogramming.screens.main

import ru.arturvasilov.rxloader.LifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.addSchedulers
import ru.kpfu.chirkov.learnprogramming.data.repository.LearnProgrammingRepositoryProvider

/**
 * @author ilya
 */
class MainPresenter(val view: MainView, val lifecycleHandler: LifecycleHandler) {
    fun init() {
        LearnProgrammingRepositoryProvider.learnProgrammingRepository
                .getShowStartScreen()
                .addSchedulers()
                .compose(lifecycleHandler.load(R.id.show_start_screen_loader))
                .filter { !it }
                .subscribe {
                    view.showStartScreen()
                    view.finish()
                }
    }
}