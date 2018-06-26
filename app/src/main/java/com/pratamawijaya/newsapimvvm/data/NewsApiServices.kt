package com.pratamawijaya.newsapimvvm.data

import com.pratamawijaya.newsapimvvm.data.response.TopHeadlineResponse
import com.pratamawijaya.newsapimvvm.entity.Article
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {

    /**
     * return 20 top news headline
     */
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String,
                        @Query("category") category: String
    ): Observable<TopHeadlineResponse>

    @GET("everything")
    fun getEverything(@Query("q") query: String, @Query("sortBy") sortBy: String): Observable<TopHeadlineResponse>

}