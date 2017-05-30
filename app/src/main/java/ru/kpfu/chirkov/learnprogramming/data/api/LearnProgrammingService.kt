package ru.kpfu.chirkov.learnprogramming.data.api

import retrofit2.http.GET
import ru.kpfu.chirkov.learnprogramming.data.model.TasksResponse
import ru.kpfu.chirkov.learnprogramming.data.model.TheoryListResponse
import rx.Observable

/**
 * @author ilya
 */

interface LearnProgrammingService {
    @GET("tasks")
    fun getTasks(): Observable<TasksResponse>

    @GET("theory")
    fun getTheory(): Observable<TheoryListResponse>
}
