package com.example.primer.compose.crud.contactos.ui.patients.detalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.primer.compose.crud.contactos.R
import com.example.primer.compose.crud.contactos.domain.model.Patient
import com.example.primer.compose.crud.contactos.ui.theme.AppTheme
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent
import com.example.primer.compose.crud.contactos.ui.utils.fontDimensionResource
import kotlinx.coroutines.Job

@Composable
fun DetallePatientsScreen(
    patientId: Int,
    detalleViewModel: DetalleViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = {},
    showSnackbar: (String, Boolean, () -> Unit) -> Job,
) {
    val uiState = detalleViewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        detalleViewModel.handleEvent(DetalleEvent.GetPatient(patientId))
    }

    DetalleContent(
        patient = uiState.patient,
        onDelete = { detalleViewModel.handleEvent(DetalleEvent.DelPatient) },
        onEdit = { patient -> detalleViewModel.handleEvent(DetalleEvent.EditPatient(patient)) }
    )

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent.forEach {
            if (it is UiEvent.ShowSnackbar)
                showSnackbar(it.message, false, {})
            else if (it is UiEvent.Navigate)
                onNavigateBack()
            detalleViewModel.handleEvent(DetalleEvent.UiEventDone)
        }
    }
}

@Composable
fun DetalleContent(
    patient: Patient?,
    onDelete: () -> Unit = {},
    onEdit: (patient : Patient) -> Unit
) {
    var isEditMode by remember { mutableStateOf(false) }
    var patientName by remember { mutableStateOf("") }
    var patientBirthDate by remember { mutableStateOf("") }
    var patientPhone by remember { mutableStateOf("") }

    LaunchedEffect(patient) {
        patientName = patient?.nombre ?: ""
        patientBirthDate = patient?.birthDate ?: ""
        patientPhone = patient?.phone ?: ""
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.PADDING_16)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_16)))

            Text(
                text = "Detalles del Paciente",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_16)))

            Text(
                text = "Nombre:",
                style = MaterialTheme.typography.labelMedium
                    .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_4DP)))
            if (isEditMode) {
                TextField(
                    value = patientName,
                    onValueChange = { patientName = it },
                    textStyle = MaterialTheme.typography.bodyLarge
                        .copy(fontSize = fontDimensionResource(R.dimen.SP25))
                )
            } else {
                Text(
                    text = patientName,
                    style = MaterialTheme.typography.bodyLarge
                        .copy(fontSize = fontDimensionResource(R.dimen.SP25))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_16)))

            Text(
                text = "ID:",
                style = MaterialTheme.typography.labelMedium
                    .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.PADDING_8DP)))
            Text(
                text = patient?.id?.toString() ?: "",
                style = MaterialTheme.typography.bodyMedium
                    .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_16)))

            Text(
                text = "Fecha de Nacimiento:",
                style = MaterialTheme.typography.labelMedium
                    .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_4DP)))
            if (isEditMode) {
                TextField(
                    value = patientBirthDate,
                    onValueChange = { patientBirthDate = it },
                    textStyle = MaterialTheme.typography.bodyMedium
                        .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
                )
            } else {
                Text(
                    text = patientBirthDate,
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_16)))

            Text(
                text = "Teléfono:",
                style = MaterialTheme.typography.labelMedium
                    .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_4DP)))
            if (isEditMode) {
                TextField(
                    value = patientPhone,
                    onValueChange = { patientPhone = it },
                    textStyle = MaterialTheme.typography.bodyMedium
                        .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
                )
            } else {
                Text(
                    text = patientPhone,
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontSize = fontDimensionResource(R.dimen.FONT_18SP))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.PADDING_16)))
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dimensionResource(R.dimen.PADDING_16))
        ) {
            Button(onClick = onDelete, modifier = Modifier.weight(1f)) {
                Text("Borrar", fontSize = fontDimensionResource(R.dimen.SP16))
            }
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.PADDING_8DP)))
            Button(
                onClick = {
                    isEditMode = !isEditMode
                    if (!isEditMode) {
                        onEdit(Patient(
                            id = patient?.id ?: 0,
                            nombre = patientName,
                            birthDate = patientBirthDate,
                            phone = patientPhone,
                            paid = patient?.paid ?: 0,
                            username = patient?.username ?: "",
                            password = patient?.password ?: ""
                        ))
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (isEditMode) "Guardar" else "Editar",
                    fontSize = fontDimensionResource(R.dimen.SP16))
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7_PRO,
    name = "Light Mode"
)
@Composable
fun PreviewDetallePatient() {
    AppTheme {
        DetalleContent(
            patient = Patient(
                id = 0,
                nombre = "Daniel",
                birthDate = "Nacimiento",
                phone = "asd",
                paid = 0,
                username = "aqwesd",
                password = "as12d",
            ),
            onDelete = {},
            onEdit = {}
        )
    }
}