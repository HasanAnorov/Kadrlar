package com.ierusalem.kadrlar.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun FollowCardSelectable(
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit
) {



    Card(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp).clickable { onClickListener() }
        ) {
            CaseChecker(modifier = Modifier.padding(start = 4.dp))
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier.weight(1F),
                    text = "Enter your gender",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Male",
                    color = MaterialTheme.colorScheme.primary.copy(0.8F),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFollowCardLight() {
    KadrlarTheme {
        Surface {
            FollowCardSelectable(
                onClickListener = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFollowCardDark() {
    KadrlarTheme {
        Surface {
            FollowCardSelectable(
                onClickListener = {}
            )
        }
    }
}