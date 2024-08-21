package com.ierusalem.kadrlar.core.app

import com.ierusalem.androchat.core.utils.UiText
import com.ierusalem.kadrlar.R

enum class AppLanguage(val languageRes: UiText, var isSelected: Boolean) {
    Uzbek(languageRes = UiText.StringResource(R.string.uzbek), isSelected =  false),
    English(languageRes = UiText.StringResource(R.string.english),  isSelected = false),
    Russian(languageRes = UiText.StringResource(R.string.russian), isSelected =  true),
}
