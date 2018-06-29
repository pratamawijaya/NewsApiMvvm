package com.pratamawijaya.newsapimvvm.di.module

import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.db.mapper.ArticleTableMapper
import com.pratamawijaya.newsapimvvm.data.mapper.ArticleMapper
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepository
import com.pratamawijaya.newsapimvvm.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideArticleMapper(): ArticleMapper = ArticleMapper()

    @Provides
    fun provideArticleTableMapper(): ArticleTableMapper = ArticleTableMapper()

    @Provides
    fun provideNewsRepo(newsServices: NewsApiServices,
                        articleDao: ArticleDao,
                        articleMapper: ArticleMapper,
                        articleTableMapper: ArticleTableMapper): NewsRepository = NewsRepositoryImpl(newsServices, articleDao, articleMapper, articleTableMapper)
}