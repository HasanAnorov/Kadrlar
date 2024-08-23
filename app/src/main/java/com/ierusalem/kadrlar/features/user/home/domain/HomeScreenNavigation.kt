package com.ierusalem.kadrlar.features.user.home.domain

sealed interface HomeScreenNavigation {
    data object NavigateToSettings: HomeScreenNavigation
    data object NavigateToProfile: HomeScreenNavigation
    data object NavigateToSupport: HomeScreenNavigation
    data object SelectFile: HomeScreenNavigation
    data object NavigateToDiploma: HomeScreenNavigation
}