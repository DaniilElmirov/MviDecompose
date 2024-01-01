package com.elmirov.mvidecompose.domain.usecase

import com.elmirov.mvidecompose.domain.entity.Contact
import com.elmirov.mvidecompose.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetContactsUseCase(
    private val repository: Repository,
) {

    operator fun invoke(): Flow<List<Contact>> = repository.contacts
}