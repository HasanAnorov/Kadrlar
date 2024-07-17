package com.ierusalem.kadrlar.core.ui.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue.Closed
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.ierusalem.kadrlar.features.home.domain.HomeScreenClickIntents

@Composable
fun KadrlarDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = Closed),
    onDrawerItemClick: (HomeScreenClickIntents) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                KadrlarDrawerContent(
                    onDrawerItemClick = onDrawerItemClick
                )
            }
        },
        content = content
    )
}
