package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author ilya
 */
data class TheoryListResponse(@SerializedName("categories") val categories: List<CategoryResponse>,
                              @SerializedName("theory") val theoryList: List<TheoryResponse>,
                              @SerializedName("mapping_category_to_theory") val mapping: Map<Long, List<Long>>)