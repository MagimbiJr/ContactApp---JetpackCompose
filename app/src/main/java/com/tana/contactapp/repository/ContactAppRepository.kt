package com.tana.contactapp.repository

import androidx.lifecycle.LiveData
import com.tana.contactapp.data.Contact
import com.tana.contactapp.data.ContactAppDao

class ContactAppRepository(private val contactDao: ContactAppDao) {

    val contacts: LiveData<List<Contact>> = contactDao.getContacts()

    suspend fun addContact(contact: Contact) {
        contactDao.addContact(contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact)
    }

    suspend fun deleteContacts() {
        contactDao.deleteContacts()
    }

}