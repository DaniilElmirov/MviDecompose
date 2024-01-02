package com.elmirov.mvidecompose.presentation.edit

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.consume
import com.elmirov.mvidecompose.data.RepositoryImpl
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.domain.usecase.EditContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultEditContactComponent(
    componentContext: ComponentContext,
    private val contact: Contact,
    private val onContactSaved: () -> Unit,
) : EditContactComponent, ComponentContext by componentContext {

    companion object {
        private const val KEY = "DefaultEditContactComponent"
    }

    private val repository = RepositoryImpl
    private val editContactUseCase = EditContactUseCase(repository)

    init {
        stateKeeper.register(key = KEY) {
            model.value
        }
    }

    private val _model = MutableStateFlow(
        stateKeeper.consume(key = KEY) ?: EditContactComponent.Model(
            username = contact.username,
            phone = contact.phone
        )
    )
    override val model: StateFlow<EditContactComponent.Model>
        get() = _model.asStateFlow()

    override fun onUsernameChange(username: String) {
        _model.value = model.value.copy(username = username)
    }

    override fun onPhoneChange(phone: String) {
        _model.value = model.value.copy(phone = phone)
    }

    override fun onSaveContactClicked() {
        val (username, phone) = model.value
        editContactUseCase(
            contact.copy(
                username = username,
                phone = phone
            )
        )
        onContactSaved()
    }
}