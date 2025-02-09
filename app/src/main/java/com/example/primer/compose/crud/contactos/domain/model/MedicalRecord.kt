package com.example.primer.compose.crud.contactos.domain.model

data class MedicalRecord(
    val id: Int = 0,
    val description: String,
    val date: String? = null,
    val idPatient: Int? = null,
    val idDoctor: Int? = null,
    val medications: List<String>? = null
)