package com.ierusalem.kadrlar.features.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.baselineHeight
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.profile.data.models.response.Diplomlar
import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileData
import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileResponse
import com.ierusalem.kadrlar.features.profile.data.models.response.ProfileUser
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenClickIntents

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    uiState: ProfileResponse,
    eventHandler: (ProfileScreenClickIntents) -> Unit,
    nestedScrollInteropConnection: NestedScrollConnection = rememberNestedScrollInteropConnection()
) {

    val scrollState = rememberScrollState()

    BoxWithConstraints(
        modifier = modifier
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
                val user = uiState.user.profile[0]
                UserInfoFields(
                    userData = user,
                    containerHeight = this@BoxWithConstraints.maxHeight,
                    eventHandler = eventHandler
                )
            }
        }

        val fabExtended by remember { derivedStateOf { scrollState.value == 0 } }
        ProfileFab(
            extended = fabExtended,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                // Offsets the FAB to compensate for CoordinatorLayout collapsing behaviour
                .offset(y = ((-100).dp)),
            onFabClicked = {
                eventHandler(ProfileScreenClickIntents.OnEditProfileClicked)
            }
        )
    }
}

@Composable
private fun UserInfoFields(
    userData: ProfileData,
    containerHeight: Dp,
    eventHandler: (ProfileScreenClickIntents) -> Unit,
) {

    Column {
        Spacer(modifier = Modifier.height(8.dp))

        NameAndPosition(userData)

//        ProfileProperty(stringResource(R.string.pinfl), userData.jshir)
//        ProfileProperty(stringResource(R.string.passport_series), userData.pasportSeria)
//        ProfileProperty(stringResource(R.string.passport_number), userData.pasportNomer)
//        ProfileProperty(stringResource(R.string.passport_expiration_date), userData.dateOfPassport)
//        ProfileProperty(stringResource(R.string.passport_issued_date), userData.passportIssued)
//        ProfileFileProperty(
//            label = stringResource(R.string.passport_pdf),
//            downloadUrl = userData.pasportPdf,
//            onDownloadClick = { eventHandler(ProfileScreenClickIntents.OnDownloadFileClicked(it)) }
//        )
//        ProfileProperty(stringResource(R.string.nationality), userData.nationality)
//        ProfileProperty(stringResource(R.string.citizenship), userData.citizenship)
//        ProfileProperty(stringResource(R.string.partisanship), userData.partisanship)
//        ProfileProperty(stringResource(R.string.birthday), userData.dateOfBirth)
//        ProfileProperty(stringResource(R.string.gender), userData.gender)
//        ProfileProperty(stringResource(R.string.city), userData.city)
//        ProfileProperty(stringResource(R.string.district), userData.district)
//        ProfileProperty(stringResource(R.string.inn_number), userData.innNomer)
//        ProfileProperty(stringResource(R.string.phone_number), userData.phoneNo)
//        ProfileProperty(
//            stringResource(R.string.pension_register_number),
//            userData.pensiyaDaftariRaqimi
//        )
//        ProfileProperty(stringResource(R.string.language_proficiency), userData.tiliniBilishDaraja)
//        ProfileProperty(stringResource(R.string.government_awards), userData.davlatMukofotlari)
//        ProfileProperty(stringResource(R.string.departamental_awards), userData.idoraviyMukofotlari)
//        ProfileProperty(
//            stringResource(R.string.qualification_organization_name),
//            userData.malakaTashkilotNomi
//        )
        ProfileProperty(stringResource(R.string.qualification_area), userData.malakaYunalish)
        ProfileProperty(stringResource(R.string.starting_date), userData.boshlaganVaqti)
        ProfileProperty(stringResource(R.string.ending_date), userData.tugatganVaqti)
        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        Name(
            username = stringResource(id = R.string.diplomas),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Red)
        ) {
            items(userData.diplomlar) {
                DiplomaProperty(diploma = it, diplomaNumber = "1")
            }
        }

//        ProfileProperty(stringResource(R.string.twitter), userData.twitter, isLink = true)

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun NameAndPosition(
    userData: ProfileData
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        val fullName = userData.firstName + " " + userData.lastName + " " + userData.patronymicName
        Name(
            username = fullName,
            modifier = Modifier.baselineHeight(32.dp)
        )
        Address(
            address = userData.address,
            modifier = Modifier.baselineHeight(24.dp)
        )
        Address(
            address = userData.permanentAddress,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .baselineHeight(24.dp)
        )
    }
}

