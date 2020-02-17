package com.pratamawijaya.newsapimvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pratamawijaya.newsapimvvm.data.model.ArticleTable
import com.pratamawijaya.newsapimvvm.data.model.StringKeyValuePairTable

@Database(
        entities = [
            ArticleTable::class,
            StringKeyValuePairTable::class
        ],
        version = 2,
        exportSchema = false
)
abstract class NewsAppDb : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun stringKeyValueDao(): StringKeyValueDao
}