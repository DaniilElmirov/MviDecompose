package com.elmirov.mvidecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.ui.content.AddContact
import com.elmirov.mvidecompose.ui.content.Contacts
import com.elmirov.mvidecompose.ui.content.EditContact
import com.elmirov.mvidecompose.ui.theme.MviDecomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by remember {
                mutableStateOf<Screen>(Screen.ContactList)
            }

            MviDecomposeTheme {
                when (val currentScreen = screen) {
                    Screen.AddContact -> {
                        AddContact(
                            onContactSaved = {
                                screen = Screen.ContactList
                            }
                        )
                    }

                    Screen.ContactList -> {
                        Contacts(
                            onAddContactClick = {
                                screen = Screen.AddContact
                            },
                            onContactClick = {
                                screen = Screen.EditContact(it)
                            }
                        )
                    }

                    is Screen.EditContact -> {
                        EditContact(
                            contact = currentScreen.contact,
                            onContactChanged = {
                                screen = Screen.ContactList
                            }
                        )
                    }
                }
            }
        }
    }
}

sealed interface Screen {
    data object ContactList : Screen
    data object AddContact : Screen

    data class EditContact(val contact: Contact) : Screen
}