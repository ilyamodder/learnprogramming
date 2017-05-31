package ru.kpfu.chirkov.learnprogramming.screens.tasks

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.startActivity
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.base.BaseFragment
import ru.kpfu.chirkov.learnprogramming.base.CategoriesListAdapter
import ru.kpfu.chirkov.learnprogramming.base.ListItem
import ru.kpfu.chirkov.learnprogramming.data.model.TaskResponse
import ru.kpfu.chirkov.learnprogramming.screens.task.TaskActivity

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
        recyclerView.adapter = CategoriesListAdapter<TaskResponse>(list, this::onItemClick)
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

    fun onItemClick(task: TaskResponse) {
        startActivity<TaskActivity>("id" to task.id)
    }
}