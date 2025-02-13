package com.example.primer.compose.crud.contactos.domain.model

data class Doctor (
    override val id : Int = 0,
    override val nombre: String,
    override val detalle: String? = "General",
    val phone: String?
) : ModeloItem
