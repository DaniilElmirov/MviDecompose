package com.elmirov.mvidecompose.presentation.list

import com.elmirov.mvidecompose.domain.entity.Contact
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {

    val model: StateFlow<ContactListStore.State>

    fun onContactClicked(contact: Contact)

    fun onAddContactClick()
}