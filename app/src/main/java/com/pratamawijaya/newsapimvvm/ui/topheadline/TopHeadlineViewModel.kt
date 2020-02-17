package com.pratamawijaya.newsapimvvm.ui.topheadline

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.d
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepository
import com.pratamawijaya.newsapimvvm.domain.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

sealed class TopHeadlineState {
    object EmptyState : TopHeadlineState()
    data class ArticleLoadedState(val articles: List<Article>) : TopHeadlineState()
    object LoadingState : TopHeadlineState()
    data class ErrorState(val errorMessage: String) : TopHeadlineState()
}

class TopHeadlineViewModel constructor(private val repo: NewsRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val topHeadlineState = MutableLiveData<TopHeadlineState>()
    var currentArticle: MutableList<Article> = mutableListOf()

    init {
        topHeadlineState.value = TopHeadlineState.EmptyState
    }

    fun updateTopHeadlines() {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {
        compositeDisposable.add(repo.getTopHeadlines()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onArticleReceived, this::onError))
    }

    /**
     * search article
     */
    fun searchArticle(query: String) {
        d { "search $query" }
        // set state to loading
        topHeadlineState.value = TopHeadlineState.LoadingState

        compositeDisposable.add(repo.getEverything(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSearchReceived, this::onError))
    }

    private fun onSearchReceived(articles: List<Article>) {
        topHeadlineState.value = TopHeadlineState.ArticleLoadedState(articles)
    }

    private fun onArticleReceived(articles: List<Article>) {
        val currentArticles = obtainCurrentData()
        currentArticles.addAll(articles)
        topHeadlineState.value = TopHeadlineState.ArticleLoadedState(currentArticles)
    }

    private fun onError(error: Throwable) {
        topHeadlineState.value = TopHeadlineState.ErrorState(error.localizedMessage)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun disposeViewModel() {
        compositeDisposable.dispose()
    }

    private fun obtainCurrentData() = currentArticle

    fun restoreTopHeadlines() {
        topHeadlineState.value = TopHeadlineState.ArticleLoadedState(obtainCurrentData())
    }


}
