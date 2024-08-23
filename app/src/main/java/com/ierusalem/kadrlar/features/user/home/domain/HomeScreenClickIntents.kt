package com.ierusalem.kadrlar.features.user.home.domain

sealed interface HomeScreenClickIntents {
    data object NavIconClicked: HomeScreenClickIntents
    data object DrawerSupportClick: HomeScreenClickIntents
    data object DrawerProfileClick: HomeScreenClickIntents
    data object DrawerSettingClick: HomeScreenClickIntents

    data class OnFilesAdded(val fileName:String): HomeScreenClickIntents

    data object SelectFile: HomeScreenClickIntents
    data class OnFirstNameChanged(val firstName: String): HomeScreenClickIntents
    data class OnLastNameChanged(val lastName: String): HomeScreenClickIntents
    data class OnPatronymicNameChanged(val patronymicName: String): HomeScreenClickIntents
    data class OnPinflChanged(val pinfl: String): HomeScreenClickIntents
    data class OnPassportSeriesChanged(val passportSeries: String): HomeScreenClickIntents
    data class OnPassportNumberChanged(val passportNumber: String): HomeScreenClickIntents

    data class OnPassportIssuedDateChanged(val passportIssuedDate: String): HomeScreenClickIntents
    data class OnPassportExpirationDateChanged(val passportExpirationDate: String):
        HomeScreenClickIntents

    data class OnNationalityChanged(val nationality: String): HomeScreenClickIntents
    data class OnCitizenshipChanged(val citizenship: String): HomeScreenClickIntents
    data class OnPartisanshipChanged(val partisanship: String): HomeScreenClickIntents
    data class OnGenderChanged(val gender: String): HomeScreenClickIntents
    data class OnPhoneNumberChanged(val phoneNumber: String): HomeScreenClickIntents

    data class OnBirthdayChanged(val date: String): HomeScreenClickIntents

    data object AddDiplomaClick: HomeScreenClickIntents

}