package com.ierusalem.kadrlar.features.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.androchat.core.utils.UiText
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.AppBar
import com.ierusalem.kadrlar.core.ui.components.LanguageDialog
import com.ierusalem.kadrlar.core.ui.components.ThemeSwitcher
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.settings.domain.SettingsScreenEvents
import com.ierusalem.kadrlar.features.settings.domain.SettingsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    uiState: SettingsState,
    eventHandler: (SettingsScreenEvents) -> Unit,
) {
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(
                modifier = modifier,
                scrollBehavior = scrollBehavior,
                onNavIconPressed = { eventHandler(SettingsScreenEvents.NavIconClick) },
                title = {
                    Text(
                        text = stringResource(R.string.settings),
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navIcon = Icons.AutoMirrored.Filled.ArrowBack
            )
        },
        // Exclude ime and navigation bar padding so this can be added by the UserInput composable
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime)
    ) { paddingValues ->
        if (uiState.languageDialogVisibility) {
            LanguageDialog(
                onDismissDialog = {
                    eventHandler(SettingsScreenEvents.OnDismissLanguageDialog)
                },
                languages = uiState.languagesList,
                onLanguageSelected = {
                    eventHandler(SettingsScreenEvents.OnLanguageChange(it))
                },
                selectedLanguage = uiState.selectedLanguage
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            content = {
                GeneralOptionsUI(
                    eventHandler = eventHandler,
                    uiState = uiState
                )
                LogoutUi()
            }
        )
    }
}

@Composable
fun LogoutUi() {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceDim
        ),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp,
                        horizontal = 14.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.error,
                    text = stringResource(R.string.logout),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.log_out),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Composable
fun GeneralOptionsUI(eventHandler: (SettingsScreenEvents) -> Unit, uiState: SettingsState) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.general),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        GeneralSettingsItem(
            modifier = Modifier.padding(top = 1.dp),
            iconStart = R.drawable.language,
            iconEnd = null,
            mainText = stringResource(R.string.language),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            subText = uiState.selectedLanguage.languageRes,
            onClick = {
                eventHandler(SettingsScreenEvents.LanguageCLick)
            }
        )
        GeneralSettingsItemX(
            modifier = Modifier.padding(top = 1.dp),
            iconStart = R.drawable.color_palette,
            mainText = stringResource(R.string.app_theme),
            onClick = {
                eventHandler(SettingsScreenEvents.OnThemeChange)
            },
            isSystemInDarkMode = uiState.appTheme,
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        )
    }
}

@Composable
fun GeneralSettingsItem(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
    iconStart: Int? = null,
    iconEnd: Int? = null,
    mainText: String,
    subText: UiText? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = shape,
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp,
                        horizontal = 14.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1F),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    iconStart?.let {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(shape = ShapeDefaults.Medium)
                                .background(color = MaterialTheme.colorScheme.background.copy(0.6F))
                        ) {
                            Icon(
                                painter = painterResource(id = iconStart),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = textModifier.weight(1F),
                            color = textColor,
                            text = mainText,
                            style = MaterialTheme.typography.titleMedium
                        )
                        subText?.let {
                            Text(
                                text = subText.asString(),
                                color = MaterialTheme.colorScheme.primary.copy(0.8F),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }
                iconEnd?.let {
                    Icon(
                        painter = painterResource(id = iconEnd),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun GeneralSettingsItemX(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
    iconStart: Int? = null,
    mainText: String,
    isSystemInDarkMode: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = shape,
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp,
                        horizontal = 14.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1F),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    iconStart?.let {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(shape = ShapeDefaults.Medium)
                                .background(color = MaterialTheme.colorScheme.background.copy(0.6F))
                        ) {
                            Icon(
                                painter = painterResource(id = iconStart),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = textModifier.weight(1F),
                            color = textColor,
                            text = mainText,
                            style = MaterialTheme.typography.titleMedium
                        )
                        ThemeSwitcher(
                            darkTheme = isSystemInDarkMode,
                            size = 32.dp,
                            padding = 5.dp,
                            onClick = { onClick() }
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun SettingsScreenPreviewLight() {
    KadrlarTheme {
        SettingsScreen(
            modifier = Modifier,
            uiState = SettingsState(),
            eventHandler = {}
        )
    }
}

@Preview
@Composable
private fun SettingsScreenPreviewDark() {
    KadrlarTheme(isDarkTheme = true) {
        SettingsScreen(
            modifier = Modifier,
            uiState = SettingsState(),
            eventHandler = {}
        )
    }
}