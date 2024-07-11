package com.ierusalem.kadrlar.features.home.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.home.domain.HomeScreenClickIntents
import com.ierusalem.kadrlar.features.home.domain.HomeScreenState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    eventHandler: (HomeScreenClickIntents) -> Unit
) {

}

@Preview
@Composable
private fun PreviewHomeContentLight() {
    KadrlarTheme(isDarkTheme = false) {
        HomeContent(
            uiState = HomeScreenState(),
            eventHandler = {}
        )
    }
}

@Preview
@Composable
private fun PreviewHomeContentDark() {
    KadrlarTheme(isDarkTheme = true) {
        HomeContent(
            uiState = HomeScreenState(),
            eventHandler = {}
        )
    }
}