package com.pratamawijaya.newsapimvvm.data

import com.pratamawijaya.newsapimvvm.data.response.TopHeadlineResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {

    /**
     * return 20 top news headline
     */
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("category") category: String
    ): Single<TopHeadlineResponse>

    @GET("everything")
    fun getEverything()

}