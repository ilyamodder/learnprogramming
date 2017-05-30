package ru.kpfu.chirkov.learnprogramming.screens.tasks

import ru.kpfu.chirkov.learnprogramming.base.BaseView
import ru.kpfu.chirkov.learnprogramming.base.ListItem

/**
 * @author ilya
 */
interface TasksView : BaseView {
    fun showTasks(list: List<ListItem>)
}