package com.pratamawijaya.newsapimvvm.data.mapper

import com.pratamawijaya.newsapimvvm.EntityFactory
import com.pratamawijaya.newsapimvvm.data.model.ArticleModel
import com.pratamawijaya.newsapimvvm.domain.Article
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleMapperTest {

    private val mapper = ArticleMapper()

    @Test
    fun `test mapper from entity`() {
        val entity = EntityFactory.makeArticleEntity()
        val article = mapper.mapFromEntity(entity)
        assertEqualData(entity, article)
    }

    @Test
    fun `test map to entity`() {
        val article = EntityFactory.makeArticle()
        val entity = mapper.mapToEntity(article)
        assertEqualData(entity, article)
    }

    private fun assertEqualData(entity: ArticleModel, article: Article) {
        assertEquals(entity.title, article.title)
    }
}