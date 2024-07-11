package com.ierusalem.kadrlar.features.settings.presentation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun SettingsUiScreen() {
    Scaffold { paddingValues ->
        Text(
            modifier = Modifier.padding(paddingValues),
            text = "Settings"
        )
    }
}

@Preview
@Composable
private fun PreviewSettingsUiScreenLight() {
    KadrlarTheme(isDarkTheme = false) {
        SettingsUiScreen()
    }
}

@Preview
@Composable
private fun PreviewSettingsUiScreenDark() {
    KadrlarTheme(isDarkTheme = true) {
        SettingsUiScreen()
    }
}