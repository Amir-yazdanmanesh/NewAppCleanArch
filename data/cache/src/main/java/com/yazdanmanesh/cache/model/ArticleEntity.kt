package com.yazdanmanesh.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    val author: String?,
    val content: String,
    val description: String?,
    val publishedAt: String,
    val source: SourceEntity,
    val title: String,
    val url: String,
    val urlToImage: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)
