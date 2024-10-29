package com.example.yazdanmanesh.ui.feature.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yazdanmanesh.ui.theme.NewsComposeTheme

@Composable
fun Progress(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPreview() {
    NewsComposeTheme {
        Progress(Modifier.fillMaxSize())
    }
}
