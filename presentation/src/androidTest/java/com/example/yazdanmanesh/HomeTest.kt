package com.example.yazdanmanesh

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.yazdanmanesh.ui.feature.home.comosables.HomeListItems
import com.example.yazdanmanesh.ui.utils.Constants.SHIMMER_ITEM_COUNT
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.common_entity.generateFakeArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test


class HomeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun createFakePagingData(loadResult: PagingSource.LoadResult<Int, Article>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { FakePagingSource(loadResult) }
        ).flow
    }

    @Test
    fun homeListItems_firstDisplaysShimmer() {
        // Mock data
        val testArticles = generateFakeArticles(5)
        val articlesFlow = flowOf(PagingData.from(testArticles))

        composeTestRule.setContent {
            HomeListItems(
                articles = articlesFlow,
                onAddArticleClicked = {}
            )
        }
        composeTestRule.onNodeWithTag("shimmerEffect")
        composeTestRule.onNodeWithTag("homeListItemsLazyColumn")
            .onChildren()
            .assertCountEquals(SHIMMER_ITEM_COUNT)

    }

    @Test
    fun homeListItems_displaysArticlesOnSuccess() {
        val articles = generateFakeArticles(5)
        val loadResult = PagingSource.LoadResult.Page<Int, Article>(
            data = articles,
            prevKey = null,
            nextKey = null
        )
        val pagingData = createFakePagingData(loadResult)

        composeTestRule.setContent {
            HomeListItems(
                articles = pagingData,
                onAddArticleClicked = {}
            )
        }

        articles.forEach { article ->
            composeTestRule.onNodeWithText(article.title).assertExists()
        }
    }

    @Test
    fun homeListItems_displaysErrorMessageOnError() {
        val errorMessage = "Test Error Message"
        val loadResult = PagingSource.LoadResult.Error<Int, Article>(Throwable(errorMessage))
        val pagingData = createFakePagingData(loadResult)

        composeTestRule.setContent {
            HomeListItems(
                articles = pagingData,
                onAddArticleClicked = {}
            )
        }

        composeTestRule.onNodeWithText(errorMessage).assertExists()
    }
}
