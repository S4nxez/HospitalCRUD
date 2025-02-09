package com.example.primer.compose.crud.contactos.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primer.compose.crud.contactos.data.PreferencesRepository
import com.example.primer.compose.crud.contactos.data.local.di.IoDispatcher
import com.example.primer.compose.crud.contactos.domain.usecases.login.GetLoginUseCase
import com.example.primer.compose.crud.contactos.ui.navigation.MedRecordDestination
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInFlow: GetLoginUseCase,
    private val preferencesRepository: PreferencesRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState(0))
    val uiState = _uiState.asStateFlow()


    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.doLogin -> doLogin(event.id)
        }
    }

    private fun doLogin(id: Int) {
        viewModelScope.launch(dispatcher) {
            val result = logInFlow.invoke(id)
            _uiState.update {
                if (result.isNotEmpty()) {
                    preferencesRepository.saveUserName(id)
                    it.copy(
                        patientId = id,
                        event = UiEvent.Navigate(MedRecordDestination)
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
