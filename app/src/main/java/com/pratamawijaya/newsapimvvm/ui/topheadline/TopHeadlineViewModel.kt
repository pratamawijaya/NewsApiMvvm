package com.pratamawijaya.newsapimvvm.ui.topheadline

import android.arch.lifecycle.*
import com.github.ajalt.timberkt.d
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepository
import com.pratamawijaya.newsapimvvm.domain.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val topHeadlineState = MutableLiveData<TopHeadlineState>()

    init {
        topHeadlineState.value = LoadingState(emptyList())
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
        topHeadlineState.value = LoadingState(emptyList())

        compositeDisposable.add(repo.getEverything(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSearchReceived, this::onError))
    }

    private fun onSearchReceived(articles: List<Article>) {
        topHeadlineState.value = DefaultState(articles)
    }

    private fun onArticleReceived(articles: List<Article>) {
        val currentArticles = obtainCurrentData().toMutableList()
        currentArticles.addAll(articles)
        topHeadlineState.value = DefaultState(currentArticles)
    }

    private fun onError(error: Throwable) {
        topHeadlineState.value = ErrorState(error.localizedMessage, obtainCurrentData())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun disposeViewModel() {
        compositeDisposable.dispose()
    }

    private fun obtainCurrentData() = topHeadlineState.value?.data ?: emptyList()

    fun restoreTopHeadlines() {
        topHeadlineState.value = DefaultState(obtainCurrentData())
    }


}
