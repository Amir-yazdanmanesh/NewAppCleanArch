package com.example.yazdanmanesh.ui.feature.details.comosables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.yazdanmanesh.R
import com.example.yazdanmanesh.ui.base.SIDE_EFFECTS_KEY
import com.example.yazdanmanesh.ui.feature.details.DetailsContract
import com.example.yazdanmanesh.ui.utils.Constants.DETAILS_ANIMATION_DELAY
import com.example.yazdanmanesh.ui.utils.Constants.FUNNY_BUTTON_ANIMATION_DURATION
import com.example.yazdanmanesh.ui.utils.Constants.FUNNY_TEXT_STOP_DELAY
import com.example.yazdanmanesh.ui.utils.Constants.FUNNY_TEXT_VISIBLE_DELAY
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.common_entity.generateFakeArticles
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    state: DetailsContract.DetailsState,
    effectFlow: Flow<DetailsContract.Effect>?,
    onNavigationRequested: (navigationEffect: DetailsContract.Effect.Navigation) -> Unit,
    onEventSent: (event: DetailsContract.Event) -> Unit,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                DetailsContract.Effect.Navigation.Back -> {
                    onNavigationRequested(DetailsContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DetailsTopBar {
                onEventSent(DetailsContract.Event.BackButtonClicked)
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (state) {
                is DetailsContract.DetailsState.Initial -> {
                    DetailsScreenContent(article = state.article!!)
                }
            }
        }
    }
}


@Composable
fun DetailsScreenContent(article: Article) {
    var visible by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf(article.title) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(DETAILS_ANIMATION_DELAY)
        visible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimensionResource(id = R.dimen.size_16)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ) + scaleIn(
                initialScale = 0.8f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.size_24).value.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .offset { IntOffset(offsetX.value.toInt(), offsetY.value.toInt()) }
                    .padding(dimensionResource(id = R.dimen.size_16))
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16)))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ) + scaleIn(
                initialScale = 0.8f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            ArticleImage(urlToImage = article.urlToImage)
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16)))

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ) + scaleIn(
                initialScale = 0.8f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            ArticleContent(content = article.content)
        }

        // This button and its animation are just for fun ;)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_32)))
        if (visible){
            Button(
                onClick = {
                    title = context.getString(R.string.i_m_gone_find_me)
                    coroutineScope.launch {
                        delay(FUNNY_TEXT_VISIBLE_DELAY)
                        val targetX = (0..screenWidth.value.toInt()).random().toFloat()
                        val targetY = (0..screenHeight.value.toInt()).random().toFloat()
                        offsetX.animateTo(targetX, animationSpec = tween(durationMillis = FUNNY_BUTTON_ANIMATION_DURATION))
                        offsetY.animateTo(targetY, animationSpec = tween(durationMillis = FUNNY_BUTTON_ANIMATION_DURATION))
                        delay(FUNNY_TEXT_STOP_DELAY)
                        title = context.getString(R.string.oh_am_i_still_visible)
                    }
                }
            ) {
                Text(stringResource(R.string.don_t_touch_me))
            }
        }
    }
}

@Composable
fun ArticleImage(urlToImage: String?) {
    if (urlToImage != null) {
        AsyncImage(
            model = urlToImage,
            contentDescription = stringResource(R.string.article_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_200))
                .background(
                    Color.Gray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
                )
                .padding(dimensionResource(id = R.dimen.size_8))
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_200))
                .background(
                    Color.Gray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.image_not_available),
                color = Color.White,
                fontSize = dimensionResource(id = R.dimen.font_16).value.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ArticleContent(content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
            )
            .padding(dimensionResource(id = R.dimen.size_16))
    ) {
        Text(
            text = content,
            fontSize = dimensionResource(id = R.dimen.font_16).value.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenSuccessPreview() {
    DetailsScreen(
        state = DetailsContract.DetailsState.Initial(
            article = generateFakeArticles(1).first(),
        ),
        effectFlow = null,
        onNavigationRequested = {},
        onEventSent = {}
    )
}
