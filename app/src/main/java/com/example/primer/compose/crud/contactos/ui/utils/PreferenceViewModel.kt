package com.example.primer.compose.crud.contactos.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primer.compose.crud.contactos.data.PreferencesRepository
import com.example.primer.compose.crud.contactos.data.local.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PreferenceViewModel @Inject constructor(

    private val preferencesRepository: PreferencesRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val userId: StateFlow<Int> = preferencesRepository.userId
        .stateIn(viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = -1)

    fun saveUserId(userId: Int) {
        viewModelScope.launch {
            preferencesRepository.saveUserId(userId)
        }
    }
}