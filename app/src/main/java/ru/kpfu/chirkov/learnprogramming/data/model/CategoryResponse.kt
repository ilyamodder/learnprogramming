package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName
import ru.kpfu.chirkov.learnprogramming.base.ListItem

/**
 * @author ilya
 */
data class CategoryResponse(@SerializedName("id") val id: Long,
                            @SerializedName("name") val name: String) : ListItem {
    override val title
        get() = name
}