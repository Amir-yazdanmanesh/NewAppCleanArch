package com.yazdanmanesh.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey val articleId: Long,
    val prevPage: Int?,
    val nextPage: Int?,
)
