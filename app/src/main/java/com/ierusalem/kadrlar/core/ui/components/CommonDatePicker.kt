package com.ierusalem.kadrlar.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun CommonDatePicker(
    modifier: Modifier = Modifier,
    dateOfBirthday: String,
    onDateChange: (String) -> Unit,
    @StringRes label: Int? = null,
) {
    var showDatePickerDialog by rememberSaveable {
        mutableStateOf(false)
    }
    if (showDatePickerDialog) {
        DatePickerDialog(
            label = label,
            onDismissDialog = {
                showDatePickerDialog = false
            },
            onSnappedDate = {
                onDateChange(it)
                showDatePickerDialog = false
            }
        )
    }
    Column(modifier = modifier) {
        label?.let {
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                text = stringResource(id = label),
                style = MaterialTheme.typography.labelMedium,
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1F),
                style = MaterialTheme.typography.titleMedium,
                text = dateOfBirthday
            )
            IconButton(onClick = { showDatePickerDialog = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_event),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLightCommonDatePicker() {
    KadrlarTheme {
        Surface {
            CommonDatePicker(
                label = R.string.log_in_to_continue,
                dateOfBirthday = "12.02.2001",
                onDateChange = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDarkCommonDatePicker() {
    KadrlarTheme(isDarkTheme = true) {
        Surface {
            CommonDatePicker(
                label = R.string.log_in_to_continue,
                dateOfBirthday = "12.02.2001",
                onDateChange = {}
            )
        }
    }
}
