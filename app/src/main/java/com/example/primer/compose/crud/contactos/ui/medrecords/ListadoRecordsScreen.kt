package com.example.primer.compose.crud.contactos.ui.medrecords

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.primer.compose.crud.contactos.ui.doctors.createList


@Composable
fun ListadoRecordsScreen(
    viewModel: ListadoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val userId by viewModel.userId.collectAsStateWithLifecycle()

    viewModel.handleEvent(ListadoEvent.GetRecordsByPatient(userId))
    val result = uiState.medRecords
    createList(result)
}