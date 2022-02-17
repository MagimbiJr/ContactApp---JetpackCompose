package com.tana.contactapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tana.contactapp.components.SettingScreenTopBar

@Composable
fun SettingScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SettingScreenTopBar(navHostController = navHostController) }
    ) {
        Column(
            modifier = modifier.fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = "Settings Screen",
                style = MaterialTheme.typography.h4
            )
            
        }
    }
}