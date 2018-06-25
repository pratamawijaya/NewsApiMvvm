package com.pratamawijaya.newsapimvvm.di.module

import android.content.Context
import com.pratamawijaya.newsapimvvm.data.ConnectionHelper
import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.PreferencesHelper
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.db.NewsAppDb
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepository
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(newsServices: NewsApiServices,
                        articleDao: ArticleDao,
                        preferencesHelper: PreferencesHelper,
                        connectionHelper: ConnectionHelper): NewsRepository = NewsRepositoryImpl(newsServices, articleDao, preferencesHelper, connectionHelper)
}