package com.example.primer.compose.crud.contactos.domain.model

data class MedicalRecord(
    override val nombre: String,
    override val detalle: String? = null,
    override val id : Int,
    val date: String? = null,
    val idPatient: Int? = null,
    val idDoctor: Int? = null,
    val medications: List<String>? = null
) : ModeloItem