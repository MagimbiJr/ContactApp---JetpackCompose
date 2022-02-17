package com.tana.contactapp.screens

import android.content.Context
import android.view.View
import android.view.WindowInsetsAnimation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavHostController
import com.tana.contactapp.data.Contact
import com.tana.contactapp.ui.theme.DarkSurface
import com.tana.contactapp.ui.theme.DarkYellow
import com.tana.contactapp.ui.theme.LightSurface
import com.tana.contactapp.ui.theme.Yellow
import com.tana.contactapp.viewmodel.ContactAppVM
import kotlinx.coroutines.Dispatchers


@Composable
fun AddScreen(
    navHostController: NavHostController,
    viewModel: ContactAppVM,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(60.dp))
            Box(
                modifier = modifier
                    .shadow(5.dp, CircleShape)
                    .clip(CircleShape)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                DarkYellow,
                                Yellow
                            )
                        )
                    )
            ) {
                Image(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    modifier = modifier.padding(32.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Spacer(modifier = modifier.height(32.dp))
            ContactForm(
                name = name,
                number = number,
                modifier = modifier,
                onNameChanged = { text ->
                    name = text
                },
                onNumberChanged = { text ->
                    number = text
                }
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val contact = Contact(name = name, number = number)
            Text(
                text = "Save",
                style = MaterialTheme.typography.body1,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                color = Yellow,
                modifier = modifier.clickable {
                    viewModel.addContact(contact)
                    navHostController.popBackStack()
                }
            )
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.body1,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                color = Yellow,
                modifier = modifier.clickable {
                    navHostController.popBackStack()
                }
            )
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContactForm(
    name: String,
    number: String,
    modifier: Modifier,
    onNameChanged: (String) -> Unit,
    onNumberChanged: (String) -> Unit
) {
    val localFocusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardOptions = KeyboardActions(
        onNext = { localFocusManager.moveFocus(FocusDirection.Down) },
        onDone = {
            localFocusManager.clearFocus()
            keyboardController?.hide()
        }
    )
    Column() {
        FormTextField(
            value = name,
            placeholder = "Name",
            leadingIcon = Icons.Default.Person,
            modifier = modifier,
            onTextChange = onNameChanged,
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction =  ImeAction.Next,
            keyboardActions = keyboardOptions
        )
        Spacer(modifier = modifier.height(16.dp))
        FormTextField(
            value = number,
            placeholder = "Number",
            leadingIcon = Icons.Default.Call,
            modifier = modifier,
            onTextChange = onNumberChanged,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done,
            keyboardActions = keyboardOptions
        )
    }
}

@Composable
fun FormTextField(
    value: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier,
    onTextChange: (String) -> Unit,
    capitalization: KeyboardCapitalization,
    keyboardType: KeyboardType,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Default
) {
    TextField(
        value = value,
        onValueChange = onTextChange,
        modifier = modifier
            .fillMaxWidth()
            .shadow(5.dp, CircleShape)
            .clip(CircleShape),
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = capitalization,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = if (isSystemInDarkTheme()) DarkSurface else LightSurface,
            leadingIconColor = DarkYellow,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.onBackground
        )
    )
}