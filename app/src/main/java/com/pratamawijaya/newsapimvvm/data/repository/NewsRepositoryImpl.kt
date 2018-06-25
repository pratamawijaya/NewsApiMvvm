package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.data.ConnectionHelper
import com.pratamawijaya.newsapimvvm.data.Constants
import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.PreferencesHelper
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.model.ArticleModel
import com.pratamawijaya.newsapimvvm.data.model.ArticleTable
import com.pratamawijaya.newsapimvvm.data.model.SourceModel
import com.pratamawijaya.newsapimvvm.entity.Article
import com.pratamawijaya.newsapimvvm.entity.Source
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class NewsRepositoryImpl constructor(private val service: NewsApiServices,
                                     private val articleDao: ArticleDao,
                                     private val prefHelper: PreferencesHelper,
                                     private val connectionHelper: ConnectionHelper) : NewsRepository {

    override fun getTopHeadlines(forced: Boolean): Single<List<Article>> {
        return if (shouldUpdate(forced)) {
            loadTopHeadlineFromNetwork()
        } else {
            loadTopHeadlineOffline()
        }
    }

    /**
     * get top headline from local storage
     */
    private fun loadTopHeadlineOffline(): Single<List<Article>> {
        return articleDao.getArticles()
                .toObservable()
                .flatMapIterable { it }
                .map { articleDomainMapper(it) }
                .toList()
    }

    /**
     * get top headline from network n save to local
     */
    private fun loadTopHeadlineFromNetwork(): Single<List<Article>> {
        return service.getTopHeadlines(country = "us", category = "technology")
                .toObservable()
                .flatMapIterable { it.articles }
                .flatMap { saveToLocal(it) }
                .map { articleMapper(it) }
                .toList()
    }

    /**
     * save article to local storage
     */
    private fun saveToLocal(it: ArticleModel): Observable<ArticleModel> {
        val articleTable = articleTableMapper(it)
        articleDao.insert(articleTable)

        val currentTime = Calendar.getInstance().timeInMillis
        prefHelper.saveLong(PreferencesHelper.LAST_UPDATE_KEY, currentTime)

        return Observable.just(it)
    }

    /**
     * check should update or not
     */
    private fun shouldUpdate(forced: Boolean): Boolean = when {
        forced -> true
        !connectionHelper.isOnline() -> false
        else -> {
            val lastUpdate = prefHelper.getLong(PreferencesHelper.LAST_UPDATE_KEY)
            val currentTime = Calendar.getInstance().timeInMillis

            lastUpdate + Constants.REFRESH_LIMIT < currentTime
        }
    }

    private fun sourceMapper(sourceModel: SourceModel) = Source(
            id = sourceModel.id ?: "",
            name = sourceModel.name
    )

    private fun articleMapper(it: ArticleModel) = Article(
            title = it.title,
            url = it.url,
            publishedAt = it.publishedAt,
            author = it.author ?: "",
            description = it.description ?: "",
            source = sourceMapper(it.source),
            urlToImage = it.urlToImage ?: "")

    private fun articleTableMapper(it: ArticleModel) = ArticleTable(
            title = it.title,
            image = it.urlToImage ?: "",
            sourceId = it.source.id ?: "",
            sourceName = it.source.name ?: "",
            url = it.url, publishedAt = it.publishedAt, author = it.author
            ?: "", uid = 0
    )

    private fun articleDomainMapper(it: ArticleTable) = Article(
            title = it.title,
            url = it.url,
            publishedAt = it.publishedAt,
            author = it.author ?: "",
            description = "",
            source = Source(it.sourceId, it.sourceName),
            urlToImage = it.image
    )
}