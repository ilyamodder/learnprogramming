package ru.kpfu.chirkov.learnprogramming.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.data.model.CategoryResponse

/**
 * @author ilya
 */
class CategoriesListAdapter<in T>(val list: List<ListItem>, val clickListener: (T) -> Unit)
    : RecyclerView.Adapter<CategoriesListAdapter.ItemViewHolder>() {

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.bind(list[position])
        if (getItemViewType(position) != 1) {
            holder?.itemView?.setOnClickListener { clickListener.invoke(list[position] as T) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val layoutId = when (viewType) {
            0 -> R.layout.main_list_item
            1 -> R.layout.item_category
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
        }
        return ItemViewHolder(LayoutInflater.from(parent!!.context).inflate(layoutId, parent, false));
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is CategoryResponse -> 1
            else -> 0
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val text: TextView = itemView.findViewById(R.id.text) as TextView

        fun bind(item: ListItem) {
            text.text = item.title
        }
    }
}