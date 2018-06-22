package com.pratamawijaya.newsapimvvm.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.pratamawijaya.newsapimvvm.data.model.ArticleTable
import io.reactivex.Single

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: ArticleTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(article: List<ArticleTable>)

    @Query("SELECT * FROM ARTICLE")
    fun getArticle(): Single<List<ArticleTable>>
}