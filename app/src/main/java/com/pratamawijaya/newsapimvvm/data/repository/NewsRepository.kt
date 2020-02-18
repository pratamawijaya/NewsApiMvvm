package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.domain.Article
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(country: String, category: String): List<Article>
    fun getEverything(query: String): Single<List<Article>>
}