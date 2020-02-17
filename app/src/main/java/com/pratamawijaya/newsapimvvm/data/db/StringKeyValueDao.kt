package com.pratamawijaya.newsapimvvm.data.db

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pratamawijaya.newsapimvvm.data.model.StringKeyValuePairTable

@Dao
interface StringKeyValueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keyValueData: StringKeyValuePairTable)

    @Query("SELECT * FROM StringKeyValuePairTable WHERE `key` = :key LIMIT 1")
    suspend fun get(@NonNull key: String): StringKeyValuePairTable?

    @Query("DELETE FROM StringKeyValuePairTable WHERE `key` = :key")
    suspend fun delete(@NonNull key: String)

    @Query("DELETE FROM StringKeyValuePairTable")
    suspend fun clear()

    @Query("SELECT * FROM StringKeyValuePairTable")
    suspend fun getAll(): List<StringKeyValuePairTable>?
}