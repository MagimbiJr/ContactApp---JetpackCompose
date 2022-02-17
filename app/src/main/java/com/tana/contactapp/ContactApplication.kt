package com.tana.contactapp

import android.app.Application
import com.tana.contactapp.data.ContactAppDatabase
import com.tana.contactapp.repository.ContactAppRepository

class ContactApplication : Application() {
    private val database by lazy { ContactAppDatabase.getDatabase(this) }
    val repository by lazy { ContactAppRepository(database.contactsDao()) }
}