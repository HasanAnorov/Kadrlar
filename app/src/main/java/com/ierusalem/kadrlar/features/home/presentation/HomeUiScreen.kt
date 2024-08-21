package com.ierusalem.kadrlar.features.home.presentation

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
import com.ierusalem.kadrlar.core.ui.components.AppBar
import com.ierusalem.kadrlar.features.home.domain.HomeScreenClickIntents
import com.ierusalem.kadrlar.features.home.domain.HomeScreenState
import com.ierusalem.kadrlar.features.home.presentation.components.HomeContent

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
                        text = uiState.connectivityStatus.asString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                onNavIconPressed = { eventHandler(HomeScreenClickIntents.NavIconClicked) },
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