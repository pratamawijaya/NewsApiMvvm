package com.pratamawijaya.newsapimvvm.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.pratamawijaya.newsapimvvm.data.remote.NewsApiServices
import com.pratamawijaya.newsapimvvm.data.local.db.ArticleDao
import com.pratamawijaya.newsapimvvm.data.local.db.mapper.ArticleTableMapper
import com.pratamawijaya.newsapimvvm.data.mapper.ArticleMapper
import com.pratamawijaya.newsapimvvm.data.model.ArticleModel
import com.pratamawijaya.newsapimvvm.data.model.SourceModel
import com.pratamawijaya.newsapimvvm.data.remote.response.TopHeadlineResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NewsRepositoryImplTest {


    @Mock
    lateinit var service: NewsApiServices
    @Mock
    lateinit var articleDao: ArticleDao
    @Mock
    lateinit var mapperEntity: ArticleMapper
    @Mock
    lateinit var tableMapper: ArticleTableMapper

    private lateinit var newsRepositoryImpl: NewsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        newsRepositoryImpl = NewsRepositoryImpl(service, articleDao, mapperEntity, tableMapper)
    }

    @Test
    fun `test get top headlines return single observable`() {
        Single.just(TopHeadlineResponse(
                status = "oke",
                totalResults = 1,
                articles = listOf(
                        ArticleModel(
                                source = SourceModel("", ""),
                                author = "author",
                                publishedAt = "publish",
                                url = "url",
                                title = "title",
                                description = "desc",
                                urlToImage = "url"
                        )
                )
        )).toObservable()
                .flatMapIterable { it.articles }
                .map { mapperEntity.mapFromEntity(it) }
                .test()
                .assertError { error -> "The mapper function returned a null value." == error.message }

//        stubGetTopHeadlines(Single.just(TopHeadlineResponse(
//                status = "oke",
//                totalResults = 1,
//                articles = listOf(
//                        ArticleModel(
//                                source = SourceModel("", ""),
//                                author = "author",
//                                publishedAt = "publish",
//                                url = "url",
//                                title = "title",
//                                description = "desc",
//                                urlToImage = "url"
//                        )
//                )
//        )))
//        val testObserver = newsRepositoryImpl.getTopHeadlines().test()
//        testObserver.assertComplete()
    }

    private fun stubGetTopHeadlines(data: Single<TopHeadlineResponse>) {
        whenever(service.getTopHeadlines(country = any(), category = any()))
                .thenReturn(data)
    }
}