package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author ilya
 */
data class TasksResponse(@SerializedName("categories") val categories: List<CategoryResponse>,
                         @SerializedName("tasks") val tasks: List<TaskResponse>,
                         @SerializedName("mapping_category_to_tasks") val mapping: Map<Long, List<Long>>)