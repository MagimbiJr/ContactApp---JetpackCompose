package com.tana.contactapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tana.contactapp.repository.ContactAppRepository

class ContactAppVMFactory(
    private val repository: ContactAppRepository
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactAppVM::class.java)) {
            return ContactAppVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}