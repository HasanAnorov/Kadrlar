package com.ierusalem.kadrlar.features.profile.domain

sealed interface ProfileScreenClickIntents {
    data object OnNavIconClicked : ProfileScreenClickIntents
    data object OnEditProfileClicked : ProfileScreenClickIntents
    data class OnDownloadFileClicked(val downloadUrlLink:String) : ProfileScreenClickIntents
}