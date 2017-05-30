package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName
import ru.kpfu.chirkov.learnprogramming.base.ListItem

/**
 * @author ilya
 */
data class TheoryResponse(@SerializedName("id") val id: Long,
                          @SerializedName("title") override val title: String,
                          @SerializedName("description") val description: String) : ListItem