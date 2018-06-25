package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.entity.Article
import io.reactivex.Single

interface NewsRepository {
    fun getTopHeadlines(forced: Boolean): Single<List<Article>>
}