package ru.kpfu.chirkov.learnprogramming.data.repository

/**
 * @author ilya
 */
class LearnProgrammingRepositoryProvider {
    companion object {
        val learnProgrammingRepository: LearnProgrammingRepository by lazy {
            LearnProgrammingRepositoryImpl()
        }
    }
}