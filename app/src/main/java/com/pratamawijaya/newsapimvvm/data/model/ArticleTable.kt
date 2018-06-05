package com.pratamawijaya.newsapimvvm.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "ARTICLE"
)
data class ArticleTable(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,
        val title: String,
        val author: String,
        val sourceId: String,
        val sourceName: String,
        val url: String,
        val image: String,
        val publishedAt: String
)
