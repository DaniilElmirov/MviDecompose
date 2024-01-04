package com.elmirov.mvidecompose.presentation.edit.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.presentation.edit.store.EditContactStore
import com.elmirov.mvidecompose.presentation.edit.store.EditContactStoreFactory
import com.elmirov.mvidecompose.util.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultEditContactComponent(
    componentContext: ComponentContext,
    contact: Contact,
    private val onContactSaved: () -> Unit,
) : EditContactComponent, ComponentContext by componentContext {

    private val storeFactory = EditContactStoreFactory()
    private val store = storeFactory.create(contact = contact)

    init {
        componentScope().launch {
            store.labels.collect {
                when (it) {
                    EditContactStore.Label.ContactSaved -> onContactSaved()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<EditContactStore.State>
        get() = store.stateFlow

    override fun onUsernameChange(username: String) {
        store.accept(EditContactStore.Intent.ChangeUsername(username = username))
    }

    override fun onPhoneChange(phone: String) {
        store.accept(EditContactStore.Intent.ChangePhone(phone = phone))
    }

    override fun onSaveContactClicked() {
        store.accept(EditContactStore.Intent.SaveContact)
    }
}