package ru.kpfu.chirkov.learnprogramming.screens.tasks

import ru.arturvasilov.rxloader.LifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.addSchedulers
import ru.kpfu.chirkov.learnprogramming.base.ListItem
import ru.kpfu.chirkov.learnprogramming.data.repository.LearnProgrammingRepositoryProvider

/**
 * @author ilya
 */
class TasksPresenter(val view: TasksView, val lifecycleHandler: LifecycleHandler) {
    fun init() {
        LearnProgrammingRepositoryProvider.learnProgrammingRepository
                .getTasks()
                .map {
                    val list = ArrayList<ListItem>()
                    it.mapping.forEach { (key, value) ->
                        list.add(it.categories.find { it.id == key }
                                ?: throw NullPointerException("category $key not found"))
                        value.mapTo(list) { id ->
                            it.tasks.find { it.id == id }
                                    ?: throw NullPointerException("task $id not found")
                        }
                    }
                    return@map list
                }
                .addSchedulers()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .compose<List<ListItem>>(lifecycleHandler.load(R.id.tasks_loader))
                .subscribe({
                    view.showTasks(it)
                }, {
                    it.printStackTrace()
                    view.showError(it.localizedMessage)
                })
    }
}