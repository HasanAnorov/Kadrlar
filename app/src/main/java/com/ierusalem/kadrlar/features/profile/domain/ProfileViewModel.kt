package com.ierusalem.kadrlar.features.profile.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.utils.Resource
import com.ierusalem.kadrlar.core.preferences.DataStorePreferenceRepository
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import com.ierusalem.kadrlar.core.utils.Constants
import com.ierusalem.kadrlar.core.utils.log
import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStorePreferenceRepository: DataStorePreferenceRepository,
    private val repository: ProfileRepository
) : ViewModel(),
    NavigationEventDelegate<ProfileScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState()
    )
    val state = _state.asStateFlow()

    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch {

            //repository.getProfile(dataStorePreferenceRepository.getAccessToken.first()).let {
            repository.getProfile(Constants.TEMP_TOKEN).let {
                if (it.isSuccessful) {
                    val profileResponse = it.body()!!
                    log("profile response : $profileResponse")
                    _state.update { uiState ->
                        uiState.copy(
                            profileScreen = Resource.Success(profileResponse)
                        )
                    }
                } else {
                    //emitNavigation(ProfileScreenNavigation.InvalidResponse)
                    _state.update { uiState ->
                        uiState.copy(
                            profileScreen = Resource.Failure(it.message())
                        )
                    }
                }
            }
        }
    }

    fun handleClickIntents(intent: ProfileScreenClickIntents) {
        when (intent) {
            ProfileScreenClickIntents.OnNavIconClicked -> {
                emitNavigation(ProfileScreenNavigation.OnNavIconClicked)
            }
            ProfileScreenClickIntents.OnEditProfileClicked -> {

            }
            is ProfileScreenClickIntents.OnDownloadFileClicked -> {

            }
        }
    }
}

data class ProfileScreenState(
    val profileScreen: Resource<ProfileResponse> = Resource.Loading()
)