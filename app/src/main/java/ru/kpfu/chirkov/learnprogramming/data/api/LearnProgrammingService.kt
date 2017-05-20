package ru.kpfu.chirkov.learnprogramming.data.api

import retrofit2.http.GET
import ru.kpfu.chirkov.learnprogramming.data.model.TasksResponse
import rx.Observable

/**
 * @author ilya
 */

interface LearnProgrammingService {
    @GET("tasks")
    fun getPosts(): Observable<TasksResponse>
}
