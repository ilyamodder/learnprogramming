package ru.kpfu.chirkov.learnprogramming.screens.task

import ru.kpfu.chirkov.learnprogramming.base.BaseView
import ru.kpfu.chirkov.learnprogramming.data.model.TaskResponse

/**
 * @author ilya
 */
interface TaskView : BaseView {
    fun showTask(task: TaskResponse)
    fun showProgressDialog()
    fun hideProgressDialog()
    fun showErrorMessage(error: String)
    fun showSuccess()
}