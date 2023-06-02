package com.tarekrefaei.littlelemonstore.ui.screens

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tarekrefaei.littlelemonstore.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun OnBoarding(
    navHostController: NavHostController,
    sharedPreferences: SharedPreferences
) {
    var firstName by rememberSaveable {
        mutableStateOf(
            sharedPreferences.getString(
                "firstName",
                ""
            ) ?: ""
        )
    }
    var lastName by rememberSaveable {
        mutableStateOf(
            sharedPreferences.getString(
                "lastName",
                ""
            ) ?: ""
        )
    }
    var email by rememberSaveable {
        mutableStateOf(
            sharedPreferences.getString(
                "email",
                ""
            ) ?: ""
        )
    }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp)
            .verticalScroll(rememberScrollState(), reverseScrolling = true),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
                .size(150.dp),
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(colorResource(id = R.color.primary1)),
        ) {
            Text(
                text = "Let's get to know you",
                fontSize = 24.sp,
                color = colorResource(id = R.color.white),
                modifier = Modifier.align(Center)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Personal Information",
            fontSize = 24.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            delay(50)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            value = firstName,
            label = { Text(text = "First Name") },
            onValueChange = { value -> firstName = value },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            placeholder = { Text(text = "Enter Your E-mail") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            delay(50)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            value = lastName,
            onValueChange = { value -> lastName = value },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            label = { Text(text = "Last Name") },
            placeholder = { Text(text = "Enter Your E-mail") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            delay(20)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            value = email,
            onValueChange = { value -> email = value },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Enter Your E-mail") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            delay(50)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.primary2)
            ),
            onClick = {
                keyboardController?.hide()
                if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()) {
                    Toast.makeText(context, "Registration Success", Toast.LENGTH_LONG).show()
                    coroutineScope.launch {
                        with(sharedPreferences.edit()) {
                            putString("firstName", firstName)
                            putString("lastName", lastName)
                            putString("email", email)
                            apply()
                        }
                    }
                    navHostController.navigate(Screens.HomeScreen.route)
                } else {
                    Toast.makeText(
                        context,
                        "Complete your Information please",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        ) {
            Text(text = "Register")
        }
    }
}