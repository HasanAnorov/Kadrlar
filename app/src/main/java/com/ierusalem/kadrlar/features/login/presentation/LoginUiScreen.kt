package com.ierusalem.kadrlar.features.login.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun LoginUiScreen() {
    Scaffold { paddingValues ->
        Text(modifier = Modifier.padding(paddingValues), text = "Login")
    }
}

@Preview
@Composable
private fun LoginPreviewLight() {
    KadrlarTheme(isDarkTheme = false) {
        LoginUiScreen()
    }
}

@Preview
@Composable
private fun LoginPreviewDark() {
    KadrlarTheme(isDarkTheme = true) {
        LoginUiScreen()
    }
}
