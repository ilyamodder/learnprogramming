package ru.kpfu.chirkov.learnprogramming.screens.tasks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_category.view.*
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.data.model.CategoryResponse
import ru.kpfu.chirkov.learnprogramming.data.model.TaskResponse

/**
 * @author ilya
 */
class TasksAdapter(val list: List<Any>) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bindTask(list.get(position) as TaskResponse)
            is DividerViewHolder -> holder.bindCategory(list.get(position) as CategoryResponse)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> ItemViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.main_list_item, parent, false))
            1 -> DividerViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_category, parent, false))
            else -> throw IllegalArgumentException("Wrong viewType: ${viewType}")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list.get(position)) {
            is TaskResponse -> 0
            is CategoryResponse -> 1
            else -> throw IllegalArgumentException("Wrong item at ${position}")
        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ItemViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindTask(task: TaskResponse) {
            itemView.text.text = task.title
        }
    }

    class DividerViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindCategory(category: CategoryResponse) {
            itemView.text.text = category.name
        }
    }
}