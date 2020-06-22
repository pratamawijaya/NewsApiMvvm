package com.pratamawijaya.newsapimvvm.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pratamawijaya.newsapimvvm.data.model.ArticleTable

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: ArticleTable)

    @Query("SELECT * FROM ARTICLE")
    fun getArticle(): List<ArticleTable>
}