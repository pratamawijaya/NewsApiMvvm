package com.pratamawijaya.newsapimvvm.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.db.NewsAppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideDb(app: Application): NewsAppDb {
        return Room.databaseBuilder(app, NewsAppDb::class.java, "newsapi.db").build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: NewsAppDb): ArticleDao {
        return db.articleDao()
    }
}