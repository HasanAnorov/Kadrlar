package com.ierusalem.kadrlar.features.home.domain

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.NavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.androchat.core.utils.UiText
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.connectivity.ConnectivityObserver
import com.ierusalem.kadrlar.core.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    connectivityObserver: ConnectivityObserver
) : ViewModel(),
    NavigationEventDelegate<HomeScreenNavigation> by DefaultNavigationEventDelegate() {

    init {
        connectivityObserver.observe().onEach { connectivityStatus ->
            when (connectivityStatus) {
                ConnectivityObserver.Status.Available -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connected)
                        )
                    }
                }

                ConnectivityObserver.Status.Loosing -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connectivity_loosing)
                        )
                    }
                }

                ConnectivityObserver.Status.Lost -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connectivity_lost)
                        )
                    }
                }

                ConnectivityObserver.Status.Unavailable -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connectivity_unavailable)
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

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
            HomeScreenClickIntents.DrawerSettingClick -> {
                emitNavigation(HomeScreenNavigation.NavigateToSettings)
            }

            HomeScreenClickIntents.OnSearchClick -> {

            }

            is HomeScreenClickIntents.TabItemClicked -> {

            }

            HomeScreenClickIntents.NavIconClicked -> {
                log("click")
                openDrawer()
            }

        }
    }

}

@Immutable
data class HomeScreenState(
    //connectivity
    val connectivityStatus: UiText = UiText.StringResource(R.string.connectivity_unavailable),

    )