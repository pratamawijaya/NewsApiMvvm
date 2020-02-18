package com.pratamawijaya.newsapimvvm.data.db.mapper

import com.pratamawijaya.newsapimvvm.data.model.ArticleTable
import com.pratamawijaya.newsapimvvm.domain.Article
import com.pratamawijaya.newsapimvvm.domain.Source

open class ArticleTableMapper : DbMapper<ArticleTable, Article> {
    override fun mapFromDB(table: ArticleTable): Article {
        return Article(
                source = Source(table.sourceId, table.sourceName),
                author = table.author,
                publishedAt = table.publishedAt,
                url = table.url,
                title = table.title,
                urlToImage = table.image,
                description = ""
        )
    }

    override fun mapToDB(domain: Article): ArticleTable {
        return ArticleTable(
                uid = 0,
                title = domain.title,
                url = domain.url,
                publishedAt = domain.publishedAt,
                author = domain.author,
                sourceName = domain.source.name,
                sourceId = domain.source.id,
                image = domain.urlToImage
        )
    }
}