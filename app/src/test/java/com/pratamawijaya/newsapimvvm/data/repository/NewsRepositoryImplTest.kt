package com.pratamawijaya.newsapimvvm.data.repository

import com.pratamawijaya.newsapimvvm.data.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.db.NewsAppDb
import com.pratamawijaya.newsapimvvm.entity.Article
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsRepositoryImplTest() {

    @Mock
    lateinit var mockNewsApiServices: NewsApiServices

    @Mock
    lateinit var mockNewsAppDb: NewsAppDb

    lateinit var newsRepositoryImpl: NewsRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
//        newsRepositoryImpl = NewsRepositoryImpl(mockNewsApiServices, mockNewsAppDb)
    }

    @Test
    fun testGetTopHeadline() {
        val listNewsModel = listOf<Article>()
        val testObserver = newsRepositoryImpl.getTopHeadlines(true).test()

        testObserver.assertNoErrors()
    }
}