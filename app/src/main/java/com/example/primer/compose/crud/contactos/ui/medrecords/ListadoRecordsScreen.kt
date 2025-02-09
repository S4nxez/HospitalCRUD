package com.example.primer.compose.crud.contactos.ui.medrecords

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.primer.compose.crud.contactos.R


@Composable
fun ListadoRecordsScreen(
    viewModel: ListadoViewModel = hiltViewModel(),
    onNavigateDetalle: (Int) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()
    val userId by viewModel.userId.collectAsStateWithLifecycle()

    viewModel.handleEvent(ListadoEvent.GetRecordsByPatient(userId))

    Spacer(modifier = Modifier.padding(4.dp))
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        val result = uiState.medRecords

        items(result) { record ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable { onNavigateDetalle(record.id) },
            ) {
                Text(
                    text = record.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.PADDING_16))
                )
            }
        }
    }
}