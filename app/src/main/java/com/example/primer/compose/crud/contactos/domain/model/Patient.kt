package com.example.primer.compose.crud.contactos.domain.model

import com.example.primer.compose.crud.contactos.data.local.PatientEntity


data class Patient(val id : Int = 0,
                   val name : String = "",
                   val birthDate : String = "",
                   val phone : String = "",
                   val paid : Int = 0,
                   val username : String = "",
                   val password : String = "",
                    ) {
    fun toPatient(): PatientEntity {
        return PatientEntity(
            patient_id = id,
            name = name,
            date_of_birth = birthDate,
            phone = phone
        )
    }
}
