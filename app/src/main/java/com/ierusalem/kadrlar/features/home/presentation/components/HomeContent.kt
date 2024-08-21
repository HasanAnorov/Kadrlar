package com.ierusalem.kadrlar.features.home.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.FollowCard
import com.ierusalem.kadrlar.core.ui.components.FollowCardDate
import com.ierusalem.kadrlar.core.ui.components.FollowCardFile
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.home.domain.HomeScreenClickIntents
import com.ierusalem.kadrlar.features.home.domain.HomeScreenState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState,
    eventHandler: (HomeScreenClickIntents) -> Unit
) {
    Surface {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    label = R.string.firstname_description,
                    placeHolder = R.string.firstname,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnFirstNameChanged(it))
                    },
                    value = uiState.firstName
                )
            }
            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.lastname_description,
                    placeHolder = R.string.lastname,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnLastNameChanged(it))
                    },
                    value = uiState.lastName
                )
            }
            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.patronymic_description,
                    placeHolder = R.string.patronymic,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPatronymicNameChanged(it))
                    },
                    value = uiState.patronymicName
                )
            }
            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.pinfl_description,
                    placeHolder = R.string.pinfl,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPinflChanged(it))
                    },
                    value = uiState.pinfl,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next, // ** Done. Close the keyboard **
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.passport_series_description,
                    placeHolder = R.string.passport_series,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPassportSeriesChanged(it))
                    },
                    value = uiState.passportSeries,
                )
            }
            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.passport_number_description,
                    placeHolder = R.string.passport_number,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPassportNumberChanged(it))
                    },
                    value = uiState.passportNumber,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next, // ** Done. Close the keyboard **
                        keyboardType = KeyboardType.Number
                    )
                )
            }

            //passport issued date
            item {
                FollowCardDate(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    dateOfBirthday = uiState.passportIssuedDate,
                    label = R.string.passport_issued_date_description,
                    onDateChange = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPassportIssuedDateChanged(it))
                    }
                )
            }

            //passport expiration date
            item {
                FollowCardDate(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    dateOfBirthday = uiState.passportExpirationDate,
                    label = R.string.passport_expiration_date,
                    onDateChange = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPassportExpirationDateChanged(it))
                    }
                )
            }
            item{
                FollowCardFile(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    onSelectFileClick = {
                        eventHandler.invoke(HomeScreenClickIntents.SelectFile)
                    },
                    label = R.string.passport_copy_description,
                    fileName = uiState.passportPdf
//                    diploma = diploma
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.nationality_description,
                    placeHolder = R.string.nationality,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnNationalityChanged(it))
                    },
                    value = uiState.nationality
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.citizenship_description,
                    placeHolder = R.string.citizenship,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnCitizenshipChanged(it))
                    },
                    value = uiState.citizenship
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.partisanship_description,
                    placeHolder = R.string.partisanship,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPartisanshipChanged(it))
                    },
                    value = uiState.partisanship
                )
            }

            item {
                FollowCardDate(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    dateOfBirthday = uiState.dateOfBirthday,
                    label = R.string.birthday_description,
                    onDateChange = {
                        eventHandler.invoke(HomeScreenClickIntents.OnBirthdayChanged(it))
                    }
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.gender_description,
                    placeHolder = R.string.gender,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnGenderChanged(it))
                    },
                    value = uiState.gender
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.phone_number_description,
                    placeHolder = R.string.phone_number,
                    onTextChanged = {
                        eventHandler.invoke(HomeScreenClickIntents.OnPhoneNumberChanged(it))
                    },
                    value = uiState.phoneNumber,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next, // ** Done. Close the keyboard **
                        keyboardType = KeyboardType.Number
                    )
                )
            }

        }
    }
}

@Preview
@Composable
private fun PreviewHomeContentLight() {
    KadrlarTheme(isDarkTheme = false) {
        HomeContent(
            uiState = HomeScreenState(),
            eventHandler = {}
        )
    }
}

@Preview
@Composable
private fun PreviewHomeContentDark() {
    KadrlarTheme(isDarkTheme = true) {
        HomeContent(
            uiState = HomeScreenState(),
            eventHandler = {}
        )
    }
}