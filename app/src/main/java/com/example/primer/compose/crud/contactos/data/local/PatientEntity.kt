package com.example.primer.compose.crud.contactos.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.primer.compose.crud.contactos.domain.model.MedicalRecord
import com.example.primer.compose.crud.contactos.domain.model.Patient

@Entity(
    tableName = "patients",
    indices = [Index(value = ["name", "date_of_birth"], unique = true)]
)
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val patient_id: Int? = null,
    val name: String,
    val date_of_birth: String?,
    val phone: String?
)

fun PatientEntity.toPatient(): Patient = Patient(
    id = this.patient_id!!,
    name = this.name,
    birthDate = this.date_of_birth!!,
    phone = this.phone!!
)

@Entity(tableName = "doctors")
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true) val doctor_id: Int? = null,
    val name: String,
    val specialization: String?,
    val phone: String?
)

@Entity(
    tableName = "medical_records",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["patient_id"],
            childColumns = ["patient_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DoctorEntity::class,
            parentColumns = ["doctor_id"],
            childColumns = ["doctor_id"]
        )
    ]
)
data class MedicalRecordEntity(
    @PrimaryKey(autoGenerate = true) val record_id: Int? = null,
    val patient_id: Int,
    val doctor_id: Int,
    val diagnosis: String?,
    val admission_date: String?
)

fun MedicalRecordEntity.toMedicalRecord(): MedicalRecord = MedicalRecord(
    id = this.record_id!!,
    description = this.diagnosis!!,
    date = this.admission_date,
    idPatient = this.patient_id,
    idDoctor = this.doctor_id,
    medications = emptyList()

)

@Entity(
    tableName = "appointments",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["patient_id"],
            childColumns = ["patient_id"],
            onDelete = ForeignKey.CASCADE,

        ),
        ForeignKey(
            entity = DoctorEntity::class,
            parentColumns = ["doctor_id"],
            childColumns = ["doctor_id"]
        )
    ]
)
data class Appointment(
    @PrimaryKey(autoGenerate = true) val appointment_id: Int = 0,
    val patient_id: Int,
    val doctor_id: Int,
    val appointment_date: String
)

@Entity(
    tableName = "prescribed_medications",
    foreignKeys = [
        ForeignKey(
            entity = MedicalRecordEntity::class,
            parentColumns = ["record_id"],
            childColumns = ["record_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class PrescribedMedication(
    @PrimaryKey(autoGenerate = true) val prescription_id: Int = 0,
    val record_id: Int,
    val medication_name: String,
    val dosage: String
)

@Entity(
    tableName = "patient_payments",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["patient_id"],
            childColumns = ["patient_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class PatientPayment(
    @PrimaryKey(autoGenerate = true) val payment_id: Int = 0,
    val patient_id: Int,
    val amount: Double,
    val payment_date: String
)

@Entity(
    tableName = "user_login",
    foreignKeys = [
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = ["patient_id"],
            childColumns = ["patient_id"],
            deferred = true,
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = DoctorEntity::class,
            parentColumns = ["doctor_id"],
            childColumns = ["doctor_id"],
            deferred = true
        )
    ]
)
data class UserLogin(
    @PrimaryKey(autoGenerate = true) val user_id: Int = 0,
    val username: String,
    val password: String,
    val patient_id: Int?,
    val doctor_id: Int?
)
