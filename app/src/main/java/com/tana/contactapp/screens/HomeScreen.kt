package com.tana.contactapp.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tana.contactapp.components.ContactAppDrawer
import com.tana.contactapp.components.MainTopBar
import com.tana.contactapp.components.Screens
import com.tana.contactapp.data.Contact
import com.tana.contactapp.ui.theme.CallBgColor
import com.tana.contactapp.ui.theme.InfoBgColor
import com.tana.contactapp.ui.theme.MsgBgColor
import com.tana.contactapp.ui.theme.VidBgColor
import com.tana.contactapp.viewmodel.ContactAppVM
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: ContactAppVM,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val contacts = viewModel.contacts.observeAsState(listOf()).value.sortedBy {
        it.name
    }

    Scaffold(
        topBar = {
            MainTopBar(
                navHostController = navHostController,
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope,
                viewModel = viewModel
            )
        },
        drawerContent = {
            ContactAppDrawer(
                contacts = contacts,
                navHostController = navHostController,
                modifier = modifier
            )
        },
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerShape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
        scaffoldState = scaffoldState

    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            ContactsList(
                viewModel = viewModel,
                modifier = modifier,
                navHostController = navHostController,
                contacts = contacts
            )
        }
    }
}

@Composable
fun ContactsList(
    viewModel: ContactAppVM,
    modifier: Modifier,
    navHostController: NavHostController,
    contacts: List<Contact>
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item(contacts) {
            contacts.forEach { contact ->
                ContactRow(
                    contact = contact,
                    viewModel = viewModel,
                    modifier = modifier,
                    navHostController = navHostController
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContactRow(
    contact: Contact,
    viewModel: ContactAppVM,
    navHostController: NavHostController,
    modifier: Modifier
) {
    val context = LocalContext.current
    var isClicked by remember { mutableStateOf(false) }
    val (visible, setVisibility) = remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                isClicked = if (!visible) {
                    setVisibility(true)
                    true
                } else {
                    setVisibility(false)
                    false
                }
//                navHostController.navigate(
//                    "${Screens.DetailScreen.route}/${contact.id}/${contact.name}/${contact.number}"
//                )
            }
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {
        val cardBgColor by remember { mutableStateOf(viewModel.randomColors()) }

        Card(
            modifier = modifier
                .shadow(5.dp, CircleShape)
                .clip(CircleShape),
            backgroundColor = cardBgColor
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Contact Dp",
                modifier = modifier.padding(8.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
        Spacer(modifier = modifier.width(24.dp))
        Column {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.body1,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            if (isClicked)
                AnimatedVisibility(
                    visible = isClicked,
                   // enter = fadeIn(1.9f)
                ) {
                    ContactOptions(
                        contact = contact,
                        modifier = modifier,
                        navHostController = navHostController
                    )
                }
            Divider(modifier = modifier.padding(top = 12.dp))
        }
    }
}

@Composable
fun ContactOptions(
    contact: Contact,
    modifier: Modifier,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    Column() {
        Row() {
            Text(
                text = "Mobile",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.width(3.dp))
            Text(
                text = contact.number,
                style = MaterialTheme.typography.body1
            )
        }
        Spacer(modifier = modifier.height(5.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Option(
                icon = Icons.Default.Call,
                bgColor = CallBgColor,
                description = "Call",
                modifier = modifier
            ) {
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${contact.number}")
                }
                context.startActivity(callIntent)
            }
            Option(
                icon = Icons.Default.ChatBubble,
                bgColor = MsgBgColor,
                description = "Message",
                modifier = modifier
            ) {

            }
            Option(
                icon = Icons.Default.Videocam,
                bgColor = VidBgColor,
                description = "Video",
                modifier = modifier
            ) {

            }
            Option(
                icon = Icons.Default.Info,
                bgColor = InfoBgColor,
                description = "More Info",
                modifier = modifier
            ) {
                navHostController.navigate(
                    "${Screens.DetailScreen.route}/${contact.id}/${contact.name}/${contact.number}"
                )
            }
        }
    }
}

@Composable
fun Option(
    icon: ImageVector,
    bgColor: Color,
    description: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .shadow(5.dp, CircleShape)
            .clip(CircleShape),
        backgroundColor = bgColor
    ) {
        Image(
            imageVector = icon,
            contentDescription = description,
            modifier = modifier.padding(8.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}