package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.db.NewsAppDb
import com.pratamawijaya.newsapimvvm.data.model.ArticleModel
import com.pratamawijaya.newsapimvvm.data.model.SourceModel
import com.pratamawijaya.newsapimvvm.entity.Article
import com.pratamawijaya.newsapimvvm.entity.Source
import io.reactivex.Observable

class NewsRepositoryImpl constructor(private val service: NewsApiServices,
                                     private val newsDb: NewsAppDb) : NewsRepository {

//    val fetcher = Fetcher<List<ArticleModel>, String> { anu -> service.getEverything() }

//    val persister = object {
//        override fun read(key: String): Observable<List<ArticleTable>> {
//            return newsDb.articleDao().getArticle().toObservable()
//        }
//
//        override fun write(key: String, raw: ArticleTable) {
//            newsDb.articleDao().insert(raw)
//        }
//
//    }

    override fun getTopHeadlines(): Observable<List<Article>> {
        return service.getTopHeadlines(country = "us", category = "technology")
                .flatMap { Observable.fromIterable(it.articles) }
                .map { articleMapper(it) }
                .toList().toObservable()
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
}