package com.example.primer.compose.crud.contactos.ui.patients.detalle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primer.compose.crud.contactos.data.local.di.IoDispatcher
import com.example.primer.compose.crud.contactos.domain.usecases.contactos.DeletePatientByIdUseCase
import com.example.primer.compose.crud.contactos.domain.usecases.contactos.EditPatientUseCase
import com.example.primer.compose.crud.contactos.domain.usecases.contactos.GetPatientById
import com.example.primer.compose.crud.contactos.ui.navigation.PatientsDestination
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleViewModel @Inject constructor(
    val getPatientById: GetPatientById,
    val deletePatient: DeletePatientByIdUseCase,
    val editPatient: EditPatientUseCase,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetalleState> = MutableStateFlow(
        DetalleState()
    )


    val uiState: StateFlow<DetalleState> = _uiState.asStateFlow()

    fun handleEvent(event: DetalleEvent) {
        when (event) {
            is DetalleEvent.GetPatient ->
                viewModelScope.launch(dispatcher) {
                    val result = getPatientById.invoke(event.id)

                    _uiState.update {
                        it.copy(
                            patient = result,
                        )
                    }
                }

            is DetalleEvent.DelPatient -> viewModelScope.launch(dispatcher) {
                _uiState.value.patient?.let { patient ->
                    deletePatient(patient.id)
                    _uiState.update {
                        it.copy(
                            uiEvent = listOf(UiEvent.Navigate(PatientsDestination)),
                        )
                    }
                }
            }

            is DetalleEvent.UiEventDone -> {
                _uiState.update { it.copy(uiEvent = emptyList()) }
            }

            is DetalleEvent.EditPatient -> {
                viewModelScope.launch(dispatcher) {
                    editPatient.invoke(event.patient)
                }
            }
        }
    }


}