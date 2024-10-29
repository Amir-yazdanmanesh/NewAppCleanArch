package com.example.yazdanmanesh

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.yazdanmanesh.ui.feature.details.DetailsContract
import com.example.yazdanmanesh.ui.feature.details.comosables.DetailsScreen
import com.example.yazdanmanesh.ui.utils.Constants
import com.yazdanmanesh.common_entity.generateFakeArticles
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysArticleDetails_whenInitializedWithArticle() {
        // Arrange
        val article = generateFakeArticles(1).first()
        composeTestRule.setContent {
            DetailsScreen(state = DetailsContract.DetailsState.Initial(article),
                effectFlow = null,
                onNavigationRequested = {},
                onEventSent = {})
        }

        // Act
        composeTestRule.mainClock.advanceTimeBy(Constants.DETAILS_ANIMATION_DELAY)

        // Assert
        composeTestRule.onNodeWithText(article.content).assertIsDisplayed()
        composeTestRule.onNodeWithText(article.title).assertIsDisplayed()
    }
}
