package com.pratamawijaya.newsapimvvm.data.db.mapper

interface DbMapper<T, D> {
    fun mapFromDB(table: T): D
    fun mapToDB(domain: D): T
}