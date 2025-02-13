package com.example.primer.compose.crud.contactos.ui.doctors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primer.compose.crud.contactos.data.local.di.IoDispatcher
import com.example.primer.compose.crud.contactos.domain.usecases.contactos.GetDoctors
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListadoDoctorViewModel @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val getDoctors: GetDoctors,
) : ViewModel() {
    private val _uiEvent = MutableStateFlow<UiEvent?>(null)

    private val _uiState = MutableStateFlow(ListadoDoctorState())
    val uiState = _uiState.asStateFlow()

    fun handleEvent(event: ListadoDoctorEvent) {
        when (event) {
            is ListadoDoctorEvent.UiDoctorEventDone -> {
                _uiEvent.update { null }
            }

            is ListadoDoctorEvent.GetDoctors -> {
                getDoctors(event.id)
            }

        }
    }

    private fun getDoctors(id : Int) {
        viewModelScope.launch(dispatcher) {
            val result = getDoctors.invoke(id)

            _uiState.update {
                if (result.isNotEmpty()) {
                    it.copy(
                        doctors = result,
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

