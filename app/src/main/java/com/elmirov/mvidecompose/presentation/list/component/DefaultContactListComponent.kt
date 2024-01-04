package com.elmirov.mvidecompose.presentation.list.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.presentation.list.store.ContactListStore
import com.elmirov.mvidecompose.presentation.list.store.ContactListStoreFactory
import com.elmirov.mvidecompose.util.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultContactListComponent(
    componentContext: ComponentContext,
    val onEditingContactRequest: (Contact) -> Unit,
    val onAddContactRequest: () -> Unit,
) : ContactListComponent, ComponentContext by componentContext {

    private val storeFactory = ContactListStoreFactory()
    private val store = storeFactory.create()

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    ContactListStore.Label.AddContact -> onAddContactRequest()

                    is ContactListStore.Label.EditContact -> onEditingContactRequest(it.contact)
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<ContactListStore.State>
        get() = store.stateFlow

    override fun onContactClicked(contact: Contact) {
        store.accept(ContactListStore.Intent.SelectContact(contact = contact))
    }

    override fun onAddContactClick() {
        store.accept(ContactListStore.Intent.AddContact)
    }
}