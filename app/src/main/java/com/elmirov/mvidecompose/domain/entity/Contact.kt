package com.elmirov.mvidecompose.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int = -1,
    val username: String,
    val phone: String,
) : Parcelable