package com.example.primer.compose.crud.contactos.ui.patients.list

sealed class ListadoPatientEvent {
    class GetPatients() : ListadoPatientEvent()
    object UiPatientEventDone : ListadoPatientEvent()
}