package com.ierusalem.kadrlar.features.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.features.login.domain.LoginFormEvents
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.CommonPasswordTextField
import com.ierusalem.kadrlar.core.ui.components.CommonTextFieldWithError
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.login.domain.LoginScreenState

@Composable
fun LoginUiScreen(
    state: LoginScreenState,
    intentReducer: (LoginFormEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        content = {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.welcome_back),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayLarge,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                text = stringResource(R.string.log_in_to_continue),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            CommonTextFieldWithError(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                placeHolder = stringResource(id = R.string.username),
                value = state.username,
                errorMessage = state.usernameError,
                onTextChanged = {
                    intentReducer(LoginFormEvents.UsernameChanged(it))
                }
            )

            CommonPasswordTextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
                label = stringResource(id = R.string.password),
                value = state.password,
                passwordVisibility = state.passwordVisibility,
                errorMessage = state.passwordError,
                onPasswordVisibilityChanged = {
                    intentReducer(LoginFormEvents.PasswordVisibilityChanged)
                },
                onPasswordTextChanged = {
                    intentReducer(LoginFormEvents.PasswordChanged(it))
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.primary)
                    .clickable { intentReducer(LoginFormEvents.Login) },
                content = {
                    Text(
                        text = stringResource(R.string.login),
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 16.dp
                            )
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                },
            )
        }
    )
}

@Preview
@Composable
private fun LoginPreviewLight() {
    KadrlarTheme(isDarkTheme = false) {
        LoginUiScreen(
            state = LoginScreenState(),
            intentReducer = {}
        )
    }
}

@Preview
@Composable
private fun LoginPreviewDark() {
    KadrlarTheme(isDarkTheme = true) {
        LoginUiScreen(
            state = LoginScreenState(),
            intentReducer = {}
        )
    }
}
