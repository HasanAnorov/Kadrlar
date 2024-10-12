package com.ierusalem.kadrlar.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.settings.data.PreviewSettings
import com.ierusalem.kadrlar.features.super_user.home.domain.SuperHomeScreenClickIntents
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun KadrlarHostDrawerContent(
    onDrawerItemClick: (SuperHomeScreenClickIntents) -> Unit
) {
    // Use windowInsetsTopHeight() to add a spacer which pushes the drawer content
    // below the status bar (y-axis)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        DrawerHeader()
        DividerItem()
        Spacer(modifier = Modifier.height(2.dp))
        ChatItem(
            text = stringResource(id = R.string.profile),
            image = rememberVectorPainter(image = Icons.Default.Person),
            onChatClicked = { onDrawerItemClick(SuperHomeScreenClickIntents.OnProfileClicked) }
        )
        ChatItem(
            text = stringResource(id = R.string.settings),
            image = painterResource(id = R.drawable.settings_sharp),
            onChatClicked = { onDrawerItemClick(SuperHomeScreenClickIntents.OnSettingsClicked) }
        )
    }
}

@Composable
private fun DrawerHeader() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 12.dp)
    ) {
        GlideImage(
            imageModel = { PreviewSettings.AVATAR_URL },
            failure = {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "H")
                }
            },
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            imageOptions = ImageOptions(
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
        ) {
            Text(
                text = "Hasan Anorov",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "ierusalem",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
private fun ChatItem(
    text: String,
    image: Painter,
    onChatClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = onChatClicked),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            painter = image,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            contentDescription = null
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}



@Composable
@Preview
fun DrawerPreviewHost() {
    KadrlarTheme {
        Surface {
            Column {
                KadrlarDrawerContent {}
            }
        }
    }
}

@Composable
@Preview
fun DrawerPreviewDarkHost() {
    KadrlarTheme(isDarkTheme = true) {
        Surface {
            Column {
                KadrlarDrawerContent {}
            }
        }
    }
}
