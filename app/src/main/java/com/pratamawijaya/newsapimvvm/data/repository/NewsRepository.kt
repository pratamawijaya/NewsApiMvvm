package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.domain.Article
import io.reactivex.Single

interface NewsRepository {
    fun getTopHeadlines(): Single<List<Article>>
    fun getEverything(query: String): Single<List<Article>>
}