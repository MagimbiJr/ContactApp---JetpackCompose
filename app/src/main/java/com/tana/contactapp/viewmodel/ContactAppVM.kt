package com.tana.contactapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.contactapp.data.Contact
import com.tana.contactapp.repository.ContactAppRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class ContactAppVM(private val repository: ContactAppRepository) : ViewModel() {
    val expanded = mutableStateOf(false)
    val contacts: LiveData<List<Contact>> = repository.contacts

    fun addContact(contact: Contact) = viewModelScope.launch {
        repository.addContact(contact)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch {
        repository.updateContact(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch {
        repository.deleteContact(contact)
    }

    fun deleteContacts() = viewModelScope.launch {
        repository.deleteContacts()
    }

    fun randomColors(): Color = Color(
        red = Random.nextInt(0, 255),
        green = Random.nextInt(0, 255),
        blue = Random.nextInt(0, 255)
    )
}