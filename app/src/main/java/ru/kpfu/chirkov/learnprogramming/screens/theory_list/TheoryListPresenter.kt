package ru.kpfu.chirkov.learnprogramming.screens.theory_list

import ru.arturvasilov.rxloader.LifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.addSchedulers
import ru.kpfu.chirkov.learnprogramming.base.ListItem
import ru.kpfu.chirkov.learnprogramming.data.api.ApiFactory

/**
 * @author ilya
 */
class TheoryListPresenter(val view: TheoryListView, val lifecycleHandler: LifecycleHandler) {
    fun init() {
        ApiFactory.getLearnProgrammingService()
                .getTheory()
                .map {
                    val list = ArrayList<ListItem>()
                    it.mapping.forEach { (key, value) ->
                        list.add(it.categories.find { it.id == key }
                                ?: throw NullPointerException("category $key not found"))
                        value.mapTo(list) { id ->
                            it.theoryList.find { it.id == id }
                                    ?: throw NullPointerException("theory $id not found")
                        }
                    }
                    return@map list
                }
                .addSchedulers()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .compose<List<ListItem>>(lifecycleHandler.load(R.id.tasks_loader))
                .subscribe({
                    view.showTheoryList(it)
                }, {
                    it.printStackTrace()
                    view.showError(it.localizedMessage)
                })
    }
}