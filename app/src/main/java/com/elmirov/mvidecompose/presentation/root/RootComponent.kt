package com.elmirov.mvidecompose.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.elmirov.mvidecompose.presentation.add.AddContactComponent
import com.elmirov.mvidecompose.presentation.edit.EditContactComponent
import com.elmirov.mvidecompose.presentation.list.ContactListComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class AddContact(val component: AddContactComponent) : Child

        class ContactList(val component: ContactListComponent) : Child

        class EditContact(val component: EditContactComponent) : Child
    }
}