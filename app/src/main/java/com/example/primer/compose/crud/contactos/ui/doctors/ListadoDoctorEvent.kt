package com.example.primer.compose.crud.contactos.ui.doctors

sealed class ListadoDoctorEvent {
    data class GetDoctors(val id : Int) : ListadoDoctorEvent()
    object UiDoctorEventDone : ListadoDoctorEvent()
}