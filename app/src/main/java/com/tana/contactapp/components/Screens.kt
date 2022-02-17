package com.tana.contactapp.components

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home_screen")
    object DetailScreen : Screens("detail_screen")
    object AddScreen : Screens("add_screen")
    object SettingScreen : Screens("setting_setting")
}