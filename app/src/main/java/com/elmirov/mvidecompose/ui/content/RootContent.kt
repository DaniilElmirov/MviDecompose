package com.elmirov.mvidecompose.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.elmirov.mvidecompose.presentation.add.AddContactComponent
import com.elmirov.mvidecompose.presentation.edit.EditContactComponent
import com.elmirov.mvidecompose.presentation.list.ContactListComponent
import com.elmirov.mvidecompose.presentation.root.DefaultRootComponent
import com.elmirov.mvidecompose.ui.theme.MviDecomposeTheme

@Composable
fun RootContent(
    component: DefaultRootComponent, //TODO() переделать (зависимость от реализации)
) {
    MviDecomposeTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Children(
                stack = component.stack,
            ) {
                when (val instance = it.instance) {
                    is ContactListComponent -> Contacts(component = instance)

                    is AddContactComponent -> AddContact(component = instance)

                    is EditContactComponent -> EditContact(component = instance)
                }
            }
        }
    }
}