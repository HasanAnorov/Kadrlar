package com.ierusalem.kadrlar.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun FollowCard(
    modifier: Modifier = Modifier,
    value: String,
    onTextChanged: (String) -> Unit,
    @StringRes label: Int? = null,
    @StringRes placeHolder: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next, // ** Done. Close the keyboard **
        keyboardType = KeyboardType.Text
    )
) {
    Card(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            CaseChecker(modifier = Modifier.padding(start = 4.dp))
            CommonTextFieldWithTopLabel(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 12.dp),
                placeHolder = placeHolder,
                errorMessage = null,
                onTextChanged = onTextChanged,
                keyboardOptions = keyboardOptions,
                value = value,
                label = label
            )
        }
    }
}

@Composable
fun CaseChecker(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(1.dp, Color(0xFF35C47C), CircleShape)
            .size(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background.copy(0.4F)),
        contentAlignment = Alignment.Center,
        content = {
            Image(
                modifier = Modifier.padding(4.dp),
                painter = painterResource(id = R.drawable.check_circle),
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
private fun PreviewFollowCardLight() {
    KadrlarTheme {
        Surface {
            FollowCard(
                label = R.string.app_name,
                placeHolder = R.string.log_in_to_continue,
                onTextChanged = {},
                value = ""
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFollowCardDark() {
    KadrlarTheme {
        Surface {
            FollowCard(
                label = R.string.app_name,
                placeHolder = R.string.log_in_to_continue,
                onTextChanged = {},
                value = ""
            )
        }
    }
}