package ru.kpfu.chirkov.learnprogramming.data.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.kpfu.chirkov.learnprogramming.BuildConfig
import ru.kpfu.chirkov.learnprogramming.data.model.HackerOneResponse
import rx.Observable

/**
 * @author ilya
 */

interface HackerOneService {
    @FormUrlEncoded
    @POST("checker/submission.json")
    fun check(@Field("source") source: String, @Field("lang") lang: Int,
              @Field("testcases") testCases: String,
              @Field("api_key") apiKey: String = BuildConfig.HACKERONE_API_KEY,
              @Field("format") format: String = "json", @Field("wait") wait: String = "true")
            : Observable<HackerOneResponse>
}
