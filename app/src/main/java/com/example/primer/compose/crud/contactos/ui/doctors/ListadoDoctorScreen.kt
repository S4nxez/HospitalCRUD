package com.example.primer.compose.crud.contactos.ui.doctors

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.primer.compose.crud.contactos.R
import com.example.primer.compose.crud.contactos.domain.model.ModeloItem
import com.example.primer.compose.crud.contactos.ui.utils.PreferenceViewModel

@Composable
fun ListadoDoctorScreen(
    preferenceViewModel: PreferenceViewModel = hiltViewModel(),
    viewModel: ListadoDoctorViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val userId by preferenceViewModel.userId.collectAsStateWithLifecycle()

    viewModel.handleEvent(ListadoDoctorEvent.GetDoctors(userId))
    val result = uiState.doctors
    createList(result)
}


@Composable
fun createList(
    result: List<ModeloItem>,
    onNavigateDetalle: ((Int) -> Unit)? = null,
) {
    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.PADDING_8DP)))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.PADDING_16))
    ) {
        items(result) { result ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.PADDING_8DP))
                    .clickable(enabled = onNavigateDetalle != null) {
                        onNavigateDetalle?.invoke(result.id)
                    },
                elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.PADDING_8DP)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.PADDING_16))
                ) {
                    Text(
                        text = result.nombre,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.PADDING_4DP))
                    )
                    if (result.detalle != null)
                        Text(
                            text = result.detalle ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                }
            }
        }
    }
}
