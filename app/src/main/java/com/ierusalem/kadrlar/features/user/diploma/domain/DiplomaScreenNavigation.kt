package com.ierusalem.kadrlar.features.user.diploma.domain

sealed interface DiplomaScreenNavigation {
    data class NavigateToHomeWithDiploma(val diploma: DiplomaScreenState): DiplomaScreenNavigation
    data object NavigateToHome: DiplomaScreenNavigation
    data object HasEmptyFields: DiplomaScreenNavigation
}