package com.tana.contactapp

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tana.contactapp.ui.theme.ContactAppTheme
import com.tana.contactapp.viewmodel.ContactAppVM
import com.tana.contactapp.viewmodel.ContactAppVMFactory

class ContactActivity : ComponentActivity() {
    private val viewModel: ContactAppVM by viewModels {
        ContactAppVMFactory((application as ContactApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        setContent {

            val navHostController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()

            ProvideWindowInsets() {
                ContactAppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
                        ContactNavGraph(
                            navHostController = navHostController,
                            viewModel = viewModel,
                            scaffoldState = scaffoldState,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactAppTheme {
        Greeting("Android")
    }
}