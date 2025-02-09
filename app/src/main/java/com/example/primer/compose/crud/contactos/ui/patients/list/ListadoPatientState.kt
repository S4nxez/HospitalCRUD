package com.example.primer.compose.crud.contactos.ui.patients.list

import com.example.primer.compose.crud.contactos.domain.model.Patient
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent

data class ListadoPatientState(
    val patients: List<Patient> = emptyList(),
    val isLoading: Boolean = false,
    val selectMode: Boolean = false,
    val event: UiEvent? = null,
)
