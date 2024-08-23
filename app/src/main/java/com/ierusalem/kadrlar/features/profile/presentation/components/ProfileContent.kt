package com.ierusalem.kadrlar.features.profile.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenClickIntents
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenState

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    uiState: ProfileScreenState,
    eventHandler: (ProfileScreenClickIntents) -> Unit,
) {

}