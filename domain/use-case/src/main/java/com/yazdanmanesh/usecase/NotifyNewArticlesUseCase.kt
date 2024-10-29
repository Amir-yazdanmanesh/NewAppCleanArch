package com.yazdanmanesh.usecase

import com.yazdanmanesh.repository.TehranArticlesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotifyNewArticlesUseCase @Inject constructor(private val articlesRepository: TehranArticlesRepository) :
    UseCase<Flow<Int>>() {
    override fun buildUseCase(): Flow<Int> {
        return articlesRepository.notifyNewArticles()
    }
}
