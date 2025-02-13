package com.example.primer.compose.crud.contactos.ui.doctors

import com.example.primer.compose.crud.contactos.domain.model.Doctor
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent

data class ListadoDoctorState(
    val doctors: List<Doctor> = emptyList(),
    val isLoading: Boolean = false,
    val selectMode: Boolean = false,
    val event: UiEvent? = null,
)
