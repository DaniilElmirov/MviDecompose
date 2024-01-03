package com.elmirov.mvidecompose.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.presentation.add.AddContactComponent
import com.elmirov.mvidecompose.presentation.add.DefaultAddContactComponent
import com.elmirov.mvidecompose.presentation.edit.DefaultEditContactComponent
import com.elmirov.mvidecompose.presentation.edit.EditContactComponent
import com.elmirov.mvidecompose.presentation.list.ContactListComponent
import com.elmirov.mvidecompose.presentation.list.DefaultContactListComponent
import com.elmirov.mvidecompose.presentation.root.DefaultRootComponent.Config.AddContact
import com.elmirov.mvidecompose.presentation.root.DefaultRootComponent.Config.ContactList
import com.elmirov.mvidecompose.presentation.root.DefaultRootComponent.Config.EditContact
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val stack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        initialConfiguration = ContactList,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): Child =
        when (config) {
            AddContact -> {
                val component = DefaultAddContactComponent(
                    componentContext = componentContext,
                    onContactSaved = {
                        navigation.pop()
                    },
                )
                Child.AddContact(component = component)
            }

            ContactList -> {
                val component = DefaultContactListComponent(
                    componentContext = componentContext,
                    onAddContactRequest = {
                        navigation.push(AddContact)
                    },
                    onEditingContactRequest = {
                        navigation.push(EditContact(contact = it))
                    },
                )
                Child.ContactList(component = component)
            }

            is EditContact -> {
                val component = DefaultEditContactComponent(
                    componentContext = componentContext,
                    contact = config.contact,
                    onContactSaved = {
                        navigation.pop()
                    }
                )
                Child.EditContact(component = component)
            }
        }

    sealed interface Child {
        class AddContact(val component: AddContactComponent) : Child

        class ContactList(val component: ContactListComponent) : Child

        class EditContact(val component: EditContactComponent) : Child
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