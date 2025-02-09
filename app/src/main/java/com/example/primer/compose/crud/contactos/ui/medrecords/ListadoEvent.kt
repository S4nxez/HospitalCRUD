package com.example.primer.compose.crud.contactos.ui.medrecords

sealed class ListadoEvent {
    data class GetRecordsByPatient(val id: Int) : ListadoEvent()
    object UiEventDone : ListadoEvent()
}