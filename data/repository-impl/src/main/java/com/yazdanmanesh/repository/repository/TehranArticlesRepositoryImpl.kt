package com.yazdanmanesh.repository.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yazdanmanesh.cache.RemoteKeysDao
import com.yazdanmanesh.cache.TehranArticlesDao
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.network.service.NewsApi
import com.yazdanmanesh.network.utils.Constants.FETCH_REFRESH_TIME_TIME_MILLISECONDS
import com.yazdanmanesh.network.utils.Constants.MAX_PAGING_SIZE
import com.yazdanmanesh.repository.TehranArticlesRepository
import com.yazdanmanesh.repository.mapper.ArticleMapper
import com.yazdanmanesh.repository.paging.TehranArticlesRemoteMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TehranArticlesRepositoryImpl @Inject constructor(
    private val articleDao: TehranArticlesDao,
    private val remoteMediator: TehranArticlesRemoteMediator,
    private val articlesApi: NewsApi,
    private val remoteKeysDao: RemoteKeysDao,
    private val mapper: ArticleMapper,
) : TehranArticlesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getTehranArticles(): Result<Flow<PagingData<Article>>> {
        return runCatching {
            Pager(
                config = PagingConfig(
                    pageSize = MAX_PAGING_SIZE,
                ),
                remoteMediator = remoteMediator,
                pagingSourceFactory = { articleDao.getPagingTehranArticles() }
            ).flow.map { pagingData ->
                pagingData.map { mapper.articleEntityToDomain(it) }
            }
        }
    }

    private val hashedLocalData = mutableSetOf<Int>()

    override fun notifyNewArticles(): Flow<Int> = flow {
        hashedLocalData.clear()
        hashedLocalData.addAll(
            mapper.listArticleEntityToDto(articleDao.getTehranArticles())
                .asSequence()
                .map { it.hashCode() }
        )

        while (true) {
            delay(FETCH_REFRESH_TIME_TIME_MILLISECONDS)
            val newArticleCount = countNewArticles()
            if (newArticleCount > 0) {
                emit(newArticleCount)
            }
        }
    }.catch {
        emit(-1)
    }

    private suspend fun countNewArticles(): Int {
        var newArticleCount = 0
        val lastPage = remoteKeysDao.getLastRemoteKey()?.nextPage ?: 1

        for (page in 1 until lastPage) {
            val articles =
                articlesApi.getTehranArticles(page = page, num = MAX_PAGING_SIZE).articles

            articles.forEach { article ->
                if (!hashedLocalData.contains(article.hashCode())) {
                    hashedLocalData.add(article.hashCode())
                    newArticleCount++
                }
            }
            delay(50) // Throttling API requests
        }
        return newArticleCount
    }
}
