package com.elmirov.mvidecompose.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.presentation.add.DefaultAddContactComponent
import com.elmirov.mvidecompose.presentation.edit.DefaultEditContactComponent
import com.elmirov.mvidecompose.presentation.list.DefaultContactListComponent
import com.elmirov.mvidecompose.presentation.root.DefaultRootComponent.Config.AddContact
import com.elmirov.mvidecompose.presentation.root.DefaultRootComponent.Config.ContactList
import com.elmirov.mvidecompose.presentation.root.DefaultRootComponent.Config.EditContact
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    val navigation = StackNavigation<Config>()

    fun child(
        componentContext: ComponentContext,
        config: Config,
    ): ComponentContext =
        when (config) {
            AddContact -> DefaultAddContactComponent(
                componentContext = componentContext,
                onContactSaved = {
                    navigation.pop()
                },
            )

            ContactList -> DefaultContactListComponent(
                componentContext = componentContext,
                onAddContactRequest = {
                    navigation.push(AddContact)
                },
                onEditingContactRequest = {
                    navigation.push(EditContact(contact = it))
                },
            )

            is EditContact -> DefaultEditContactComponent(
                componentContext = componentContext,
                contact = config.contact,
                onContactSaved = {
                    navigation.pop()
                }
            )
        }

    sealed interface Config : Parcelable {
        @Parcelize
        data object ContactList : Config

        @Parcelize
        data object AddContact : Config

        @Parcelize
        data class EditContact(val contact: Contact) : Config
    }
}