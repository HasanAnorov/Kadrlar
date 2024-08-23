package com.ierusalem.kadrlar.features.profile.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.baselineHeight
import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileData
import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileResponse
import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileUser
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenClickIntents
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenState

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    uiState: ProfileResponse,
    eventHandler: (ProfileScreenClickIntents) -> Unit,
    nestedScrollInteropConnection: NestedScrollConnection = rememberNestedScrollInteropConnection()
) {

    val scrollState = rememberScrollState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollInteropConnection)
            .systemBarsPadding()
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                UserInfoFields(uiState, this@BoxWithConstraints.maxHeight)
            }
        }

        val fabExtended by remember { derivedStateOf { scrollState.value == 0 } }
        ProfileFab(
            extended = fabExtended,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                // Offsets the FAB to compensate for CoordinatorLayout collapsing behaviour
                .offset(y = ((-100).dp)),
            onFabClicked = { }
        )
    }
}

@Composable
private fun UserInfoFields(userData: ProfileResponse, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        NameAndPosition(userData)

//        ProfileProperty(stringResource(R.string.display_name), userData.displayName)
//
//        ProfileProperty(stringResource(R.string.status), userData.status)
//
//        ProfileProperty(stringResource(R.string.twitter), userData.twitter, isLink = true)

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun NameAndPosition(
    userData: ProfileResponse
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Name(
            userData.user.profile[0],
            modifier = Modifier.baselineHeight(32.dp)
        )
        Position(
            userData.user,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .baselineHeight(24.dp)
        )
    }
}

@Composable
private fun Name(userData: ProfileData, modifier: Modifier = Modifier) {
    Text(
        text = userData.firstName,
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun Position(userData: ProfileUser, modifier: Modifier = Modifier) {
    Text(
        text = userData.username,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}


@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        HorizontalDivider()
        Text(
            text = label,
            modifier = Modifier.baselineHeight(24.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        val style = if (isLink) {
            MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.typography.bodyLarge
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}

@Composable
fun ProfileError(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.profile_error))
    }
}

@Composable
fun ProfileFab(
    extended: Boolean,
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit = { }
) {
    FloatingActionButton(
        onClick = onFabClicked,
        modifier = modifier
            .padding(16.dp)
            .navigationBarsPadding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Create,
                    contentDescription = stringResource(R.string.edit_profile)
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.edit_profile),
                )
            },
            extended = extended
        )
    }
}