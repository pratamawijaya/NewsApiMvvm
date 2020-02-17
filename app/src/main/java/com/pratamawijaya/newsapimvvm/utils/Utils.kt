package com.pratamawijaya.newsapimvvm.utils

object Utils {
    fun shouldCallApi(
            lastApiCallMillis: String,
            cacheThresholdInMillis: Long = 300000L //default value is 5 minutes//
    ): Boolean {
        return (System.currentTimeMillis() - lastApiCallMillis.toLong()) >= cacheThresholdInMillis
    }
}