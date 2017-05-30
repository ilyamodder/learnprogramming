package ru.kpfu.chirkov.learnprogramming.screens.dashboard

import ru.kpfu.chirkov.learnprogramming.base.BaseFragment

/**
 * @author ilya
 */
class DashboardFragment : BaseFragment() {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getLayoutId(): Int {
        return android.R.layout.simple_list_item_1
    }
}