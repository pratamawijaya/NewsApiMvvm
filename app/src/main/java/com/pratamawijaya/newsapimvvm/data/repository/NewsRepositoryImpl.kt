package com.pratamawijaya.newsapimvvm.data.repository

import com.github.ajalt.timberkt.d
import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.db.mapper.ArticleTableMapper
import com.pratamawijaya.newsapimvvm.data.mapper.ArticleMapper
import com.pratamawijaya.newsapimvvm.domain.Article
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val service: NewsApiServices,
                                             private val articleDao: ArticleDao,
                                             private val mapper: ArticleMapper,
                                             private val tableMapper: ArticleTableMapper
) : NewsRepository {

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

    override fun getTopHeadlines(): Single<List<Article>> {
        return service.getTopHeadlines(country = "us", category = "technology")
                .toObservable()
                .flatMapIterable { it.articles }
                .map { mapper.mapFromEntity(it) }
                .doOnNext { saveToLocal(it) }
                .toList()
    }
}