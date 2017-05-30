package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName
import ru.kpfu.chirkov.learnprogramming.base.ListItem

/**
 * @author ilya
 */
data class TaskResponse(@SerializedName("id") val id: Long,
                        @SerializedName("title") override val title: String,
                        @SerializedName("description") val description: String,
                        @SerializedName("initial-code") val initialCode: String,
                        @SerializedName("language") val language: String,
                        @SerializedName("result") val result: String) : ListItem