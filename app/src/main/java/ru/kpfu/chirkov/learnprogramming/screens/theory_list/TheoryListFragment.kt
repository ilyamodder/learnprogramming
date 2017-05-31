package ru.kpfu.chirkov.learnprogramming.screens.theory_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.*
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.base.BaseFragment
import ru.kpfu.chirkov.learnprogramming.base.CategoriesListAdapter
import ru.kpfu.chirkov.learnprogramming.base.ListItem
import ru.kpfu.chirkov.learnprogramming.data.model.TheoryResponse

/**
 * @author ilya
 */
class TheoryListFragment : BaseFragment(), TheoryListView {

    private lateinit var presenter: TheoryListPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_list;
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        presenter = TheoryListPresenter(this, LoaderLifecycleHandler.create(activity, loaderManager))
        presenter.init();
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showTheoryList(list: List<ListItem>) {
        recyclerView.adapter = CategoriesListAdapter<TheoryResponse>(list, this::onItemClick)
    }

    fun onItemClick(theory: TheoryResponse) {

    }
}