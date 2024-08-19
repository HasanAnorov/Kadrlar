package com.ierusalem.kadrlar.features.login.domain

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.preferences.DataStorePreferenceRepository
import com.ierusalem.kadrlar.core.utils.FieldValidator
import com.ierusalem.kadrlar.core.utils.log
import com.ierusalem.kadrlar.features.login.data.models.UserLoginRequest
import com.ierusalem.kadrlar.features.login.presentation.LoginFormEvents
import com.ierusalem.kadrlar.features.login.presentation.LoginNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validator: FieldValidator,
    private val dataStorePreferenceRepository: DataStorePreferenceRepository,
    private val repository: LoginRepository
) : ViewModel(), DefaultLifecycleObserver,
    NavigationEventDelegate<LoginNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<LoginScreenState> = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("ahi3646", " coroutineExceptionHandler : error - $exception ")
        emitNavigation(LoginNavigation.InvalidResponse)
    }

    fun handleEvents(event: LoginFormEvents) {
        when (event) {
            LoginFormEvents.Login -> loginUser()
            is LoginFormEvents.UsernameChanged -> {
                _state.update {
                    it.copy(
                        username = event.username
                    )
                }
            }

            is LoginFormEvents.PasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            LoginFormEvents.PasswordVisibilityChanged -> {
                _state.update {
                    it.copy(
                        passwordVisibility = !state.value.passwordVisibility
                    )
                }
            }
        }
    }

    private fun loginUser() {
        val usernameResult = validator.validateUsername(state.value.username)
        val passwordResult = validator.validatePassword(state.value.password)

        val hasError = listOf(
            usernameResult,
            passwordResult,
        ).any {
            !it.successful
        }

        if (hasError) {
            _state.update {
                it.copy(
                    usernameError = usernameResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                )
            }
            return
        }
        _state.update {
            it.copy(
                usernameError = null,
                passwordError = null,
            )
        }
        viewModelScope.launch(handler) {
            val userLoginRequest = UserLoginRequest(
                username = state.value.username,
                password = state.value.password
            )
            repository.loginUser(userLoginRequest).let {
                if (it.isSuccessful) {
                    val userLoginResponse = it.body()!!
                    log("loginUser: \naccess token ${userLoginResponse.access} \nrefresh token ${userLoginResponse.refresh}")
                    dataStorePreferenceRepository.saveAccessToken(userLoginResponse.access)
                    dataStorePreferenceRepository.saveRefreshToken(userLoginResponse.refresh)

                    emitNavigation(LoginNavigation.ToHome)
                } else {
                    emitNavigation(LoginNavigation.InvalidResponse)
                }
            }
        }
    }

}

@Immutable
data class LoginScreenState(
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val passwordVisibility: Boolean = false
)