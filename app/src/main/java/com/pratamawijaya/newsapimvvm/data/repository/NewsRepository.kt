package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.entity.Article
import io.reactivex.Observable

interface NewsRepository {
    fun getTopHeadlines(): Observable<List<Article>>
}