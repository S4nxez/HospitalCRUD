package com.example.primer.compose.crud.contactos.ui.login

sealed class LoginEvent {
    class doLogin(val id: Int): LoginEvent()
}