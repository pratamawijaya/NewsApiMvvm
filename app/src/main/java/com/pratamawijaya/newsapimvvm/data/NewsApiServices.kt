package com.pratamawijaya.newsapimvvm.data

import com.pratamawijaya.newsapimvvm.data.response.TopHeadlineResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {

    /**
     * return 20 top news headline
     */
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String,
                                @Query("category") category: String): TopHeadlineResponse

    @GET("everything")
    fun getEverything(@Query("q") query: String, @Query("sortBy") sortBy: String): Single<TopHeadlineResponse>

}