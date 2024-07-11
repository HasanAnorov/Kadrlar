package com.ierusalem.kadrlar.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun CommonPasswordTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    errorMessage: String? = null,
    passwordVisibility: Boolean = false,
    onPasswordVisibilityChanged: () -> Unit,
    onPasswordTextChanged: (String) -> Unit
) {
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            textStyle = MaterialTheme.typography.titleMedium,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = onPasswordVisibilityChanged,
                    content = {
                        if (passwordVisibility) {
                            Icon(imageVector = Icons.Default.Visibility, contentDescription = null)
                        } else {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }

                    }
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            onValueChange = { onPasswordTextChanged(it) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next, // ** Done. Close the keyboard **
                keyboardType = KeyboardType.Password
            ),
            shape = RoundedCornerShape(size = 12.dp),
            singleLine = true,
        )
        errorMessage?.let{
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Red.copy(0.8F)
            )
        }
    }
}

@Preview
@Composable
fun CommonPasswordTextFieldPreview() {
    KadrlarTheme {
        CommonPasswordTextField(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            label = "Username",
            value = "hasn",
            errorMessage = "Login should be at least 3 characters",
            passwordVisibility = false,
            onPasswordVisibilityChanged = {},
            onPasswordTextChanged = {}
        )
    }
}

@Preview
@Composable
fun CommonPasswordTextFieldDarkPreview() {
    KadrlarTheme(isDarkTheme = true) {
        CommonPasswordTextField(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            label = "Username",
            value = "hasan",
            errorMessage = "Login should be at least 3 characters",
            passwordVisibility = true,
            onPasswordVisibilityChanged = {},
            onPasswordTextChanged = {}
        )
    }
}