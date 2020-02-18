package com.pratamawijaya.newsapimvvm.data.repository

import com.github.ajalt.timberkt.d
import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.db.StringKeyValueDao
import com.pratamawijaya.newsapimvvm.data.db.mapper.ArticleTableMapper
import com.pratamawijaya.newsapimvvm.data.mapper.ArticleMapper
import com.pratamawijaya.newsapimvvm.domain.Article
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//https://proandroiddev.com/using-coroutines-and-flow-with-mvvm-architecture-796142dbfc2f

class NewsRepositoryImpl constructor(private val service: NewsApiServices,
                                     private val articleDao: ArticleDao,
                                     private val stringKeyValueDao: StringKeyValueDao,
                                     private val mapper: ArticleMapper,
                                     private val tableMapper: ArticleTableMapper
) : NewsRepository {

    private val oneMinute = 60000L
    private val topHeadlineCacheThresholdMillis = 1 * oneMinute //


    override fun getEverything(query: String): Single<List<Article>> {
        return service.getEverything(query, "publishedAt")
                .toObservable()
                .flatMapIterable { it.articles }
                .map { mapper.mapToDomain(it) }
                .doOnNext { saveToLocal(it) }
                .toList()
    }

    /**
     * save to local
     */
    private fun saveToLocal(article: Article?) {
        if (article != null) {
            d { "save to local $article" }
            val articleTable = tableMapper.mapToDB(article)
            articleDao.insert(articleTable)
        }
    }

    override suspend fun getTopHeadlines(country: String, category: String): List<Article> {
        val result = service.getTopHeadlines(country, category)
        return mapper.mapToListDomain(result.articles)
    }
}