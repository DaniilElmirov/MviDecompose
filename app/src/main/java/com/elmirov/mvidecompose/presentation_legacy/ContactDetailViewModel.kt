package com.elmirov.mvidecompose.presentation_legacy

import androidx.lifecycle.ViewModel
import com.elmirov.mvidecompose.data.RepositoryImpl
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.domain.usecase.AddContactUseCase
import com.elmirov.mvidecompose.domain.usecase.EditContactUseCase

class ContactDetailViewModel : ViewModel() {

    private val repository = RepositoryImpl

    private val addContactUseCase = AddContactUseCase(repository)
    private val editContactUseCase = EditContactUseCase(repository)

    fun addContact(username: String, phone: String) {
        addContactUseCase(username, phone)
    }

    fun editContact(contact: Contact) {
        editContactUseCase(contact)
    }
}