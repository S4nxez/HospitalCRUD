package com.example.primer.compose.crud.contactos.ui.login

sealed class LoginEvent {
    data object UiEventDone : LoginEvent()
    class doLogin(val id: Int): LoginEvent()
}