package com.elmirov.mvidecompose.domain.usecase

import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.domain.repository.Repository

class AddContactUseCase(
    private val repository: Repository,
) {

    operator fun invoke(
        username: String,
        phone: String,
    ) {
        val contact = Contact(
            username = username,
            phone = phone
        )
        repository.saveContact(contact)
    }
}