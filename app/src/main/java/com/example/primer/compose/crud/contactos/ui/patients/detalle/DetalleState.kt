package com.example.primer.compose.crud.contactos.ui.patients.detalle

import com.example.primer.compose.crud.contactos.domain.model.Patient
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent

data class DetalleState(
    val patient: Patient? = null,
    val isLoading: Boolean = false,
    val uiEvent: List<UiEvent?> = emptyList(),
)
