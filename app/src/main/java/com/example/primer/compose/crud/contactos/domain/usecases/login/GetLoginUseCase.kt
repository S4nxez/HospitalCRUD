package com.example.primer.compose.crud.contactos.domain.usecases.login

import com.example.primer.compose.crud.contactos.data.ObjetosRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val objetosRepository: ObjetosRepository){
    operator fun invoke(id : Int) = objetosRepository.login(id)
}