@Composable
private fun Name(username: String, modifier: Modifier = Modifier) {
    Text(
        text = username,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun Address(address: String?, modifier: Modifier = Modifier) {
    Text(
        text = address ?: stringResource(id = R.string.not_available),
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun ProfileFileProperty(
    label: String,
    downloadUrl: String?,
    onDownloadClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        HorizontalDivider()
        if (downloadUrl == null) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(id = R.string.not_available),
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    modifier = Modifier
                        .weight(1F),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                IconButton(onClick = { onDownloadClick(downloadUrl) }) {
                    Icon(imageVector = Icons.Default.Downloading, contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun ProfileProperty(label: String, value: String?, isLink: Boolean = false) {
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
            text = value ?: stringResource(id = R.string.not_available),
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
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.profile_error)
        )
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

@Preview
@Composable
private fun PreviewLight() {
    KadrlarTheme {
        ProfileContent(
            uiState = ProfileResponse(
                status = 200, user = ProfileUser(
                    profile = listOf(
                        ProfileData(
                            address = "Samarkand, Uzbekistan",
                            boshlaganVaqti = "01-03-2023",
                            citizenship = "Uzbek",
                            city = "Samarkand",
                            dateOfBirth = "20-03-2001",
                            dateOfPassport = "20-05-2025",
                            davlatMukofotlari = "",
                            diplomlar = listOf(
                                Diplomlar(
                                    file = "file_link",
                                    id = 1,
                                    mutaxasisligi = "Mutaxassisligi",
                                    nomeri = "2349s7df9793",
                                    profile = 1,
                                    seriyasi = "ABF",
                                    tugatganMuassasasi = "Toshkent Axborot Texnologiyalari Universiteti",
                                    tugatganYili = "2019",
                                    xujjatTuri = "Bakalavr diplomi"
                                ),
                                Diplomlar(
                                    file = "file_link",
                                    id = 1,
                                    mutaxasisligi = "Mutaxassisligi",
                                    nomeri = "2349s7df9793",
                                    profile = 1,
                                    seriyasi = "ABF",
                                    tugatganMuassasasi = "Toshkent Axborot Texnologiyalari Universiteti",
                                    tugatganYili = "2019",
                                    xujjatTuri = "Bakalavr diplomi"
                                ),
                                Diplomlar(
                                    file = "file_link",
                                    id = 1,
                                    mutaxasisligi = "Mutaxassisligi",
                                    nomeri = "2349s7df9793",
                                    profile = 1,
                                    seriyasi = "ABF",
                                    tugatganMuassasasi = "Toshkent Axborot Texnologiyalari Universiteti",
                                    tugatganYili = "2019",
                                    xujjatTuri = "Bakalavr diplomi"
                                )
                            ),
                            district = "Jomboy",
                            firstName = "Hasan",
                            gender = "Male",
                            id = 0,
                            idoraviyMukofotlari = "",
                            innNomer = "242175df7f9a7f97asf97",
                            jshir = "341593473953798579853",
                            lastName = "Anorov",
                            malakaTashkilotNomi = "Tuit Sf",
                            malakaYunalish = "Axborot xavfsizligi",
                            nationality = "Uzbek",
                            //todo ask obyektivka
                            obyektivka = "",
                            partisanship = "UzLidep",
                            pasportNomer = "3241424",
                            pasportPdf = "passport url",
                            pasportSeria = "AB",
                            passportIssued = "20-12-2024",
                            patronymicName = "Ikromjon o'g'li",
                            pensiyaDaftariRaqimi = "2048324a",
                            permanentAddress = "Jomboy tumani, Samarkand",
                            phoneNo = "93 1234567",
                            status = "Status",
                            tiliniBilishDaraja = "Ingliz tili, IELTS 6.5",
                            tugatganVaqti = "20-07-2019"
                        )
                    ),
                    username = "Hasan"
                )
            ),
            eventHandler = {}
        )
    }
}

@Preview
@Composable
private fun PreviewDark() {
    KadrlarTheme(isDarkTheme = true) {
        ProfileContent(
            uiState = ProfileResponse(
                status = 200,
                user = ProfileUser(
                    profile = listOf(
                        ProfileData(
                            address = null,
                            boshlaganVaqti = "01-03-2023",
                            citizenship = "Uzbek",
                            city = "Samarkand",
                            dateOfBirth = "20-03-2001",
                            dateOfPassport = "20-05-2025",
                            davlatMukofotlari = "",
                            diplomlar = listOf(),
                            district = "Jomboy",
                            firstName = "Hasan",
                            gender = "Male",
                            id = 0,
                            idoraviyMukofotlari = "",
                            innNomer = "242175df7f9a7f97asf97",
                            jshir = "341593473953798579853",
                            lastName = "Anorov",
                            malakaTashkilotNomi = "Tuit Sf",
                            malakaYunalish = "Axborot xavfsizligi",
                            nationality = "Uzbek",
                            //todo ask obyektivka
                            obyektivka = "",
                            partisanship = "UzLidep",
                            pasportNomer = "3241424",
                            pasportPdf = null,
                            pasportSeria = "AB",
                            passportIssued = "20-12-2024",
                            patronymicName = "Ikromjon o'g'li",
                            pensiyaDaftariRaqimi = "2048324a",
                            permanentAddress = "Jomboy tumani, Samarkand",
                            phoneNo = "93 1234567",
                            status = "Status",
                            tiliniBilishDaraja = "Ingliz tili, IELTS 6.5",
                            tugatganVaqti = "20-07-2019"
                        )
                    ),
                    username = "Hasan"
                )
            ),
            eventHandler = {}
        )
    }
}