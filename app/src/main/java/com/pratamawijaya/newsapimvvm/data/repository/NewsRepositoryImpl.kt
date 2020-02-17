package com.pratamawijaya.newsapimvvm.data.repository

import com.github.ajalt.timberkt.d
import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.db.StringKeyValueDao
import com.pratamawijaya.newsapimvvm.data.db.mapper.ArticleTableMapper
import com.pratamawijaya.newsapimvvm.data.mapper.ArticleMapper
import com.pratamawijaya.newsapimvvm.domain.Article
import com.pratamawijaya.newsapimvvm.utils.Utils
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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
                .map { mapper.mapFromEntity(it) }
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

    override suspend fun getTopHeadlines(): Flow<List<Article>> = flow {
        stringKeyValueDao.get("top_headlines")
                ?.takeIf {
                    !Utils.shouldCallApi(it.value, topHeadlineCacheThresholdMillis)
                }
                ?.let { emit(getDataOrError(Exception())) }

        //        val result = service.getTopHeadlines(country = "us",
//                category = "technology").asFlow()

//        return service.getTopHeadlines(country = "us",
//                category = "technology")

//        return service.getTopHeadlines(country = "us", category = "technology")
//                .toObservable()
//                .flatMapIterable { it.articles }
//                .map { mapper.mapFromEntity(it) }
//                .doOnNext { saveToLocal(it) }
//                .toList()
    }

    private fun getDataOrError(throwable: Throwable): List<Article> = articleDao.getArticle()
}