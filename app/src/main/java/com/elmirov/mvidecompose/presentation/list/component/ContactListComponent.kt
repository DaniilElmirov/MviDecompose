package com.elmirov.mvidecompose.presentation.list.component

import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.presentation.list.store.ContactListStore
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {

    val model: StateFlow<ContactListStore.State>

    fun onContactClicked(contact: Contact)

    fun onAddContactClick()
}