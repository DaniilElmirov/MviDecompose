package com.elmirov.mvidecompose.presentation.edit.component

import com.elmirov.mvidecompose.presentation.edit.store.EditContactStore
import kotlinx.coroutines.flow.StateFlow

interface EditContactComponent {
    val model: StateFlow<EditContactStore.State>

    fun onUsernameChange(username: String)

    fun onPhoneChange(phone: String)

    fun onSaveContactClicked()
}