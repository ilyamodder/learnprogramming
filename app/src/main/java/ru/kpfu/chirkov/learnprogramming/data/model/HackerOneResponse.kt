package ru.kpfu.chirkov.learnprogramming.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author ilya
 */
data class HackerOneResponse(@SerializedName("result") val result: HackerOneResult) {
}