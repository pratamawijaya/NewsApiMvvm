package com.pratamawijaya.newsapimvvm.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["key"])
data class StringKeyValuePairTable(
        val key: String,
        val value: String
)