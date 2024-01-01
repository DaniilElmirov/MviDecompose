package com.elmirov.mvidecompose.domain.usecase

import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.domain.repository.Repository

class EditContactUseCase(
    private val repository: Repository,
) {

    operator fun invoke(
        contact: Contact,
    ) {
        repository.saveContact(contact)
    }
}