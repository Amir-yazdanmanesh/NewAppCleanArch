package com.example.yazdanmanesh.ui.feature.home.comosables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.yazdanmanesh.R
import com.example.yazdanmanesh.ui.feature.common.ErrorMessage
import com.example.yazdanmanesh.ui.feature.common.LoadingNextPageItem
import com.example.yazdanmanesh.ui.feature.common.ShimmerEffect
import com.example.yazdanmanesh.ui.utils.Constants.SHIMMER_ANIMATION_DURATION
import com.example.yazdanmanesh.ui.utils.Constants.SHIMMER_ITEM_COUNT
import com.example.yazdanmanesh.ui.utils.Constants.SHIMMER_ROUNDED_CORNER_SIZE
import com.yazdanmanesh.common_entity.Article
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeListItems(
    articles: Flow<PagingData<Article>>,
    onAddArticleClicked: (Article) -> Unit
) {
    val articleList = articles.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.size_4))
                .testTag("homeListItemsLazyColumn"),
        ) {
            item { Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4))) }

            articleList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        items(SHIMMER_ITEM_COUNT) {
                            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4)))
                            ShimmerEffect(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(dimensionResource(id = R.dimen.size_128))
                                    .background(
                                        Color.LightGray,
                                        RoundedCornerShape(SHIMMER_ROUNDED_CORNER_SIZE)
                                    )
                                    .testTag("shimmerEffect"),
                                durationMillis = SHIMMER_ANIMATION_DURATION
                            )
                            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4)))
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = articleList.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem(modifier = Modifier) }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = articleList.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier,
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }
                }

                items(
                    count = articleList.itemCount,
                    key = articleList.itemKey { it.id },
                ) { index ->
                    articleList[index]?.let { article ->
                        HomeListItem(
                            article = article,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onAddArticleClicked(article) }
                                .padding(dimensionResource(id = R.dimen.size_12))
                                .testTag("homeListItem")
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4))) }
        }
    }
}
