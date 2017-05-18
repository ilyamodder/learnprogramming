package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author ilya
 */
data class CategoryResponse(@SerializedName("id") val id: Long,
                            @SerializedName("name") val name: String)