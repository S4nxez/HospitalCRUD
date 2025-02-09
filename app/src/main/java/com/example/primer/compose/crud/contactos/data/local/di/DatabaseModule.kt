package com.example.primer.compose.crud.contactos.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.primer.compose.crud.contactos.data.local.AppDatabase
import com.example.primer.compose.crud.contactos.data.local.HospitalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        val builder = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        )

        if (!appContext.getDatabasePath("app.db").exists())
            builder.createFromAsset("database/final.db")

        return builder
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideHospitalDao(appDatabase: AppDatabase): HospitalDao =
        appDatabase.cocheDao()
}