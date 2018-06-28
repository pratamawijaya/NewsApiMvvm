package com.pratamawijaya.newsapimvvm.data.mapper

import com.pratamawijaya.newsapimvvm.data.model.ArticleModel
import com.pratamawijaya.newsapimvvm.data.model.SourceModel
import com.pratamawijaya.newsapimvvm.domain.Article
import com.pratamawijaya.newsapimvvm.domain.Source
import javax.inject.Inject

open class ArticleMapper @Inject constructor() : EntityMapper<ArticleModel, Article> {

    override fun mapFromEntity(entity: ArticleModel): Article {
        return Article(
                author = entity.author ?: "",
                publishedAt = entity.publishedAt,
                url = entity.url,
                title = entity.title,
                urlToImage = entity.urlToImage ?: "",
                description = entity.description ?: "",
                source = Source(id = entity.source.id ?: "",
                        name = entity.source.name)
        )
    }

    override fun mapToEntity(domain: Article): ArticleModel {
        return ArticleModel(
                source = SourceModel(id = domain.source.id,
                        name = domain.source.name),
                description = domain.description,
                urlToImage = domain.urlToImage,
                title = domain.title,
                url = domain.url,
                publishedAt = domain.publishedAt,
                author = domain.author
        )
    }
}