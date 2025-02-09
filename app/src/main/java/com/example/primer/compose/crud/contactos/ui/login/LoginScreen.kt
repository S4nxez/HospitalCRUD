package com.example.primer.compose.crud.contactos.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.primer.compose.crud.contactos.R
import com.example.primer.compose.crud.contactos.ui.navigation.LoginDestination
import com.example.primer.compose.crud.contactos.ui.utils.UiEvent
import kotlinx.coroutines.Job

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    showSnackbar: (String, Boolean, () -> Unit) -> Job,
    navController: NavController,
) {
    val uiState by loginViewModel.uiState.collectAsState()

    LoginContent(loginViewModel)

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message, false) {}
            } else if (it is UiEvent.Navigate) {
                navController.navigate(it.route) {
                    popUpTo(LoginDestination) { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun LoginContent(loginViewModel: LoginViewModel) {
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.PADDING_16)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Id") },
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(bottom = dimensionResource(R.dimen.PADDING_16))
        )

        Button(
            onClick = {
                loginViewModel.handleEvent(LoginEvent.doLogin(inputValue.text.toInt()))
            },
            modifier = Modifier.fillMaxWidth(0.3f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        ) {
            Text(stringResource(R.string.ENTRAR))
        }
    }
}
