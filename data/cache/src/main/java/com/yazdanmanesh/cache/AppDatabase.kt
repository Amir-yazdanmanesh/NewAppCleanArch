package com.yazdanmanesh.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yazdanmanesh.cache.model.ArticleEntity
import com.yazdanmanesh.cache.converter.SourceConverter
import com.yazdanmanesh.cache.model.LastFetchedTimeEntity
import com.yazdanmanesh.cache.model.RemoteKey

@Database(
    entities = [ArticleEntity::class, LastFetchedTimeEntity::class, RemoteKey::class],
    version = 1
)
@TypeConverters(SourceConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTehranArticlesDao(): TehranArticlesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
    abstract fun getSettingsDao(): SettingsDao
}