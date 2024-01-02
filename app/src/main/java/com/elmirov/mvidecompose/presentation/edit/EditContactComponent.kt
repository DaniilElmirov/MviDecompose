package com.elmirov.mvidecompose.presentation.edit

import android.os.Parcelable
import kotlinx.coroutines.flow.StateFlow
import kotlinx.parcelize.Parcelize

interface EditContactComponent {
    val model: StateFlow<Model>

    fun onUsernameChange(username: String)

    fun onPhoneChange(phone: String)

    fun onSaveContactClicked()

    @Parcelize
    data class Model(
        val username: String,
        val phone: String,
    ): Parcelable
}