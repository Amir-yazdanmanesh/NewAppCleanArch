package com.yazdanmanesh.usecaseImp

import androidx.paging.PagingData
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.repository.TehranArticlesRepository
import com.yazdanmanesh.usecase.TehranArticlesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TehranArticlesUseCaseImp @Inject constructor(private val articlesRepository: TehranArticlesRepository) :
    TehranArticlesUseCase {
    override fun getTehranArticlesUseCase(): Result<Flow<PagingData<Article>>> {
        return articlesRepository.getTehranArticles()
    }
}
