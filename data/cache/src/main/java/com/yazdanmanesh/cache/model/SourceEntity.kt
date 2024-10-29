package com.yazdanmanesh.cache.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceEntity(
    val id: String?,
    val name: String
)