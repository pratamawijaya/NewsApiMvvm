package com.pratamawijaya.newsapimvvm.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.pratamawijaya.newsapimvvm.data.model.ArticleTable

@Database(
        entities = [
            ArticleTable::class
        ],
        version = 1,
        exportSchema = false
)
abstract class NewsAppDb : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}