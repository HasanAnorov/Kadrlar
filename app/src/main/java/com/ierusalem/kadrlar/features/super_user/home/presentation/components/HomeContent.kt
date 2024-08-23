package com.ierusalem.kadrlar.features.super_user.home.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ierusalem.kadrlar.features.super_user.home.domain.HomeScreenClickIntents
import com.ierusalem.kadrlar.features.super_user.home.domain.HomeScreenState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    eventHandler: (HomeScreenClickIntents) -> Unit
) {

}