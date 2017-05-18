package ru.kpfu.chirkov.learnprogramming.tasks

import ru.kpfu.chirkov.learnprogramming.base.BaseView

/**
 * @author ilya
 */
interface TasksView : BaseView {
    fun showTasks(list: List<Any>)
}