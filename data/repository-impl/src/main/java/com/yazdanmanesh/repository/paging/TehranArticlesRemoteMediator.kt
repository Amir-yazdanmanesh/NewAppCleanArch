package com.yazdanmanesh.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.yazdanmanesh.cache.AppDatabase
import com.yazdanmanesh.cache.model.ArticleEntity
import com.yazdanmanesh.cache.model.LastFetchedTimeEntity
import com.yazdanmanesh.cache.model.RemoteKey
import com.yazdanmanesh.network.service.NewsApi
import com.yazdanmanesh.network.utils.Constants.FIRST_PAGE
import com.yazdanmanesh.network.utils.Constants.MAX_PAGING_SIZE
import com.yazdanmanesh.network.utils.Constants.REFRESH_TIME_OUT_MINUTES
import com.yazdanmanesh.repository.mapper.ArticleMapper
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TehranArticlesRemoteMediator @Inject constructor(
    private val database: AppDatabase,
    private val api: NewsApi,
    private val mapper: ArticleMapper,
) : RemoteMediator<Int, ArticleEntity>() {
    private val newsDao = database.getTehranArticlesDao()
    private val keysDao = database.getRemoteKeysDao()
    private val settingsDao = database.getSettingsDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: FIRST_PAGE
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response =
                api.getTehranArticles(page = currentPage, num = MAX_PAGING_SIZE)

            val endOfPaginationReached = response.totalResults == currentPage

            val prevPage = if (currentPage == FIRST_PAGE) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDao.deleteArticles()
                    keysDao.clearRemoteKeys()
                }
                response.articles.forEach {
                    val id = newsDao.storeArticle(mapper.articleDtoToEntity(it))
                    val key =
                        RemoteKey(
                            articleId = id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    keysDao.insert(key)
                }

                val currentTime = System.currentTimeMillis()
                settingsDao.updateLastFetchedTime(LastFetchedTimeEntity(lastFetchedTime = currentTime))
            }
            MediatorResult.Success(endOfPaginationReached)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, ArticleEntity>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keysDao.getRemoteKeysForArticle(id = id)
            }
        }
    }

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(REFRESH_TIME_OUT_MINUTES, TimeUnit.MINUTES)
        return if (settingsDao.getLastFetchedTime() != null && System.currentTimeMillis() - settingsDao.getLastFetchedTime()!! <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, ArticleEntity>
    ): RemoteKey? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { entity ->
                keysDao.getRemoteKeysForArticle(entity.id)
            }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, ArticleEntity>
    ): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { entity ->
            keysDao.getRemoteKeysForArticle(entity.id)
        }
    }
}
