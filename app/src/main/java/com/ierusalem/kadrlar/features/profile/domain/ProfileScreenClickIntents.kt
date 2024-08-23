package com.ierusalem.kadrlar.features.profile.domain

sealed interface ProfileScreenClickIntents {
    data object OnNavIconClicked : ProfileScreenClickIntents
}