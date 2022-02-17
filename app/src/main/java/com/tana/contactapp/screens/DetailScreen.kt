package com.tana.contactapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tana.contactapp.components.DetailScreenBottomBar
import com.tana.contactapp.components.DetailScreenTopBar
import com.tana.contactapp.data.Contact
import com.tana.contactapp.ui.theme.BtnDarkColor
import com.tana.contactapp.ui.theme.BtnLightColor
import com.tana.contactapp.ui.theme.DarkSurface
import com.tana.contactapp.ui.theme.LightSurface
import com.tana.contactapp.viewmodel.ContactAppVM

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    contact: Contact,
    viewModel: ContactAppVM,
    modifier: Modifier = Modifier,
    topPadding: Dp = 100.dp,
    imageSize: Dp = 80.dp
) {
    Scaffold(
        topBar = { DetailScreenTopBar(navHostController = navHostController) },
        bottomBar = {
            DetailScreenBottomBar(
                contact = contact,
                viewModel = viewModel,
                navHostController = navHostController
            )
        }
    ) {

        val bgColor = if (isSystemInDarkTheme()) DarkSurface else LightSurface
        ContactDetail(
            contact = contact,
            modifier = modifier
                .padding(top = topPadding + imageSize / 2f, end = 0.dp, bottom = 0.dp, start = 0.dp)
                .shadow(5.dp, RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
                .background(bgColor, RoundedCornerShape(24.dp))
                .padding(top = 50.dp, bottom = 20.dp)
        )
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = modifier
                    .fillMaxSize()
            ) {
                Card(
                    modifier = modifier
                        .offset(y = topPadding)
                        .shadow(5.dp, CircleShape)
                        .clip(CircleShape),
                    backgroundColor = viewModel.randomColors()
                ) {
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Contact Dp",
                        modifier = modifier
                            .size(imageSize)
                            .padding(12.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactDetail(
    contact: Contact,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = contact.name,
            style = MaterialTheme.typography.body1,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        ContactNumberRow(contact = contact)
        AskButton()
    }
}

@Composable
fun ContactNumberRow(contact: Contact) {

    Spacer(modifier = Modifier.height(8.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Phone no:",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = contact.number,
            style = MaterialTheme.typography.body1,
            fontSize = 18.sp
        )
    }
}

@Composable
fun AskButton() {
    Spacer(modifier = Modifier.height(6.dp))
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSystemInDarkTheme()) BtnDarkColor else BtnLightColor
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row() {
            Text(text = "Ask SIM before calling")
            Icon(
                imageVector = Icons.Default.NavigateNext,
                contentDescription = "Navigate next"
            )
        }
    }
}