package com.example.yazdanmanesh.ui.feature.home.comosables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.yazdanmanesh.R

@Composable
fun HomeTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeTopBarPreview() {
    HomeTopBar()
}