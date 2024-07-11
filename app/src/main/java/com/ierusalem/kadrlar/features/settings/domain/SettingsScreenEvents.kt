package com.ierusalem.kadrlar.features.settings.domain

import com.ierusalem.kadrlar.core.app.AppLanguage

sealed interface SettingsScreenEvents {
    data object NavIconClick : SettingsScreenEvents
    data object LanguageCLick: SettingsScreenEvents
    data class OnLanguageChange(val language: AppLanguage) : SettingsScreenEvents
    data object OnThemeChange : SettingsScreenEvents
    data object OnDismissLanguageDialog : SettingsScreenEvents
}
