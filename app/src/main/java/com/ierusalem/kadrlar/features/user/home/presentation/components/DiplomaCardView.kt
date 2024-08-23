package com.ierusalem.kadrlar.features.user.home.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.CaseChecker
import com.ierusalem.kadrlar.core.ui.components.FileItem
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaScreenState

@Composable
fun DiplomaCardView(
    modifier: Modifier = Modifier,
    @StringRes label: Int? = R.string.diploma,
    diplomas: List<DiplomaScreenState> = emptyList(),
    onAddDiplomaClick: () -> Unit
) {
    Card(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CaseChecker(modifier = Modifier.padding(start = 8.dp))
            Column {
                label?.let {
                    Text(
                        modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                        text = stringResource(id = label),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                if (diplomas.isNotEmpty()) {
                    DiplomaContainer(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 8.dp),
                        files = diplomas.map { diploma -> diploma.diplomaType }
                    )
                }
                Column(
                    modifier = modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth()
                        .clickable {
                            onAddDiplomaClick()
                        }
                        .background(MaterialTheme.colorScheme.onBackground.copy(0.2F)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.padding(top = 8.dp),
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 12.dp),
                        text = stringResource(id = R.string.add_diploma),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun DiplomaContainer(modifier: Modifier = Modifier, files: List<String>) {
    LazyColumn(modifier = modifier.heightIn(min = 0.dp, 200.dp)) {
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
private fun Preview_Light() {
    KadrlarTheme {
        DiplomaCardView(
            label = R.string.diploma,
            diplomas = emptyList(),
            onAddDiplomaClick = {}
        )
    }
}

@Preview
@Composable
private fun Preview_Dark() {
    KadrlarTheme(isDarkTheme = true) {
        DiplomaCardView(
            label = R.string.diploma,
            diplomas = listOf(
                DiplomaScreenState()
            ),
            onAddDiplomaClick = {},
        )
    }
}