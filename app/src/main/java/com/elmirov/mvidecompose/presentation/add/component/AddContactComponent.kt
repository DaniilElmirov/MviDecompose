package com.elmirov.mvidecompose.presentation.add.component

import com.elmirov.mvidecompose.presentation.add.store.AddContactStore
import kotlinx.coroutines.flow.StateFlow

interface AddContactComponent {

    val model: StateFlow<AddContactStore.State>

    fun onUsernameChange(username: String)

    fun onPhoneChange(phone: String)

    fun onSaveContactClicked()
}