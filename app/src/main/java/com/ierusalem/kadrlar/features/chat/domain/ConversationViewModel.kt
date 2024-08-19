package com.ierusalem.kadrlar.features.chat.domain

import androidx.lifecycle.ViewModel
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import com.ierusalem.kadrlar.features.settings.domain.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConversationViewModel : ViewModel(),
    NavigationEventDelegate<ConversationNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    fun handleEvents(events: ConversationUiEvents) {
        when (events) {
            ConversationUiEvents.NavIconClick -> {
                emitNavigation(ConversationNavigation.NavIconClick)
            }
        }
    }


}