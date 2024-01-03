package com.elmirov.mvidecompose.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.elmirov.mvidecompose.presentation.root.RootComponent
import com.elmirov.mvidecompose.presentation.root.RootComponent.Child.*
import com.elmirov.mvidecompose.ui.theme.MviDecomposeTheme

@Composable
fun RootContent(
    component: RootComponent,
) {
    MviDecomposeTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Children(
                stack = component.stack,
            ) {
                when (val instance = it.instance) {
                    is AddContact -> AddContact(component = instance.component)

                    is ContactList -> Contacts(component = instance.component)

                    is EditContact -> EditContact(component = instance.component)
                }
            }
        }
    }
}