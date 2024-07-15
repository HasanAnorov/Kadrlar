package com.ierusalem.kadrlar.features.home.domain

sealed interface HomeScreenNavigation {
    data object NavigateToSettings: HomeScreenNavigation
}