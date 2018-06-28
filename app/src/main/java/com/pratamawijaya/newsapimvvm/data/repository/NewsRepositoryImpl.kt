package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.mapper.ArticleMapper
import com.pratamawijaya.newsapimvvm.domain.Article
import io.reactivex.Single
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val service: NewsApiServices,
                                             private val articleDao: ArticleDao,
                                             private val mapper: ArticleMapper) : NewsRepository {

    override fun getEverything(query: String): Single<List<Article>> {
        return service.getEverything(query, "publishedAt")
                .toObservable()
                .flatMapIterable { it.articles }
                .map { mapper.mapFromEntity(it) }
                .toList()
    }

    override fun getTopHeadlines(): Single<List<Article>> {
        return service.getTopHeadlines(country = "us", category = "technology")
                .toObservable()
                .flatMapIterable { it.articles }
                .map { mapper.mapFromEntity(it) }
                .toList()
    }
}