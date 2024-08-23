package com.ierusalem.kadrlar.features.super_user.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.AppBar
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.super_user.home.domain.HomeScreenState
import com.ierusalem.kadrlar.features.super_user.home.presentation.components.HomeContent
import com.ierusalem.kadrlar.features.super_user.home.domain.HomeScreenClickIntents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUiScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    eventHandler: (HomeScreenClickIntents) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { pv ->
        Column {
            AppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                onNavIconPressed = { eventHandler(HomeScreenClickIntents.OnNavIconClicked) },
            )
            HomeContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(bottom = pv.calculateBottomPadding())
                    .imePadding()
                    // So this basically doesn't do anything since we handle the padding ourselves
                    // BUT, we don't just want to consume it because we DO actually care when using
                    // Modifier.navigationBarsPadding()
                    .heightIn(min = pv.calculateBottomPadding()),
                uiState = uiState,
                eventHandler = eventHandler
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLight() {
    KadrlarTheme {
        HomeUiScreen(uiState = HomeScreenState(), eventHandler = {})
    }
}