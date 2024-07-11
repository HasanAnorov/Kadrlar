package com.ierusalem.kadrlar.features.profile.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileUiScreen() {
    Scaffold {paddingValues ->
        Text(text = "Profile", modifier = Modifier.padding(paddingValues))
    }
}