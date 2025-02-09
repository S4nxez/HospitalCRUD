package com.example.primer.compose.crud.contactos.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
object PatientsDestination

@Serializable
object LoginDestination

@Serializable
object MedRecordDestination

@Serializable
data class DetallePatientDestination(val id: Int)
