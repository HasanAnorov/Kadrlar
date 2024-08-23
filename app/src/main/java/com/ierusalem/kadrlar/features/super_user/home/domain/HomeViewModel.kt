package com.ierusalem.kadrlar.features.super_user.home.domain

import androidx.lifecycle.ViewModel
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel(),
    NavigationEventDelegate<HomeScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState()
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

    fun handleClickIntents(intent: HomeScreenClickIntents) {
        when (intent) {
            HomeScreenClickIntents.OnNavIconClicked -> {
                openDrawer()
            }
            HomeScreenClickIntents.OnSettingsClicked -> {
                emitNavigation(HomeScreenNavigation.OnSettingsClicked)
            }
            HomeScreenClickIntents.OnProfileClicked -> {
                emitNavigation(HomeScreenNavigation.OnProfileClicked)
            }
        }
    }
}

data class HomeScreenState(
    val value:String = ""
)