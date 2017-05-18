package ru.kpfu.chirkov.learnprogramming.base

/**
 * @author ilya
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(error: String)
}