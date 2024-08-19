package com.ierusalem.kadrlar.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.home.domain.Diploma

@Composable
fun CommonFileItem(
    modifier: Modifier = Modifier,
    onSelectFileClick: () -> Unit,
    diploma: Diploma,
    @StringRes label: Int? = null
) {
    Column {
        label?.let {
            Text(
                modifier = Modifier.padding(start =  12.dp, top = 12.dp),
                text = stringResource(id = label),
                style = MaterialTheme.typography.labelMedium,
            )
        }
        FilesContainer(
            modifier = Modifier.padding(horizontal = 12.dp).padding(top = 8.dp),
            files = diploma.files
        )
        Column(
            modifier = modifier
                .padding(12.dp)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .clickable {
                    onSelectFileClick()
                }
                .background(MaterialTheme.colorScheme.onBackground.copy(0.2F)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.padding(top = 8.dp),
                painter = painterResource(id = R.drawable.upload_one),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp),
                text = "Upload a file",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
private fun CommonFilePreviewLight() {
    KadrlarTheme {
        Surface {
            CommonFileItem(
                label = R.string.log_in_to_continue,
                onSelectFileClick = {},
                diploma = Diploma(
                    files = listOf(
                        "Kadrlar.pdf",
                        "Kadrlar.pdf",
                        "Kadrlar.pdf",
                        "Kadrlar.pdf",
                        "Jackson.docs"
                    )
                )
            )
        }
    }
}

@Preview
@Composable
private fun CommonFilePreviewDark() {
    KadrlarTheme(isDarkTheme = true) {
        Surface {
            CommonFileItem(
                onSelectFileClick = {},
                diploma = Diploma(
                    files = listOf(
                        "Kadrlar.pdf",
                        "Kadrlar.pdf",
                        "Kadrlar.pdf",
                        "Kadrlar.pdf",
                        "Jackson.docs"
                    )
                )

            )
        }
    }
}