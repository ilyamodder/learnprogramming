package ru.kpfu.chirkov.learnprogramming.data.repository

import ru.kpfu.chirkov.learnprogramming.data.model.TaskResponse
import ru.kpfu.chirkov.learnprogramming.data.model.TasksResponse
import ru.kpfu.chirkov.learnprogramming.data.model.TheoryListResponse
import rx.Observable

/**
 * @author ilya
 */
interface LearnProgrammingRepository {
    fun getTasks(): Observable<TasksResponse>

    fun getTheory(): Observable<TheoryListResponse>

    fun getShowStartScreen(): Observable<Boolean>

    fun getTask(id: Long): Observable<TaskResponse>
}