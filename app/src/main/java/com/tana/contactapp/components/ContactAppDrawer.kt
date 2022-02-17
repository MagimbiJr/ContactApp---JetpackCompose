package com.tana.contactapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tana.contactapp.data.Contact
import com.tana.contactapp.ui.theme.DarkSurface
import com.tana.contactapp.ui.theme.LightSurface
import com.tana.contactapp.ui.theme.Yellow

@Composable
fun ContactAppDrawer(
    contacts: List<Contact>,
    navHostController: NavHostController,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 32.dp),
    ) {
        IconButton(
            onClick = {
                navHostController.navigate(
                    Screens.SettingScreen.route
                )
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings button",
                modifier = modifier.size(25.dp)
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        ContactCard(contacts = contacts, modifier = modifier)
        Spacer(modifier = modifier.height(24.dp))
        Divider()
        Spacer(modifier = modifier.height(8.dp))
        DrawerText(text = "Groups", modifier = modifier) {}
        Spacer(modifier = modifier.height(8.dp))
        Divider()
        Spacer(modifier = modifier.height(8.dp))
        DrawerText(text = "Companies", modifier = modifier) {}
        Spacer(modifier = modifier.height(8.dp))
        Divider()
        Spacer(modifier = modifier.height(8.dp))
        DrawerText(text = "SIM for calls", modifier = modifier) {}
        Spacer(modifier = modifier.height(8.dp))
        Divider()
        Spacer(modifier = modifier.height(8.dp))
        DrawerText(text = "Manage contacts", modifier = modifier) {}
        Spacer(modifier = modifier.height(8.dp))
        Divider()
        Spacer(modifier = modifier.height(8.dp))
        DrawerText(text = "Trash", modifier = modifier) {}
    }
}

@Composable
fun ContactCard(
    contacts: List<Contact>,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        backgroundColor = if (isSystemInDarkTheme()) DarkSurface else LightSurface
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Yellow,
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Contact icon",
                    tint = Color.White,
                )
            }
            Spacer(modifier = modifier.width(16.dp))
            Text(
                text = "All contacts",
                style = MaterialTheme.typography.body1,
                fontSize = 19.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = contacts.size.toString(),
                style = MaterialTheme.typography.body1,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun DrawerText(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = modifier
            .clickable { onClick() },
        style = MaterialTheme.typography.body1,
        fontSize = 20.sp,
        fontWeight = FontWeight.W500
    )
}
