package com.ierusalem.kadrlar.features.super_user.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Surface {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick() },
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 12.dp, start = 8.dp)
                    .padding(top = 4.dp)
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "E",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 16.sp,
                        text = "Partner Username",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.outline,
                        text = "Last Message",
                        fontSize = 14.sp
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "time",
                        fontSize = 10.sp,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewContactItem() {
    KadrlarTheme {
        UserItem()
    }
}

@Preview
@Composable
private fun PreviewDarkContactItem() {
    KadrlarTheme(isDarkTheme = true) {
        UserItem()
    }
}
