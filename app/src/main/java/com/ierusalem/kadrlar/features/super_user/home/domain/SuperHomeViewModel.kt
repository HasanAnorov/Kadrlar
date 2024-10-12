package com.ierusalem.kadrlar.features.super_user.home.domain

import androidx.lifecycle.ViewModel
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SuperHomeViewModel : ViewModel(),
    NavigationEventDelegate<SuperHomeScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<SuperHomeScreenState> = MutableStateFlow(
        SuperHomeScreenState()
    )
    val state = _state.asStateFlow()

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    private fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    fun handleClickIntents(intent: SuperHomeScreenClickIntents) {
        when (intent) {
            SuperHomeScreenClickIntents.OnNavIconClicked -> {
                openDrawer()
            }
            SuperHomeScreenClickIntents.OnSettingsClicked -> {
                emitNavigation(SuperHomeScreenNavigation.OnSettingsClicked)
            }
            SuperHomeScreenClickIntents.OnProfileClicked -> {
                emitNavigation(SuperHomeScreenNavigation.OnProfileClicked)
            }
        }
    }
}

data class SuperHomeScreenState(
    val value:String = ""
)