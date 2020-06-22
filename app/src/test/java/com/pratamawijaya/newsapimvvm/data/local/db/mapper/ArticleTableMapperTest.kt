package com.pratamawijaya.newsapimvvm.data.local.db.mapper

import com.pratamawijaya.newsapimvvm.EntityFactory
import com.pratamawijaya.newsapimvvm.TableFactory
import com.pratamawijaya.newsapimvvm.data.model.ArticleTable
import com.pratamawijaya.newsapimvvm.domain.Article
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ArticleTableMapperTest {

    private val mapper = ArticleTableMapper()

    @Test
    fun `test map from db`() {
        val articleTable = TableFactory.makeArticleTable()
        val article = mapper.mapFromDB(articleTable)
        assertEqualData(article, articleTable)
    }

    @Test
    fun `test map to db`() {
        val article = EntityFactory.makeArticle()
        val articleTable = mapper.mapToDB(article)
        assertEqualData(article, articleTable)
    }

    private fun assertEqualData(article: Article, articleTable: ArticleTable) {
        assertEquals(article.url, articleTable.url)
    }
}