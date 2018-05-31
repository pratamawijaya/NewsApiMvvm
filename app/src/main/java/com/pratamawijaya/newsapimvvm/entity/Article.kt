package com.pratamawijaya.newsapimvvm.entity

data class Article(
        val source: Source,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String

)

data class Source(
        val id: String,
        val name: String
)