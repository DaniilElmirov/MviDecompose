package com.elmirov.mvidecompose.presentation.edit

import kotlinx.coroutines.flow.StateFlow

interface EditContactComponent {
    val model: StateFlow<Model>

    fun onUsernameChange(username: String)

    fun onPhoneChange(phone: String)

    fun onSaveContactClicked()

    data class Model(
        val username: String,
        val phone: String,
    )
}