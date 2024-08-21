package com.ierusalem.kadrlar.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.home.domain.Diploma

@Composable
fun FollowCardFile(
    modifier: Modifier = Modifier,
    onSelectFileClick: () -> Unit,
    diploma: Diploma,
    @StringRes label: Int? = null,
) {
    Card(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CaseChecker(modifier = Modifier.padding(start = 8.dp))
            CommonFileItem(
                label = label,
                onSelectFileClick = onSelectFileClick,
                diploma = diploma
            )
        }
    }
}

@Composable
fun FilesContainer(modifier: Modifier = Modifier, files: List<String>) {
    LazyColumn(modifier = modifier.height(100.dp)) {
        itemsIndexed(files) { index, item ->
            FileItem(fileName = item)
            if (index != files.lastIndex) {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFileItem() {
    KadrlarTheme {
        FileItem(
            fileName = "Kadrlar.pdf"
        )
    }
}

@Composable
fun FileItem(modifier: Modifier = Modifier, fileName: String) {
    Surface(
        modifier = modifier.height(IntrinsicSize.Max),
        color = MaterialTheme.colorScheme.background.copy(0.8F),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.file_text),
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(start = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = fileName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFollowCardFileLight() {
    KadrlarTheme {
        Surface {
            FollowCardFile(
                label = R.string.app_name,
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
private fun PreviewFollowCardFileDark() {
    KadrlarTheme {
        Surface {
            FollowCardFile(
                label = R.string.app_name,
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