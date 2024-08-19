package com.ierusalem.kadrlar.features.home.domain

sealed interface HomeScreenClickIntents {
    data class TabItemClicked(val tabIndex: Int): HomeScreenClickIntents
    data object NavIconClicked: HomeScreenClickIntents
    data object DrawerSupportClick: HomeScreenClickIntents
    data object DrawerSettingClick: HomeScreenClickIntents
    data object OnSearchClick: HomeScreenClickIntents

    data object SelectFile: HomeScreenClickIntents
    data class OnFirstNameChanged(val firstName: String): HomeScreenClickIntents
    data class OnLastNameChanged(val lastName: String): HomeScreenClickIntents
    data class OnPatronymicNameChanged(val patronymicName: String): HomeScreenClickIntents
    data class OnPinflChanged(val pinfl: String): HomeScreenClickIntents
    data class OnPassportSeriesChanged(val passportSeries: String): HomeScreenClickIntents
    data class OnPassportNumberChanged(val passportNumber: String): HomeScreenClickIntents
    data class OnDateChange(val date: String): HomeScreenClickIntents
    data class OnPassportIssuedDateChanged(val passportIssuedDate: String): HomeScreenClickIntents
    data class OnPassportExpirationDateChanged(val passportExpirationDate: String): HomeScreenClickIntents
}