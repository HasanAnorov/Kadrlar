package com.ierusalem.kadrlar.features.super_user.home.domain

sealed interface SuperHomeScreenClickIntents {
    data object OnNavIconClicked : SuperHomeScreenClickIntents
    data object OnProfileClicked : SuperHomeScreenClickIntents
    data object OnSettingsClicked : SuperHomeScreenClickIntents
}