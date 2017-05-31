package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author ilya
 */
data class HackerOneResult(@SerializedName("stdout") val stdout: List<String>?,
                           @SerializedName("result") val result: Int,
                           @SerializedName("compilemessage") val compileMessage: String)