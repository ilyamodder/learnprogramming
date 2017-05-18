package ru.kpfu.chirkov.learnprogramming.screens.tasks

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.fragment_task.*
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.base.BaseFragment

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

    override fun showError(error: String) {
        Toast.makeText(activity, error, LENGTH_SHORT).show()
    }

    override fun showTasks(list: List<Any>) {
        recyclerView.adapter = TasksAdapter(list)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        presenter = TasksPresenter(this, LoaderLifecycleHandler.create(activity, loaderManager))
        presenter.init()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_task
    }
}