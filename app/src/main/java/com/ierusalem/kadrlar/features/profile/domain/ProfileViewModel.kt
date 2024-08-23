package com.ierusalem.kadrlar.features.profile.domain

import androidx.lifecycle.ViewModel
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel(),
    NavigationEventDelegate<ProfileScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState()
    )
    val state = _state.asStateFlow()

    fun handleClickIntents(intent: ProfileScreenClickIntents) {
        when (intent) {
            ProfileScreenClickIntents.OnNavIconClicked -> {
                emitNavigation(ProfileScreenNavigation.OnNavIconClicked)
            }
        }
    }
}

data class ProfileScreenState(
    val value:String = ""
)