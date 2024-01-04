package com.elmirov.mvidecompose.presentation.add.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.elmirov.mvidecompose.presentation.add.store.AddContactStore
import com.elmirov.mvidecompose.util.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultAddContactComponent(
    componentContext: ComponentContext,
    private val onContactSaved: () -> Unit,
) : AddContactComponent, ComponentContext by componentContext {

    private lateinit var store: AddContactStore

    init {
        componentScope().launch {
            store.labels.collect {
                when(it) {
                    AddContactStore.Label.ContactSaved -> onContactSaved()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<AddContactStore.State>
        get() = store.stateFlow

    override fun onUsernameChange(username: String) {
        store.accept(AddContactStore.Intent.ChangeUsername(username))
    }

    override fun onPhoneChange(phone: String) {
        store.accept(AddContactStore.Intent.ChangePhone(phone))
    }

    override fun onSaveContactClicked() {
        store.accept(AddContactStore.Intent.SaveContact)
    }
}