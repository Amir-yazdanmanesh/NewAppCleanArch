package com.yazdanmanesh.repository

import androidx.paging.PagingData
import com.yazdanmanesh.common_entity.Article
import kotlinx.coroutines.flow.Flow

interface TehranArticlesRepository {
    fun getTehranArticles(): Result<Flow<PagingData<Article>>>
}