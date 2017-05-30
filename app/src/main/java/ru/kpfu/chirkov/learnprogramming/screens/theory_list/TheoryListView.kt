package ru.kpfu.chirkov.learnprogramming.screens.theory_list

import ru.kpfu.chirkov.learnprogramming.base.BaseView
import ru.kpfu.chirkov.learnprogramming.base.ListItem

/**
 * @author ilya
 */
interface TheoryListView : BaseView {
    fun showTheoryList(list: List<ListItem>)
}