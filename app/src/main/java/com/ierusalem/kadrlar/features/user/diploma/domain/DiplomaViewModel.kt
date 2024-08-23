package com.ierusalem.kadrlar.features.user.diploma.domain

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DiplomaViewModel : ViewModel(),
    NavigationEventDelegate<DiplomaScreenNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<DiplomaScreenState> = MutableStateFlow(
        DiplomaScreenState()
    )
    val state = _state.asStateFlow()

    fun handleClickIntents(intent: DiplomaScreenClickIntents) {
        when(intent){

            DiplomaScreenClickIntents.OnSaveClicked -> {
                val hasEmptyFields = state.value.hasEmptyFields()
                if (hasEmptyFields){
                    emitNavigation(DiplomaScreenNavigation.HasEmptyFields)
                }else{
                    emitNavigation(DiplomaScreenNavigation.NavigateToHomeWithDiploma(state.value))
                }
            }

            DiplomaScreenClickIntents.OnNavIconClicked -> {
                emitNavigation(DiplomaScreenNavigation.NavigateToHome)
            }
            is DiplomaScreenClickIntents.OnDiplomaTypeChanged -> {
                _state.update {
                    it.copy(
                        diplomaType = intent.diplomaType
                    )
                }
            }
            is DiplomaScreenClickIntents.OnDiplomaSpecificationChanged -> {
                _state.update {
                    it.copy(
                        diplomaSpecification = intent.specification
                    )
                }
            }
            is DiplomaScreenClickIntents.OnDiplomaSeriesChanged -> {
                _state.update {
                    it.copy(
                        diplomaSeries = intent.diplomaSeries
                    )
                }
            }
            is DiplomaScreenClickIntents.OnDiplomaNumberChanged -> {
                _state.update {
                    it.copy(
                        diplomaNumber = intent.diplomaNumber
                    )
                }
            }
            is DiplomaScreenClickIntents.OnGraduationDateChanged -> {
                _state.update {
                    it.copy(
                        graduationDate = intent.graduationDate
                    )
                }
            }
            is DiplomaScreenClickIntents.OnGraduatedUniversityChanged -> {
                _state.update {
                    it.copy(
                        graduatedUniversity = intent.graduatedUniversity
                    )
                }
            }
        }
    }

}

@Immutable
data class DiplomaScreenState(
    val diplomaType:String =  "",
    val diplomaSpecification:String = "",
    val diplomaSeries:String = "",
    val diplomaNumber:String = "",
    val graduationDate:String = "2020-01-01",
    val graduatedUniversity:String = "",
){
    fun hasEmptyFields(): Boolean {
        return diplomaType.isBlank() ||
                diplomaSpecification.isBlank() ||
                diplomaSeries.isBlank() ||
                diplomaNumber.isBlank() ||
                graduationDate.isBlank() ||   // Optionally, you may want to validate the date format
                graduatedUniversity.isBlank()
    }
}
