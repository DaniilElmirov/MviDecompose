package com.elmirov.mvidecompose.presentation.list

import com.arkivanov.mvikotlin.core.store.Store
import com.elmirov.mvidecompose.domain.entity.Contact

interface ContactListStore :
    Store<ContactListStore.Intent, ContactListStore.State, ContactListStore.Label> {

    sealed interface Intent {
        class SelectContact(val contact: Contact) : Intent

        data object AddContact : Intent
    }

    data class State(
        val contacts: List<Contact>,
    )

    sealed interface Label {
        data class EditContact(val contact: Contact) : Label

        data object AddContact : Label
    }
}