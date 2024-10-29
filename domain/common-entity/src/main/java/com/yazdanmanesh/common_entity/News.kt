package com.yazdanmanesh.common_entity

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)