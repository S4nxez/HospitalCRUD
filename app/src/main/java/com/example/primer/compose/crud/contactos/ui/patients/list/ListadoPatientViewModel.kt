package com.example.primer.compose.crud.contactos.ui.patients.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primer.compose.crud.contactos.data.local.di.IoDispatcher
import com.example.primer.compose.crud.contactos.domain.usecases.contactos.GetPatients
import com.example.primer.compose.crud.contactos.ui.navigation.DetallePatientDestination
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListadoPatientViewModel @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val getPatients: GetPatients,
) : ViewModel() {
    private val _uiEvent = MutableStateFlow<UiEvent?>(null)

    private val _uiState = MutableStateFlow(ListadoPatientState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: ListadoPatientEvent) {
        when (event) {
            is ListadoPatientEvent.UiPatientEventDone -> {
                _uiEvent.update { null }
            }

            is ListadoPatientEvent.GetPatients -> {
                getPatients()
            }

        }
    }

    private fun getPatients() {
        viewModelScope.launch(dispatcher) {
            val result = getPatients.invoke()

            _uiState.update {
                if (result.isNotEmpty()) {
                    it.copy(
                        patients = result,
                        event = UiEvent.Navigate(DetallePatientDestination)
                    )
                } else {
                    it.copy(
                        event = UiEvent.ShowSnackbar("Id no válido")
                    )
                }
            }
        }
    }
}

