package com.pratamawijaya.newsapimvvm

import com.pratamawijaya.newsapimvvm.data.model.ArticleTable

object TableFactory {

    fun makeArticleTable(): ArticleTable {
        return ArticleTable(
                DataFactory.randomInt(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString()
        )
    }


}