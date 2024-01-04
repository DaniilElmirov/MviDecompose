package com.elmirov.mvidecompose.presentation.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.elmirov.mvidecompose.presentation.add.component.AddContactComponent
import com.elmirov.mvidecompose.presentation.edit.component.EditContactComponent
import com.elmirov.mvidecompose.presentation.list.component.ContactListComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class AddContact(val component: AddContactComponent) : Child

        class ContactList(val component: ContactListComponent) : Child

        class EditContact(val component: EditContactComponent) : Child
    }
}