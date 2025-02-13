package com.example.primer.compose.crud.contactos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PatientEntity::class, MedicalRecordEntity::class,
        DoctorEntity::class, PatientDoctorEntity::class],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao
}