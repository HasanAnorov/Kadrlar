package com.ierusalem.kadrlar.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun CommonTextFieldWithTopLabel(
    modifier: Modifier = Modifier,
    value: String,
    errorMessage: String? = null,
    onTextChanged: (String) -> Unit,
    @StringRes placeHolder: Int? = null,
    @StringRes label: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next, // ** Done. Close the keyboard **
        keyboardType = KeyboardType.Text
    )
) {
    Column(modifier = modifier) {
        label?.let {
            Text(
                modifier = Modifier,
                text = stringResource(id = label),
                style = MaterialTheme.typography.labelMedium,
            )
        }
        TextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            value = value,
            textStyle = MaterialTheme.typography.titleMedium,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.background.copy(0.7F),
                unfocusedContainerColor = MaterialTheme.colorScheme.background.copy(0.7F)
            ),
            placeholder = {
                placeHolder?.let {
                    Text(
                        text = stringResource(id = placeHolder),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            onValueChange = {
                onTextChanged(it)
            },
            keyboardOptions = keyboardOptions,
            shape = RoundedCornerShape(size = 12.dp),
            singleLine = true,
        )
        errorMessage?.let {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Red.copy(0.8F)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLightCommonTextFieldWithTopLabel() {
    KadrlarTheme {
        Surface {
            CommonTextFieldWithTopLabel(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                placeHolder = R.string.log_in_to_continue,
                errorMessage = "Login should be at least 3 characters",
                onTextChanged = {},
                value = "",
                label = R.string.log_in_to_continue
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDarkCommonTextFieldWithTopLabel() {
    KadrlarTheme(isDarkTheme = true) {
        Surface {
            CommonTextFieldWithTopLabel(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                placeHolder = R.string.log_in_to_continue,
                errorMessage = "Login should be at least 3 characters",
                onTextChanged = {},
                value = "",
                label = R.string.log_in_to_continue
            )
        }
    }
}