package com.ierusalem.kadrlar.features.super_user.home.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.super_user.home.domain.SuperHomeScreenState
import com.ierusalem.kadrlar.features.super_user.home.domain.SuperHomeScreenClickIntents

@Composable
fun SuperHomeContent(
    modifier: Modifier = Modifier,
    uiState: SuperHomeScreenState,
    eventHandler: (SuperHomeScreenClickIntents) -> Unit
) {
    LazyColumn {
        items(10) {
            UserItem()
        }
    }
}

@Preview
@Composable
private fun SuperHomeContentPreview() {
    KadrlarTheme {
        SuperHomeContent(uiState = SuperHomeScreenState(), eventHandler = {})
    }
}

@Preview
@Composable
private fun SuperHomeContentPreviewDark() {
    KadrlarTheme(isDarkTheme = true) {
        SuperHomeContent(uiState = SuperHomeScreenState(), eventHandler = {})
    }
}