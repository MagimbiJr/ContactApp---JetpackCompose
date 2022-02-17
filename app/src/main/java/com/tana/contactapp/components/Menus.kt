package com.tana.contactapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.tana.contactapp.data.Contact
import com.tana.contactapp.viewmodel.ContactAppVM

@Composable
fun MainScreenMenu(
    viewModel: ContactAppVM
) {
    Box() {
        var expanded by remember { viewModel.expanded }

        IconButton(onClick = { expanded = true }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = { viewModel.deleteContacts() }) {
                Text(text = "Delete contacts")
            }
        }
    }
}

@Composable
fun DetailScreenMenu(
    contact: Contact,
    viewModel: ContactAppVM,
    modifier: Modifier,
    navHostController: NavHostController
) {
    Box() {
        var expanded by remember { viewModel.expanded }

        BottomAppItem(
            icon = Icons.Default.MoreVert,
            label = "More",
            description = "More",
            modifier = modifier
        ) {
            expanded = true
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                viewModel.deleteContact(contact)
                expanded = false
                navHostController.popBackStack()
            }
            ) {
                Text(text = "Delete")
            }
        }
    }
}