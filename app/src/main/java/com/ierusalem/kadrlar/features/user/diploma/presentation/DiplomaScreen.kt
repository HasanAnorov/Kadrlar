package com.ierusalem.kadrlar.features.user.diploma.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaScreenClickIntents
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiplomaUiScreen(
    modifier: Modifier = Modifier,
    eventHandler: (DiplomaScreenClickIntents) -> Unit,
    uiState: DiplomaScreenState,
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { pv ->
        Column {
            AppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.diplomas),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                actions = {
                    Button(
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        onClick = { eventHandler(DiplomaScreenClickIntents.OnSaveClicked) },
                        content = {
                            Text(text = "Save", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    )
                },
                onNavIconPressed = { eventHandler(DiplomaScreenClickIntents.OnNavIconClicked) },
                navIcon = Icons.AutoMirrored.Filled.ArrowBack
            )
            DiplomaContent(
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
        DiplomaUiScreen(eventHandler = {}, uiState = DiplomaScreenState())
    }
}