package com.example.primer.compose.crud.contactos.data

import com.example.primer.compose.crud.contactos.data.local.HospitalDao
import com.example.primer.compose.crud.contactos.data.local.di.IoDispatcher
import com.example.primer.compose.crud.contactos.data.local.toDoctor
import com.example.primer.compose.crud.contactos.data.local.toMedicalRecord
import com.example.primer.compose.crud.contactos.data.local.toPatient
import com.example.primer.compose.crud.contactos.domain.model.Doctor
import com.example.primer.compose.crud.contactos.domain.model.MedicalRecord
import com.example.primer.compose.crud.contactos.domain.model.Patient
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ObjetosRepository @Inject constructor(
    private val hospitalDao: HospitalDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    fun fetchUsers(): List<Patient> =
        hospitalDao.getAll().map { it.toPatient() }

    fun deleteUser(id: Int) =
        hospitalDao.delete(getPatientById(id).toPatient())

    fun editPatient(patient: Patient) =
        hospitalDao.update(
            patient.id,
            patient.nombre,
            patient.birthDate,
            patient.phone,
        )

    fun addPatient(patient: Patient): Long =
        hospitalDao.insert(patient.toPatient())

    fun login(id: Int): List<MedicalRecord> =
        hospitalDao.getMedicalRecordsById(id).map { it.toMedicalRecord() }

    fun getPatientById(id: Int): Patient =
        hospitalDao.getPatient(id).toPatient()

    fun getDoctorsByPatient(id: Int): List<Doctor> =
        hospitalDao.getDoctorsByPatient(id).map { it.toDoctor() }
}