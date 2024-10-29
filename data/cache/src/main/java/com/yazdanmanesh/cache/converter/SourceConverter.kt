package com.yazdanmanesh.cache.converter

import androidx.room.TypeConverter
import com.yazdanmanesh.cache.model.SourceEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SourceConverter {
    @TypeConverter
    fun fromString(value: String): SourceEntity {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(data: SourceEntity): String {
        return Json.encodeToString(data)
    }
}