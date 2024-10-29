package com.yazdanmanesh.common_entity

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String?,
    val name: String
)