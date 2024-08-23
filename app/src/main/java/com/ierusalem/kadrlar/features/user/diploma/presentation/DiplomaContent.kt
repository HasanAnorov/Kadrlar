package com.ierusalem.kadrlar.features.user.diploma.presentation

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
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaScreenClickIntents
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaScreenState

@Composable
fun DiplomaContent(
    modifier: Modifier = Modifier,
    uiState: DiplomaScreenState,
    eventHandler: (DiplomaScreenClickIntents) -> Unit,
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
                    label = R.string.enter_diploma_type,
                    placeHolder = R.string.diploma_type,
                    onTextChanged = {
                        eventHandler.invoke(DiplomaScreenClickIntents.OnDiplomaTypeChanged(it))
                    },
                    value = uiState.diplomaType
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.enter_diploma_specification,
                    placeHolder = R.string.diploma_speciality,
                    onTextChanged = {
                        eventHandler.invoke(DiplomaScreenClickIntents.OnDiplomaSpecificationChanged(it))
                    },
                    value = uiState.diplomaSpecification
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.diploma_series_description,
                    placeHolder = R.string.diploma_series,
                    onTextChanged = {
                        eventHandler.invoke(DiplomaScreenClickIntents.OnDiplomaSeriesChanged(it))
                    },
                    value = uiState.diplomaSeries,
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.diploma_number_description,
                    placeHolder = R.string.diploma_number,
                    onTextChanged = {
                        eventHandler.invoke(DiplomaScreenClickIntents.OnDiplomaNumberChanged(it))
                    },
                    value = uiState.diplomaNumber,
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
                    dateOfBirthday = uiState.graduationDate,
                    label = R.string.graduation_date_description,
                    onDateChange = {
                        eventHandler.invoke(DiplomaScreenClickIntents.OnGraduationDateChanged(it))
                    }
                )
            }

            item {
                FollowCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 16.dp),
                    label = R.string.graduated_university_description,
                    placeHolder = R.string.graduated_university,
                    onTextChanged = {
                        eventHandler.invoke(DiplomaScreenClickIntents.OnGraduatedUniversityChanged(it))
                    },
                    value = uiState.graduatedUniversity,
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewLight() {
    KadrlarTheme {
        DiplomaContent(
            uiState = DiplomaScreenState(),
            eventHandler = {}
        )
    }
}

@Preview
@Composable
private fun PreviewDark() {
    KadrlarTheme(isDarkTheme = true) {
        DiplomaContent(
            uiState = DiplomaScreenState(),
            eventHandler = {}
        )
    }
}
