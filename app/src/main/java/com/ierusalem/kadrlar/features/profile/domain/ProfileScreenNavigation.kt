package com.ierusalem.kadrlar.features.profile.domain

sealed interface ProfileScreenNavigation {
    data object OnNavIconClicked : ProfileScreenNavigation
    data object InvalidResponse : ProfileScreenNavigation
}