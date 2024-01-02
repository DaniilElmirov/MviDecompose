package com.elmirov.mvidecompose.presentation.add

import kotlinx.coroutines.flow.StateFlow

interface AddContactComponent {

    val model: StateFlow<Model>

    fun onUsernameChange(username: String)

    fun onPhoneChange(phone: String)

    fun onSaveContactClicked()

    data class Model(
        val username: String,
        val phone: String,
    )
}