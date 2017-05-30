package ru.kpfu.chirkov.learnprogramming.screens.tasks

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.*
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.base.BaseFragment
import ru.kpfu.chirkov.learnprogramming.base.CategoriesListAdapter
import ru.kpfu.chirkov.learnprogramming.base.ListItem

/**
 * @author ilya
 */
class TasksFragment : BaseFragment(), TasksView {

    private lateinit var presenter: TasksPresenter

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showTasks(list: List<ListItem>) {
        recyclerView.adapter = CategoriesListAdapter(list)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        presenter = TasksPresenter(this, LoaderLifecycleHandler.create(activity, loaderManager))
        if (savedInstanceState == null) {
            presenter.init()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list
    }
}