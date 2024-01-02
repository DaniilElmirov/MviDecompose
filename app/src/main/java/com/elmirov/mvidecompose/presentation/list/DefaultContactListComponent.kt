package com.elmirov.mvidecompose.presentation.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.elmirov.mvidecompose.data.RepositoryImpl
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.domain.usecase.GetContactsUseCase
import com.elmirov.mvidecompose.util.componentScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DefaultContactListComponent(
    componentContext: ComponentContext,
    val onEditingContactRequest: (Contact) -> Unit,
    val onAddContactRequest: () -> Unit,
) : ContactListComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { FakeViewModel() }

    private val repository = RepositoryImpl
    private val getContactsUseCase = GetContactsUseCase(repository)

    private val coroutineScope = componentScope()

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

private class FakeViewModel() : InstanceKeeper.Instance {

}