package com.example.primer.compose.crud.contactos.domain.usecases.contactos

import com.example.primer.compose.crud.contactos.data.ObjetosRepository
import javax.inject.Inject

class DeletePatientByIdUseCase @Inject constructor(private val objetosRepository: ObjetosRepository){
    operator fun invoke(id: Int) = objetosRepository.deleteUser(id)
}