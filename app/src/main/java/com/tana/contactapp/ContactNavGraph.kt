package com.tana.contactapp

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tana.contactapp.components.Screens
import com.tana.contactapp.data.Contact
import com.tana.contactapp.screens.AddScreen
import com.tana.contactapp.screens.DetailScreen
import com.tana.contactapp.screens.HomeScreen
import com.tana.contactapp.screens.SettingScreen
import com.tana.contactapp.viewmodel.ContactAppVM
import kotlinx.coroutines.CoroutineScope

@Composable
fun ContactNavGraph(
    navHostController: NavHostController,
    viewModel: ContactAppVM,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navHostController = navHostController,
                viewModel = viewModel,
                scaffoldState = scaffoldState,
                coroutineScope = coroutineScope
            )
        }
        composable(
            route = "${Screens.DetailScreen.route}/{id}/{name}/{number}",
            arguments = listOf(
                navArgument(name = "id") { type = NavType.IntType},
                navArgument(name = "name") { type = NavType.StringType },
                navArgument(name = "number") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id")!!
            val name = navBackStackEntry.arguments?.getString("name")!!
            val number = navBackStackEntry.arguments?.getString("number")!!
            val contact = Contact(id = id, name = name, number = number)

            DetailScreen(
                navHostController = navHostController,
                contact = contact,
                viewModel = viewModel
            )
        }
        composable(Screens.AddScreen.route) {
            AddScreen(navHostController = navHostController, viewModel = viewModel)
        }
        composable(Screens.SettingScreen.route) {
            SettingScreen(navHostController = navHostController)
        }
    }
}