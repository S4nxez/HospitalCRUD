package com.example.primer.compose.crud.contactos.ui.medrecords

import com.example.primer.compose.crud.contactos.domain.model.MedicalRecord
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent

data class ListadoState(
    val medRecords: List<MedicalRecord> = emptyList(),
    val isLoading: Boolean = false,
    val selectMode: Boolean = false,
    val event: UiEvent? = null,
)
