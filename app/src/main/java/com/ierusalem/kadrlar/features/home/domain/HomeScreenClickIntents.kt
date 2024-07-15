package com.ierusalem.kadrlar.features.home.domain

sealed interface HomeScreenClickIntents {
    data class TabItemClicked(val tabIndex: Int): HomeScreenClickIntents
    data object NavIconClicked: HomeScreenClickIntents
    data object DrawerSettingClick: HomeScreenClickIntents
    data object OnSearchClick: HomeScreenClickIntents
}