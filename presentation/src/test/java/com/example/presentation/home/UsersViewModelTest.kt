package com.example.presentation.home

import androidx.paging.PagingData
import com.example.yazdanmanesh.ui.feature.home.HomeContract
import com.example.yazdanmanesh.ui.feature.home.HomeViewModel
import com.example.presentation.utils.MainCoroutineRule
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.common_entity.generateFakeArticles
import com.yazdanmanesh.usecase.TehranArticlesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@DelicateCoroutinesApi
class UsersViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val tehranArticlesUseCase = mockk<TehranArticlesUseCase>()
    private val pagingData: Flow<PagingData<Article>> = flowOf(PagingData.from(generateFakeArticles(5)))

    @Test
    fun `When getTehranArticlesUseCase called successfully then should emit DataLoaded state`() = runTest {
        // Arrange
        coEvery { tehranArticlesUseCase.getTehranArticlesUseCase() } returns Result.success(pagingData)
        val viewModel = HomeViewModel(tehranArticlesUseCase)
        val expectedState = HomeContract.HomeState.DataLoaded(pagingData)

        // Assert
        assertEquals(expectedState, viewModel.viewState.value)
    }

    @Test
    fun `When getTehranArticlesUseCase fails then should emit Error state`() = runTest {
        // Arrange
        coEvery { tehranArticlesUseCase.getTehranArticlesUseCase() } returns Result.failure(Exception("Network error"))
        val viewModel = HomeViewModel(tehranArticlesUseCase)
        val expectedState = HomeContract.HomeState.Error

        // Assert
        assertEquals(expectedState, viewModel.viewState.value)
    }

    @Test
    fun `When Retry event is triggered after failure then should emit DataLoaded state`() = runTest {
        // Arrange
        coEvery { tehranArticlesUseCase.getTehranArticlesUseCase() } returns Result.failure(Exception("Network error"))
        val viewModel = HomeViewModel(tehranArticlesUseCase)
        assertEquals(HomeContract.HomeState.Error, viewModel.viewState.value)

        // Act
        coEvery { tehranArticlesUseCase.getTehranArticlesUseCase() } returns Result.success(pagingData)
        viewModel.setEvent(HomeContract.Event.Retry)

        // Assert
        assertEquals(HomeContract.HomeState.DataLoaded(pagingData), viewModel.viewState.value)
    }
}
