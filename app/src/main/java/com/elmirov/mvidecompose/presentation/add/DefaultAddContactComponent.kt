package com.elmirov.mvidecompose.presentation.add

import com.elmirov.mvidecompose.data.RepositoryImpl
import com.elmirov.mvidecompose.domain.usecase.AddContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultAddContactComponent : AddContactComponent {

    private val repository = RepositoryImpl
    private val addContactUseCase = AddContactUseCase(repository)

    private val _model = MutableStateFlow(AddContactComponent.Model(username = "", phone = ""))
    override val model: StateFlow<AddContactComponent.Model>
        get() = _model.asStateFlow()

    override fun onUsernameChange(username: String) {
        _model.value = model.value.copy(username = username)
    }

    override fun onPhoneChange(phone: String) {
        _model.value = model.value.copy(phone = phone)
    }

    override fun onSaveContactClicked() {
        val (username, phone) = model.value
        addContactUseCase(username, phone)
    }
}