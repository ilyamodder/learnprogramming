package ru.kpfu.chirkov.learnprogramming.screens.task

import ru.arturvasilov.rxloader.LifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.addSchedulers
import ru.kpfu.chirkov.learnprogramming.data.repository.LearnProgrammingRepositoryProvider

/**
 * @author ilya
 */
class TaskPresenter(val id: Long, val view: TaskView, val lifecycleHandler: LifecycleHandler) {
    fun init() {
        LearnProgrammingRepositoryProvider.learnProgrammingRepository
                .getTask(id)
                .addSchedulers()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .compose(lifecycleHandler.load(R.id.task_loader))
                .subscribe({
                    view.showTask(it)
                }, {
                    view.showError(it.localizedMessage)
                })
    }

    fun codeSubmitted(code: String) {

    }
}