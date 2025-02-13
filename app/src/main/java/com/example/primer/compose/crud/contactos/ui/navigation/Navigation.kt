package com.example.primer.compose.crud.contactos.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.primer.compose.crud.contactos.ui.doctors.ListadoDoctorScreen
import com.example.primer.compose.crud.contactos.ui.login.LoginScreen
import com.example.primer.compose.crud.contactos.ui.medrecords.ListadoRecordsScreen
import com.example.primer.compose.crud.contactos.ui.patients.detalle.DetallePatientsScreen
import com.example.primer.compose.crud.contactos.ui.patients.list.ListadoPatientScreen
import com.example.primer.compose.crud.contactos.ui.utils.BottomBar
import com.example.primer.compose.crud.contactos.ui.utils.TopBar
import kotlinx.coroutines.launch

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val showSnackbar = { message: String, showUndo: Boolean, undo: () -> Unit ->
        scope.launch {
            if (showUndo) {
                val result = snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = "UNDO",
                    duration = SnackbarDuration.Short
                )
                if (result == SnackbarResult.ActionPerformed) {
                    undo()
                }
            } else {
                snackbarHostState.showSnackbar(message, duration = SnackbarDuration.Short)
            }
        }
    }

    var isBottomBarVisible by rememberSaveable { mutableStateOf(false) }

    val state by navController.currentBackStackEntryAsState()

    val appTopBarList = listOf(MedRecords, Doctors, Patients, Detalle)
    val screen = appTopBarList.find { screen ->
        state?.destination?.route == screen.route::class.qualifiedName
    }
    val topBar: @Composable () -> Unit = {
        TopBar(
            navController = navController,
            screen = screen
        )
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it }),
            ) {
                BottomBar(
                    navController = navController,
                    screens = appDestinationList,
                )
            }
        },
        topBar = topBar,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<LoginDestination> {
                isBottomBarVisible = Login.onBottomBar
                LoginScreen(
                    showSnackbar = showSnackbar,
                    navController = navController
                )
            }

            composable<DoctorsDestination> {
                isBottomBarVisible = Doctors.onBottomBar
                ListadoDoctorScreen()
            }

            composable<MedRecordDestination> {
                isBottomBarVisible = MedRecords.onBottomBar
                ListadoRecordsScreen()
            }

            composable<PatientsDestination> {
                isBottomBarVisible = Patients.onBottomBar
                ListadoPatientScreen(
                    onNavigateDetalle = { id -> navController.navigate(DetallePatientDestination(id)) },
                )
            }

            composable<DetallePatientDestination> {
                val id = (it.toRoute() as DetallePatientDestination).id

                DetallePatientsScreen(
                    patientId = id,
                    onNavigateBack = { navController.popBackStack() },
                    showSnackbar = showSnackbar
                )
            }
        }
    }
}
