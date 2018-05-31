package com.pratamawijaya.newsapimvvm.di.module

import android.content.Context
import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepository
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(newsServices: NewsApiServices): NewsRepository = NewsRepositoryImpl(newsServices)
}