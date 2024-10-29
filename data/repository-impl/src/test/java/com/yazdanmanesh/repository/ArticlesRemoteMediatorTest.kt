package com.yazdanmanesh.repository

import androidx.paging.*
import androidx.room.withTransaction
import com.yazdanmanesh.cache.AppDatabase
import com.yazdanmanesh.cache.RemoteKeysDao
import com.yazdanmanesh.cache.SettingsDao
import com.yazdanmanesh.cache.TehranArticlesDao
import com.yazdanmanesh.cache.model.ArticleEntity
import com.yazdanmanesh.network.dto.getNewsDto
import com.yazdanmanesh.network.service.NewsApi
import com.yazdanmanesh.repository.mapper.ArticleMapper
import com.yazdanmanesh.repository.paging.TehranArticlesRemoteMediator
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ArticlesRemoteMediatorTest {

    private lateinit var articleDao: TehranArticlesDao
    private lateinit var remoteMediator: TehranArticlesRemoteMediator
    private lateinit var articlesApi: NewsApi
    private lateinit var remoteKeysDao: RemoteKeysDao
    private lateinit var settingsDao: SettingsDao
    private lateinit var mapper: ArticleMapper
    private lateinit var database: AppDatabase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic("androidx.room.RoomDatabaseKt")

        articlesApi = mockk(relaxed = true)
        articleDao = mockk(relaxed = true)
        remoteKeysDao = mockk(relaxed = true)
        settingsDao = mockk(relaxed = true)
        mapper = mockk(relaxed = true)

        database = mockk(relaxed = true) {
            every { getTehranArticlesDao() } returns articleDao
            every { getRemoteKeysDao() } returns remoteKeysDao
            every { getSettingsDao() } returns settingsDao
            every { runInTransaction(any()) } answers { firstArg<Runnable>().run() }
        }

        val transactionLambda = slot<suspend () -> AppDatabase>()
        coEvery { database.withTransaction(capture(transactionLambda)) } coAnswers {
            transactionLambda.captured.invoke()
        }

        remoteMediator = TehranArticlesRemoteMediator(database, articlesApi, mapper)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        // Arrange
        val pagingState = PagingState<Int, ArticleEntity>(
            listOf(),
            null,
            PagingConfig(pageSize = 10),
            10
        )

        // Act
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        // Assert
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun loadRefreshSuccess_whenRemoteReturnsData() = runTest {
        // Arrange
        coEvery { articlesApi.getTehranArticles(num = 10, page = any()) } returns getNewsDto(1).copy(totalResults = 2)
        coEvery { settingsDao.updateLastFetchedTime(any()) } just Runs
        coEvery { articleDao.deleteArticles() } just Runs
        coEvery { remoteKeysDao.clearRemoteKeys() } just Runs
        coEvery { articleDao.storeArticle(any()) } returns 1L
        coEvery { remoteKeysDao.insert(any()) } just Runs

        val pagingState = PagingState<Int, ArticleEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 10),
            leadingPlaceholderCount = 0
        )

        // Act
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        // Assert
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        // Verify interactions
        coVerify(exactly = 1) { articleDao.deleteArticles() }
        coVerify(exactly = 1) { remoteKeysDao.clearRemoteKeys() }
    }
}
