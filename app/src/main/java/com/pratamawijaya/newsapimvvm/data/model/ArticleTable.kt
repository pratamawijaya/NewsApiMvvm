package com.pratamawijaya.newsapimvvm.data.model

import android.arch.persistence.room.Entity

@Entity(
        tableName = "ARTICLE",
        primaryKeys = ["url"]
)
data class ArticleTable(
        val title: String,
        val author: String,
        val sourceId: String,
        val sourceName: String,
        val url: String,
        val image: String,
        val publishedAt: String
)
