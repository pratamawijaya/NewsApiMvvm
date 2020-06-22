package com.pratamawijaya.newsapimvvm.data.local.db.mapper

interface DbMapper<T, D> {
    fun mapFromDB(table: T): D
    fun mapToDB(domain: D): T
}