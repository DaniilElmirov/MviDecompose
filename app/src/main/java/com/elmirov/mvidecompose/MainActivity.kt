package com.elmirov.mvidecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.elmirov.mvidecompose.presentation.root.component.DefaultRootComponent
import com.elmirov.mvidecompose.ui.content.RootContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootContent(component = DefaultRootComponent(defaultComponentContext()))
        }
    }
}