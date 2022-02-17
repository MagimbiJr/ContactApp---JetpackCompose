package com.tana.contactapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactAppDao {

    @Query("SELECT * FROM contacts")
    fun getContacts(): LiveData<List<Contact>>

    @Insert
    suspend fun addContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM contacts")
    suspend fun deleteContacts()

}