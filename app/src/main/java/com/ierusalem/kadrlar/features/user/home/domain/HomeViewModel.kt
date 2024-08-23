package com.ierusalem.kadrlar.features.user.home.domain

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.androchat.core.utils.UiText
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.connectivity.ConnectivityObserver
import com.ierusalem.kadrlar.core.utils.log
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    connectivityObserver: ConnectivityObserver
) : ViewModel(),
    NavigationEventDelegate<HomeScreenNavigation> by DefaultNavigationEventDelegate() {

    init {
        connectivityObserver.observe().onEach { connectivityStatus ->
            when (connectivityStatus) {
                ConnectivityObserver.Status.Available -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connected)
                        )
                    }
                }

                ConnectivityObserver.Status.Loosing -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connectivity_loosing)
                        )
                    }
                }

                ConnectivityObserver.Status.Lost -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connectivity_lost)
                        )
                    }
                }

                ConnectivityObserver.Status.Unavailable -> {
                    _state.update {
                        it.copy(
                            connectivityStatus = UiText.StringResource(R.string.connectivity_unavailable)
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState()
    )
    val state = _state.asStateFlow()

    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    private fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    fun handleClickIntents(intent: HomeScreenClickIntents) {
        when (intent) {

            HomeScreenClickIntents.DrawerProfileClick -> {
                emitNavigation(HomeScreenNavigation.NavigateToProfile)
            }

            is HomeScreenClickIntents.OnNationalityChanged -> {
                _state.update {
                    it.copy(
                        nationality = intent.nationality
                    )
                }
            }

            is HomeScreenClickIntents.OnCitizenshipChanged -> {
                _state.update {
                    it.copy(
                        citizenship = intent.citizenship
                    )
                }
            }

            is HomeScreenClickIntents.OnPartisanshipChanged -> {
                _state.update {
                    it.copy(
                        partisanship = intent.partisanship
                    )
                }
            }

            is HomeScreenClickIntents.OnGenderChanged -> {
                _state.update {
                    it.copy(
                        gender = intent.gender
                    )
                }
            }

            is HomeScreenClickIntents.OnPhoneNumberChanged -> {
                _state.update {
                    it.copy(
                        phoneNumber = intent.phoneNumber
                    )
                }
            }

            is HomeScreenClickIntents.OnFilesAdded -> {
                log("file name - ${intent.fileName}")
                _state.update {
                    it.copy(
                        passportPdf = intent.fileName
                    )
                }
            }

            is HomeScreenClickIntents.OnPassportNumberChanged -> {
                _state.update {
                    it.copy(
                        passportNumber = intent.passportNumber
                    )
                }
            }

            is HomeScreenClickIntents.OnPassportIssuedDateChanged -> {
                _state.update {
                    it.copy(
                        passportIssuedDate = intent.passportIssuedDate
                    )
                }
            }

            is HomeScreenClickIntents.OnPassportExpirationDateChanged -> {
                _state.update {
                    it.copy(
                        passportExpirationDate = intent.passportExpirationDate
                    )
                }
            }

            is HomeScreenClickIntents.OnBirthdayChanged -> {
                _state.update {
                    it.copy(
                        dateOfBirthday = intent.date
                    )
                }
            }

            is HomeScreenClickIntents.OnPassportSeriesChanged -> {
                _state.update {
                    it.copy(
                        passportSeries = intent.passportSeries
                    )
                }
            }

            is HomeScreenClickIntents.OnFirstNameChanged -> {
                _state.update {
                    it.copy(
                        firstName = intent.firstName
                    )
                }
            }

            is HomeScreenClickIntents.OnLastNameChanged -> {
                _state.update {
                    it.copy(
                        lastName = intent.lastName
                    )
                }
            }

            is HomeScreenClickIntents.OnPatronymicNameChanged -> {
                _state.update {
                    it.copy(
                        patronymicName = intent.patronymicName
                    )
                }
            }

            is HomeScreenClickIntents.OnPinflChanged -> {
                _state.update {
                    it.copy(
                        pinfl = intent.pinfl
                    )
                }
            }

            HomeScreenClickIntents.SelectFile -> {
                emitNavigation(HomeScreenNavigation.SelectFile)
            }

            HomeScreenClickIntents.DrawerSettingClick -> {
                emitNavigation(HomeScreenNavigation.NavigateToSettings)
            }

            HomeScreenClickIntents.DrawerSupportClick -> {
                emitNavigation(HomeScreenNavigation.NavigateToSupport)
            }

            HomeScreenClickIntents.AddDiplomaClick -> {
                emitNavigation(HomeScreenNavigation.NavigateToDiploma)
            }

            HomeScreenClickIntents.NavIconClicked -> {
                openDrawer()
            }

        }
    }

    fun addDiploma(diploma: DiplomaScreenState){
        _state.update {
            it.copy(
                diplomas = it.diplomas.plus(diploma)
            )
        }
    }

}

@Immutable
data class HomeScreenState(
    //connectivity
    val connectivityStatus: UiText = UiText.StringResource(R.string.connectivity_unavailable),

    //home content
    val firstName: String = "",
    val lastName: String = "",
    val patronymicName: String = "",

    val pinfl: String = "",

    val passportSeries: String = "",
    val passportNumber: String = "",

    val passportIssuedDate: String = "2000-01-01",
    val passportExpirationDate: String = "2000-01-01",

    //passport pdf
    val passportPdf: String? = null,

    //profile
    val nationality: String = "Uzbek",
    val citizenship: String = "Uzbek",
    val partisanship: String = "Uzbek",

    val dateOfBirthday: String = "2000-01-01",

    val gender: String = "Erkak",
    val phoneNumber: String = "+998",
    val diplomas: List<DiplomaScreenState> = emptyList()
)