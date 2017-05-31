package ru.kpfu.chirkov.learnprogramming.screens.task

import ru.arturvasilov.rxloader.LifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.addSchedulers
import ru.kpfu.chirkov.learnprogramming.data.api.ApiFactory
import ru.kpfu.chirkov.learnprogramming.data.model.HackerOneResponse
import ru.kpfu.chirkov.learnprogramming.data.model.TaskResponse
import ru.kpfu.chirkov.learnprogramming.data.repository.LearnProgrammingRepositoryProvider
import ru.kpfu.chirkov.learnprogramming.toLangId
import java.io.IOException

/**
 * @author ilya
 */
class TaskPresenter(val id: Long, val view: TaskView, val lifecycleHandler: LifecycleHandler) {
    private lateinit var task: TaskResponse

    fun init() {
        LearnProgrammingRepositoryProvider.learnProgrammingRepository
                .getTask(id)
                .addSchedulers()
                .doOnSubscribe { view.showLoading() }
                .doOnTerminate { view.hideLoading() }
                .compose<TaskResponse>(lifecycleHandler.load(R.id.task_loader))
                .subscribe({
                    task = it
                    view.showTask(it)
                }, {
                    view.showError(it.localizedMessage)
                })
    }

    fun codeSubmitted(code: String) {
        ApiFactory.getHackerOneService()
                .check(code, task.language.toLangId(), "[\"${task.result}\"]")
                .addSchedulers()
                .doOnSubscribe { view.showProgressDialog() }
                .doOnTerminate { view.hideProgressDialog() }
                .map(HackerOneResponse::result)
                .map {
                    if (it.result != 0) {
                        throw IOException("Синтаксическая ошибка: ${it.compileMessage}")
                    }
                    if (it?.stdout?.get(0) != task.result) {
                        throw IOException("Неправильный вывод, должно быть \"${task.result}\", " +
                                "но ваш код выводит \"${it?.stdout?.get(0)}\"")
                    }
                }
                .subscribe({
                    view.showSuccess()
                }, {
                    it.printStackTrace()
                    view.showErrorMessage(it.localizedMessage)
                    view.hideProgressDialog()
                })
    }
}