package com.pratamawijaya.newsapimvvm.data

import com.pratamawijaya.newsapimvvm.data.response.TopHeadlineResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsApiServices {

    /**
     * return 20 top news headline
     */
    @GET("/v2/top-headlines")
    fun getTopHeadlines(): Observable<TopHeadlineResponse>

    @GET("/v2/everything")
    fun getEverything()

}