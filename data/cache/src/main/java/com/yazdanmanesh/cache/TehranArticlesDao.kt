package com.yazdanmanesh.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yazdanmanesh.cache.model.ArticleEntity

@Dao
interface TehranArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeArticle(articles: ArticleEntity): Long

    @Query("SELECT * FROM articles ")
    fun getPagingTehranArticles(): PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun deleteArticles()

    @Query("SELECT * FROM articles ")
    suspend fun getTehranArticles(): List<ArticleEntity>
}
