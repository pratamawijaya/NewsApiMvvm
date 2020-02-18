package com.pratamawijaya.newsapimvvm.data.mapper

import com.pratamawijaya.newsapimvvm.data.model.ArticleModel
import com.pratamawijaya.newsapimvvm.data.model.SourceModel
import com.pratamawijaya.newsapimvvm.domain.Article
import com.pratamawijaya.newsapimvvm.domain.Source

open class ArticleMapper : EntityMapper<ArticleModel, Article> {

    override fun mapToDomain(entity: ArticleModel): Article {
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

    override fun mapToListEntity(domains: List<Article>): List<ArticleModel> {
        val listEntity = mutableListOf<ArticleModel>()
        domains.map {
            val articleEntity = mapToEntity(it)
            listEntity.add(articleEntity)
        }
        return listEntity
    }

    override fun mapToListDomain(entities: List<ArticleModel>): List<Article> {
        val listDomain = mutableListOf<Article>()
        entities.map {
            val articleDomain = mapToDomain(it)
            listDomain.add(articleDomain)
        }
        return listDomain
    }
}