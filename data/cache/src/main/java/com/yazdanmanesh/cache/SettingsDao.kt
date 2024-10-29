package com.yazdanmanesh.cache

import androidx.room.*
import com.yazdanmanesh.cache.model.LastFetchedTimeEntity

@Dao
interface SettingsDao {
    @Query("SELECT lastFetchedTime FROM fetch_timestamps WHERE id = :id LIMIT 1")
    suspend fun getLastFetchedTime(id: String = "articles"): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLastFetchedTime(fetchTimestamp: LastFetchedTimeEntity)

}