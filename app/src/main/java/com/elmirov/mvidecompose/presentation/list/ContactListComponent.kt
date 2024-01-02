package com.elmirov.mvidecompose.presentation.list

import com.elmirov.mvidecompose.domain.entity.Contact
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {

    val model: StateFlow<Model>

    fun onContactClicked(contact: Contact)

    fun onAddContactClick()

    data class Model(
        val contacts: List<Contact>,
    )
}