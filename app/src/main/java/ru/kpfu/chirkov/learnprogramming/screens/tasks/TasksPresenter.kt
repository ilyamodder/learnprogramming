package ru.kpfu.chirkov.learnprogramming.screens.tasks

import ru.arturvasilov.rxloader.LifecycleHandler
import ru.kpfu.chirkov.learnprogramming.addSchedulers
import ru.kpfu.chirkov.learnprogramming.data.api.ApiFactory

/**
 * @author ilya
 */
class TasksPresenter(val view: TasksView, val lifecycleHandler: LifecycleHandler) {
    fun init() {
        ApiFactory.getLearnProgrammingService()
                .getPosts()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view::hideLoading }
                .compose(lifecycleHandler.load(0))
                .map {
                    val list = ArrayList<Any>()
                    for ((key, value) in it.mapping) {
                        list.add(it.categories.find { it.id == key } ?: throw NullPointerException())
                        for (id in value) {
                            list.add(it.categories.find { it.id == id } ?: throw NullPointerException())
                        }
                    }
                    return@map list
                }
                .addSchedulers()
                .subscribe(view::showTasks, {
                    view.showError(it.localizedMessage)
                })
    }
}