package com.ierusalem.kadrlar.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun FollowCardDate(
    modifier: Modifier = Modifier,
    dateOfBirthday:String,
    onDateChange: (String) -> Unit,
    @StringRes label: Int? = null,
) {
    Card(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CaseChecker(modifier = Modifier.padding(start = 8.dp))
            CommonDatePicker(
                modifier = Modifier.padding(horizontal = 16.dp),
                label = label,
                dateOfBirthday = dateOfBirthday,
                onDateChange = onDateChange
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFollowCardFileLight() {
    KadrlarTheme {
        Surface {
            FollowCardDate(
                label = R.string.app_name,
                dateOfBirthday = "12.02.2001",
                onDateChange = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFollowCardFileDark() {
    KadrlarTheme {
        Surface {
            FollowCardDate(
                label = R.string.app_name,
                dateOfBirthday = "12.02.2001",
                onDateChange = {}
            )
        }
    }
}