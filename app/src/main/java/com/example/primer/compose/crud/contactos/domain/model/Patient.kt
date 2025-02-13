package com.example.primer.compose.crud.contactos.domain.model

import com.example.primer.compose.crud.contactos.data.local.PatientEntity


data class Patient(override val id : Int,
                   override val nombre: String,
                   override val detalle: String? = null,
                   val birthDate : String = "",
                   val phone : String = "",
                   val paid : Int = 0,
                   val username : String = "",
                   val password : String = "",
                    ) : ModeloItem {
    fun toPatient(): PatientEntity {
        return PatientEntity(
            patient_id = id,
            name = nombre,
            date_of_birth = birthDate,
            phone = phone
        )
    }
}
