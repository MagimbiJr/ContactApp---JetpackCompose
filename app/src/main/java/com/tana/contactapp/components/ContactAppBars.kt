package com.tana.contactapp.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tana.contactapp.data.Contact
import com.tana.contactapp.ui.theme.DarkYellow
import com.tana.contactapp.ui.theme.Yellow
import com.tana.contactapp.viewmodel.ContactAppVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainTopBar(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    viewModel: ContactAppVM
) {
    TopAppBar(
        title = { Text(text = "Contacts") },
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Nav icon")
            }
        },
        actions = {
            IconButton(onClick = {
                navHostController.navigate(Screens.AddScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Icon to move to add screen"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
            MainScreenMenu(viewModel = viewModel)
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun DetailScreenTopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Top back button",
                    modifier = modifier.size(25.dp)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun SettingScreenTopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = "Contacts Setting") },
        navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Top back button",
                    modifier = modifier.size(25.dp)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}


@Composable
fun DetailScreenBottomBar(
    contact: Contact,
    viewModel: ContactAppVM,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        var clicked by remember { mutableStateOf(false) }
        var (visible, setVisibility) = remember { mutableStateOf(false) }
        var (icon, setIcon) = remember { mutableStateOf(Icons.Default.StarBorder) }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val context = LocalContext.current
            BottomAppItem(
                icon = icon,
                label = "Favorite",
                description = "Favorite button",
                modifier = modifier
                    .weight(0.25f)
            ) {
                if (icon == Icons.Default.StarBorder) {
                    setIcon(Icons.Default.Star)
                    setVisibility(true)
                } else {
                    setIcon(Icons.Default.StarBorder)
                    setVisibility(false)
                }
            }
            BottomAppItem(
                icon = Icons.Default.Edit,
                label = "Edit",
                description = "Edit button",
                modifier = modifier
                    .weight(0.25f)
            ) {
                Toast.makeText(context, "Edit is not available yet", Toast.LENGTH_LONG).show()
            }
            BottomAppItem(
                icon = Icons.Default.Share,
                label = "Share",
                description = "Share button",
                modifier = modifier
                    .weight(0.25f)
            ) {
                Toast.makeText(context, "Share is not available yet", Toast.LENGTH_LONG).show()
            }
            DetailScreenMenu(
                contact = contact,
                viewModel = viewModel,
                modifier = modifier.fillMaxWidth(0.25f),
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun BottomAppItem(
    icon: ImageVector,
    label: String,
    description: String,
    modifier: Modifier,
    onItemClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onItemClicked() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = if (icon == Icons.Default.Star)
                DarkYellow
                else MaterialTheme.colors.onSurface
        )
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            fontSize = 13.sp
        )
    }
}