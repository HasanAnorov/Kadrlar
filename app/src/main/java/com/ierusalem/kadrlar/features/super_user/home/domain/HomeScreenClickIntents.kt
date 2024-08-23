package com.ierusalem.kadrlar.features.super_user.home.domain

sealed interface HomeScreenClickIntents {
    data object OnNavIconClicked : HomeScreenClickIntents
    data object OnProfileClicked : HomeScreenClickIntents
    data object OnSettingsClicked : HomeScreenClickIntents
}