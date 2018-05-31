package com.pratamawijaya.newsapimvvm.ui.topheadline

import com.pratamawijaya.newsapimvvm.entity.Article

sealed class TopHeadlineState {
    abstract val data: List<Article>
}

data class DefaultState(override val data: List<Article>) : TopHeadlineState()
data class LoadingState(override val data: List<Article>) : TopHeadlineState()
data class ErrorState(val errorMessage: String, override val data: List<Article>) : TopHeadlineState()
