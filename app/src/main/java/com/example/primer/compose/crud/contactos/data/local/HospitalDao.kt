package com.example.primer.compose.crud.contactos.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HospitalDao {

    @Query("SELECT * FROM patients WHERE name LIKE :filtro ORDER BY name")
    fun getAllFlowFiltrado(filtro: String?): List<PatientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(patientEntities: List<PatientEntity>)

    @Delete
    fun delete(patientEntity: PatientEntity)

    @Query("SELECT * FROM patients ORDER BY patient_id")
    fun getAll(): List<PatientEntity>

    @Query("SELECT * FROM patients WHERE patient_id = :id")
    fun getPatient(id: Int): PatientEntity

    @Query("UPDATE patients SET name = :name, date_of_birth = :dob, phone = :phone WHERE patient_id = :id")
    fun update(id: Int, name: String, dob: String, phone: String)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(patientEntity: PatientEntity) : Long

    @Query("SELECT * FROM medical_records WHERE patient_id = :id")
    fun getMedicalRecordsById(id: Int): List<MedicalRecordEntity>

    @Query("SELECT * FROM doctors where doctor_id IN(SELECT doctor_id FROM patient_doctor WHERE patient_id = :id)")
    fun getDoctorsByPatient(id: Int): List<DoctorEntity>
}