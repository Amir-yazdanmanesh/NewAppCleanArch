package com.yazdanmanesh.usecase

import androidx.paging.PagingData
import com.yazdanmanesh.common_entity.Article
import kotlinx.coroutines.flow.Flow

interface TehranArticlesUseCase {
    fun getTehranArticlesUseCase():  Result<Flow<PagingData<Article>>>
}