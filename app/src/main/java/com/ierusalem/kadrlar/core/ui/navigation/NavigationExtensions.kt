package com.ierusalem.androchat.core.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import kotlinx.coroutines.launch

fun <VM, EVENT> VM.emitNavigation(event: EVENT) where VM: NavigationEventDelegate<EVENT>, VM : ViewModel{
    viewModelScope.launch { sendEvent(event) }
}