package com.example.primer.compose.crud.contactos.ui.utils

sealed class UiEvent {

    data object PopBackStack: UiEvent()
    data class Navigate(val route: Any): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}
