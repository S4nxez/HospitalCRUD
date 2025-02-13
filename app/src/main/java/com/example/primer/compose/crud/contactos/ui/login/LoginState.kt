package com.example.primer.compose.crud.contactos.ui.login

import com.example.primer.compose.crud.contactos.ui.utils.UiEvent

data class LoginState(
    val patientId : Int,
    val event: List<UiEvent?> = emptyList(),
    val isLoading: Boolean = true,
)
