package com.yazdanmanesh.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yazdanmanesh.cache.model.RemoteKey

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM remote_keys WHERE articleId = :id")
    suspend fun getRemoteKeysForArticle(id: Long): RemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKeys: RemoteKey)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()

    @Query("SELECT * FROM remote_keys ORDER BY articleId DESC LIMIT 1")
    suspend fun getLastRemoteKey(): RemoteKey?
}
