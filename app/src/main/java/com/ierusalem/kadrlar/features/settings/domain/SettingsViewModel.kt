package com.ierusalem.kadrlar.features.settings.domain

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.NavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.androchat.features.settings.presentation.SettingsScreenNavigation
import com.ierusalem.kadrlar.core.app.AppLanguage
import com.ierusalem.kadrlar.core.preferences.DataStorePreferenceRepository
import com.ierusalem.kadrlar.core.utils.Constants.getLanguageCode
import com.ierusalem.kadrlar.core.utils.Constants.getLanguageFromCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel(),
    NavigationEventDelegate<SettingsScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    fun initLanguageAndTheme() {
        viewModelScope.launch {
            val isSystemInDarkMode = dataStorePreferenceRepository.getTheme.first()
            val language = getLanguageFromCode(dataStorePreferenceRepository.getLanguage.first())
            _state.update { settingsState ->
                settingsState.copy(
                    selectedLanguage = language,
                    appTheme = isSystemInDarkMode
                )
            }
        }
    }

    private fun changeLanguage(language: AppLanguage) {
        viewModelScope.launch {
            dataStorePreferenceRepository.setLanguage(getLanguageCode(language))
            _state.update { settingsState ->
                settingsState.copy(
                    selectedLanguage = language
                )
            }
        }
    }

    fun handleEvents(event: SettingsScreenEvents) {
        when (event) {
            SettingsScreenEvents.OnThemeChange -> {
                viewModelScope.launch {
                    dataStorePreferenceRepository.setTheme(!state.value.appTheme)
                    _state.update {
                        it.copy(
                            appTheme = !state.value.appTheme
                        )
                    }
                }
            }
            SettingsScreenEvents.NavIconClick -> {
                emitNavigation(SettingsScreenNavigation.NavIconClick)
            }

            SettingsScreenEvents.LanguageCLick -> {
                _state.update {
                    it.copy(
                        languageDialogVisibility = true
                    )
                }
            }

            SettingsScreenEvents.OnDismissLanguageDialog -> {
                _state.update {
                    it.copy(
                        languageDialogVisibility = false
                    )
                }
            }

            is SettingsScreenEvents.OnLanguageChange -> {
                changeLanguage(event.language)
            }
        }
    }


}

@Immutable
data class SettingsState(
    val languageDialogVisibility: Boolean = false,
    val languagesList: List<AppLanguage> = listOf(
        AppLanguage.English,
        AppLanguage.Russian,
    ),
    val selectedLanguage: AppLanguage = languagesList.first{it.isSelected},
    val appTheme: Boolean = false
)
