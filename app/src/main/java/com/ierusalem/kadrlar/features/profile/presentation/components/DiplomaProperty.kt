package com.ierusalem.kadrlar.features.profile.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.profile.data.models.response.Diplomlar

@Composable
fun DiplomaProperty(
    modifier: Modifier = Modifier,
    diplomaNumber: String,
    diploma: Diplomlar
) {
    Surface {
        Column(modifier = modifier) {
            Row {
                Text(
                    text = stringResource(id = R.string.diploma),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 16.dp,
                        end = 4.dp,
                        top = 16.dp
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = diplomaNumber,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            ProfileProperty(
                label = stringResource(id = R.string.diploma_type),
                value = diploma.xujjatTuri
            )
            ProfileProperty(
                label = stringResource(id = R.string.diploma_speciality),
                value = diploma.mutaxasisligi
            )
            ProfileProperty(
                label = stringResource(id = R.string.diploma_series),
                value = diploma.seriyasi
            )
            ProfileProperty(
                label = stringResource(id = R.string.diploma_number),
                value = diploma.nomeri
            )
            ProfileProperty(
                label = stringResource(id = R.string.graduation_date),
                value = diploma.tugatganYili
            )
            ProfileProperty(
                label = stringResource(id = R.string.graduated_university),
                value = diploma.tugatganMuassasasi
            )
            ProfileFileProperty(
                label = stringResource(id = R.string.diploma),
                downloadUrl = diploma.file,
                onDownloadClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDiplomaPropertyLight() {
    KadrlarTheme {
        DiplomaProperty(
            diploma = Diplomlar(
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
            diplomaNumber = "12"
        )
    }
}

@Preview
@Composable
private fun PreviewDiplomaPropertyDark() {
    KadrlarTheme {
        DiplomaProperty(
            diploma = Diplomlar(
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
            diplomaNumber = "2"
        )
    }
}