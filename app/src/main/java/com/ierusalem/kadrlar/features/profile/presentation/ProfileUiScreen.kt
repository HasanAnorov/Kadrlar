package com.ierusalem.kadrlar.features.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.kadrlar.core.ui.components.LoadingScreen
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.AppBar
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.core.utils.ResourceWithLoading
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenClickIntents
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenState
import com.ierusalem.kadrlar.features.profile.presentation.components.ProfileContent
import com.ierusalem.kadrlar.features.profile.presentation.components.ProfileError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileUiScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileScreenState,
    eventHandler: (ProfileScreenClickIntents) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { pv ->
        Column {
            AppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.profile),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavIconPressed = { eventHandler(ProfileScreenClickIntents.OnNavIconClicked) },
            )
            when(uiState.profileScreen){
                is ResourceWithLoading.Loading -> {
                    LoadingScreen(modifier = Modifier.weight(1F))
                }
                is ResourceWithLoading.Success -> {
                    val profileData = uiState.profileScreen.data!!
                    ProfileContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)
                            .padding(bottom = pv.calculateBottomPadding())
                            .imePadding()
                            // So this basically doesn't do anything since we handle the padding ourselves
                            // BUT, we don't just want to consume it because we DO actually care when using
                            // Modifier.navigationBarsPadding()
                            .heightIn(min = pv.calculateBottomPadding()),
                        uiState = profileData,
                        eventHandler = eventHandler
                    )
                }
                is ResourceWithLoading.Failure -> {
                    ProfileError(
                        modifier = Modifier.weight(1F)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLight() {
    KadrlarTheme {
        ProfileUiScreen(uiState = ProfileScreenState(ResourceWithLoading.Loading()), eventHandler = {})
    }
}