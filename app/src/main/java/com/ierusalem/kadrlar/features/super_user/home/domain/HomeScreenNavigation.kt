package com.ierusalem.kadrlar.features.super_user.home.domain

sealed interface HomeScreenNavigation {
    data object OnFailure : HomeScreenNavigation
    data object OnSettingsClicked : HomeScreenNavigation
    data object OnProfileClicked : HomeScreenNavigation
}