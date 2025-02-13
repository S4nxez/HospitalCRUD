package com.example.primer.compose.crud.contactos.ui.patients.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.primer.compose.crud.contactos.ui.doctors.createList


@Composable
fun ListadoPatientScreen(
    viewModel: ListadoPatientViewModel = hiltViewModel(),
    onNavigateDetalle: (Int) -> Unit = {},
    ) {
    val uiState by viewModel.uiState.collectAsState()

    viewModel.handleEvent(ListadoPatientEvent.GetPatients())

    val result = uiState.patients
    createList(result, onNavigateDetalle)
}


