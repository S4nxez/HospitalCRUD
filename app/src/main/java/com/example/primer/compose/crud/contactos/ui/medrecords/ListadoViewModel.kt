package com.example.primer.compose.crud.contactos.ui.medrecords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primer.compose.crud.contactos.data.PreferencesRepository
import com.example.primer.compose.crud.contactos.data.local.di.IoDispatcher
import com.example.primer.compose.crud.contactos.domain.usecases.login.GetLoginUseCase
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListadoViewModel @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val getMedRecordsUc: GetLoginUseCase,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {
    private val _uiEvent = MutableStateFlow<UiEvent?>(null)

    private val _uiState = MutableStateFlow(ListadoState())
    val uiState = _uiState.asStateFlow()

    val userId: StateFlow<Int> = preferencesRepository.userId
        .stateIn(viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = -1)

    fun handleEvent(event: ListadoEvent) {
        when (event) {
            is ListadoEvent.UiEventDone -> {
                _uiEvent.update { null }
            }

            is ListadoEvent.GetRecordsByPatient -> {
                getMedRecords(event.id)
            }

        }
    }

    private fun getMedRecords(id: Int) {
        viewModelScope.launch(dispatcher) {
            val result = getMedRecordsUc(id)

            _uiState.update {
                if (result.isNotEmpty()) {
                    it.copy(
                        medRecords = result,
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