package com.yazdanmanesh.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fetch_timestamps")
data class LastFetchedTimeEntity(
    @PrimaryKey val id: String = "articles",
    val lastFetchedTime: Long,
)