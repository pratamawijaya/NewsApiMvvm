package com.pratamawijaya.newsapimvvm.data.mapper

interface EntityMapper<E, D> {
    fun mapToEntity(domain: D): E
    fun mapToDomain(entity: E): D
    fun mapToListEntity(domains: List<D>): List<E>
    fun mapToListDomain(entities: List<E>): List<D>
}