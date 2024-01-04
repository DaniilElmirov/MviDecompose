package com.elmirov.mvidecompose.presentation.add.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.elmirov.mvidecompose.data.RepositoryImpl
import com.elmirov.mvidecompose.domain.usecase.AddContactUseCase

class AddContactStoreFactory {

    private val storeFactory: StoreFactory = DefaultStoreFactory()
    private val repository = RepositoryImpl
    private val addContactUseCase = AddContactUseCase(repository)

    fun create(): AddContactStore = object : AddContactStore,
        Store<AddContactStore.Intent, AddContactStore.State, AddContactStore.Label> by storeFactory.create(
            name = "AddContactStore",
            initialState = AddContactStore.State(username = "", phone = ""),
            reducer = ReducerImpl,
            executorFactory = ::ExecutorImpl
        ) {}

    private sealed interface Action

    private sealed interface Msg {
        data class ChangeUsername(val username: String) : Msg

        data class ChangePhone(val phone: String) : Msg
    }

    private object ReducerImpl : Reducer<AddContactStore.State, Msg> {
        override fun AddContactStore.State.reduce(msg: Msg): AddContactStore.State = when (msg) {
            is Msg.ChangePhone -> copy(phone = msg.phone)

            is Msg.ChangeUsername -> copy(username = msg.username)
        }
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<AddContactStore.Intent, Action, AddContactStore.State, Msg, AddContactStore.Label>() {
        override fun executeIntent(
            intent: AddContactStore.Intent,
            getState: () -> AddContactStore.State,
        ) {
            when (intent) {
                is AddContactStore.Intent.ChangePhone -> dispatch(Msg.ChangePhone(intent.phone))

                is AddContactStore.Intent.ChangeUsername -> dispatch(Msg.ChangeUsername(intent.username))

                AddContactStore.Intent.SaveContact -> {
                    val state = getState()
                    addContactUseCase(username = state.username, phone = state.phone)
                    publish(AddContactStore.Label.ContactSaved)
                }
            }
        }
    }
}