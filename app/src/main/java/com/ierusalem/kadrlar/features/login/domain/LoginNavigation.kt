package com.ierusalem.kadrlar.features.login.domain

sealed interface LoginNavigation {
    data object ToHome: LoginNavigation
}