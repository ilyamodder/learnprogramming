package ru.kpfu.chirkov.learnprogramming.data.repository

import com.orhanobut.hawk.Hawk
import ru.kpfu.chirkov.learnprogramming.data.api.ApiFactory
import ru.kpfu.chirkov.learnprogramming.data.model.TaskResponse
import ru.kpfu.chirkov.learnprogramming.data.model.TasksResponse
import ru.kpfu.chirkov.learnprogramming.data.model.TheoryListResponse
import rx.Observable

/**
 * @author ilya
 */
class LearnProgrammingRepositoryImpl : LearnProgrammingRepository {
    override fun getTask(id: Long): Observable<TaskResponse> {
        return ApiFactory.getLearnProgrammingService().getTasks()
                .map { it.tasks }
                .map { it[0] }
    }

    override fun getTheory(): Observable<TheoryListResponse> {
        return ApiFactory.getLearnProgrammingService().getTheory();
    }

    override fun getShowStartScreen(): Observable<Boolean> {
        return Observable.fromCallable {
            Hawk.get<Boolean>("showed_start_screen", false)
        }.map {
            Hawk.put("showed_start_screen", true)
            it
        }
    }

    override fun getTasks(): Observable<TasksResponse> {
        return ApiFactory.getLearnProgrammingService().getTasks()
    }
}