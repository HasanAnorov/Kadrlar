package com.ierusalem.kadrlar.features.user.diploma.domain

sealed interface DiplomaScreenClickIntents {
    data object OnSaveClicked: DiplomaScreenClickIntents
    data object OnNavIconClicked: DiplomaScreenClickIntents
    data class OnDiplomaTypeChanged(val diplomaType:String): DiplomaScreenClickIntents
    data class OnDiplomaSpecificationChanged(val specification:String): DiplomaScreenClickIntents
    data class OnDiplomaSeriesChanged(val diplomaSeries:String): DiplomaScreenClickIntents
    data class OnDiplomaNumberChanged(val diplomaNumber:String): DiplomaScreenClickIntents
    data class OnGraduationDateChanged(val graduationDate:String): DiplomaScreenClickIntents
    data class OnGraduatedUniversityChanged(val graduatedUniversity:String):
        DiplomaScreenClickIntents
}