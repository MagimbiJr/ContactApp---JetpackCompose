package com.tana.contactapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactAppDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactAppDao

    companion object {
        private val INSTANCE: ContactAppDatabase? = null

        fun getDatabase(context: Context): ContactAppDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactAppDatabase::class.java,
                    "contacts_app_database"
                ).fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}