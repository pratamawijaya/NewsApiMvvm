package com.pratamawijaya.newsapimvvm

import com.pratamawijaya.newsapimvvm.data.model.ArticleModel
import com.pratamawijaya.newsapimvvm.data.model.SourceModel
import com.pratamawijaya.newsapimvvm.domain.Article
import com.pratamawijaya.newsapimvvm.domain.Source

object EntityFactory {
    fun makeArticleEntity(): ArticleModel {
        return ArticleModel(
                source = SourceModel(DataFactory.randomString(), DataFactory.randomString()),
                author = DataFactory.randomString(),
                publishedAt = DataFactory.randomString(),
                url = DataFactory.randomString(),
                title = DataFactory.randomString(),
                description = DataFactory.randomString(),
                urlToImage = DataFactory.randomString()
        )
    }

    fun makeArticle(): Article {
        return Article(
                source = Source(DataFactory.randomString(), DataFactory.randomString()),
                urlToImage = DataFactory.randomString(),
                description = DataFactory.randomString(),
                title = DataFactory.randomString(),
                url = DataFactory.randomString(),
                publishedAt = DataFactory.randomString(),
                author = DataFactory.randomString()
        )
    }
}