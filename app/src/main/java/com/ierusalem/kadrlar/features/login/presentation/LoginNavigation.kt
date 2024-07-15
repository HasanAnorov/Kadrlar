package com.ierusalem.kadrlar.features.login.presentation

sealed interface LoginNavigation {
    data object ToHome: LoginNavigation
    data object InvalidResponse: LoginNavigation
}