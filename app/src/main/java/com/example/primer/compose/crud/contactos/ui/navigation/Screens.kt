package com.example.primer.compose.crud.contactos.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument


val appDestinationList = listOf(MedRecords, Doctors, Patients)

interface AppDestination {
    val route: Any
    val title: String
    val scaffoldState: ScaffoldState
        get() = ScaffoldState()
}

interface AppMainBottomDestination : AppDestination {
    val onBottomBar: Boolean
    val icon: ImageVector
}

object Patients : AppMainBottomDestination {
    override val route = PatientsDestination
    override val title = "Pacientes"
    override val onBottomBar = true
    override val icon = Icons.Filled.AccountCircle
}

object Doctors : AppMainBottomDestination {
    override val route = DoctorsDestination
    override val title = "Doctores"
    override val onBottomBar = true
    override val icon = Icons.Filled.Face
}

object Login : AppMainBottomDestination {
    override val route = LoginDestination
    override val title = "Log in"
    override val onBottomBar = false
    override val icon = Icons.Filled.Lock
    override val scaffoldState = ScaffoldState(
        topBarState =TopBarState(showNavigationIcon = false, arrangement = Arrangement.Start),
        fabVisible = true
    )
}

object MedRecords : AppMainBottomDestination {
    override val route = MedRecordDestination
    override val title = "MedRecords"
    override val onBottomBar = true
    override val icon = Icons.Rounded.AddCircle
    val arguments = listOf(
        navArgument("id") { type = NavType.IntType }
    )
}

object Detalle : AppDestination {
    override val route = DetallePatientDestination
    override val title = "Detalle"
    val arguments = listOf(
        navArgument("id") { type = NavType.IntType }
    )
}