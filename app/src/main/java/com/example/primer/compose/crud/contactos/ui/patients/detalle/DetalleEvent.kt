package com.example.primer.compose.crud.contactos.ui.patients.detalle

import com.example.primer.compose.crud.contactos.domain.model.Patient


sealed class DetalleEvent {
    data class GetPatient(val id: Int) : DetalleEvent()
    data object DelPatient : DetalleEvent()
    data class EditPatient(val patient: Patient) : DetalleEvent()
    data object UiEventDone : DetalleEvent()
}
