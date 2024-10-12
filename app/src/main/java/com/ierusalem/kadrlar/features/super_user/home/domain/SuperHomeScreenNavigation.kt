package com.ierusalem.kadrlar.features.super_user.home.domain

sealed interface SuperHomeScreenNavigation {
    data object OnFailure : SuperHomeScreenNavigation
    data object OnSettingsClicked : SuperHomeScreenNavigation
    data object OnProfileClicked : SuperHomeScreenNavigation
}