package com.pratamawijaya.newsapimvvm.data.db

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.pratamawijaya.newsapimvvm.data.model.ArticleTable
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    lateinit var articleDao: ArticleDao
    lateinit var database: NewsAppDb

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, NewsAppDb::class.java).build()
        articleDao = database.articleDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertArticle() {
        val articles = listOf(
                ArticleTable(uid = 1,
                        author = "CNN",
                        publishedAt = "",
                        url = "",
                        title = "Dunia Dalam Berita",
                        sourceName = "CNN",
                        sourceId = "c",
                        image = ""
                )
        )

        articleDao.insertAll(article = articles)

        val allArticles = articleDao.getArticle()
        assertEquals(articles, allArticles)

    }
}