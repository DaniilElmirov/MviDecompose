package com.elmirov.mvidecompose.presentation.edit

import kotlinx.coroutines.flow.StateFlow

interface EditContactComponent {
    val model: StateFlow<EditContactStore.State>

    fun onUsernameChange(username: String)

    fun onPhoneChange(phone: String)

    fun onSaveContactClicked()
}