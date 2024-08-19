package com.ierusalem.kadrlar.features.home.domain

sealed interface HomeScreenNavigation {
    data object NavigateToSettings: HomeScreenNavigation
    data object NavigateToSupport: HomeScreenNavigation
    data object SelectFile: HomeScreenNavigation
}