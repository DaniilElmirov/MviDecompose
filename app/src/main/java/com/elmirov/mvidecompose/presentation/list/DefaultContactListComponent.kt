package com.elmirov.mvidecompose.presentation.list

import com.elmirov.mvidecompose.data.RepositoryImpl
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.domain.usecase.GetContactsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DefaultContactListComponent(
    val onEditingContactRequest: (Contact) -> Unit,
    val onAddContactRequest: () -> Unit,
) : ContactListComponent {

    private val repository = RepositoryImpl
    private val getContactsUseCase = GetContactsUseCase(repository)

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    override val model: StateFlow<ContactListComponent.Model> = getContactsUseCase()
        .map { ContactListComponent.Model(it) }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = ContactListComponent.Model(listOf()),
        )

    override fun onContactClicked(contact: Contact) {
        onEditingContactRequest(contact)
    }

    override fun onAddContactClick() {
        onAddContactRequest()
    }
}