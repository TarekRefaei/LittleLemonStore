package com.tarekrefaei.littlelemonstore

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController, sharedPreferences: SharedPreferences) {
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""
    val coroutineScope = rememberCoroutineScope()
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
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Personal Information",
            fontSize = 24.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "First Name: $firstName"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Last Name: $lastName"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Email: $email"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth(),
            onClick = {
                navController.navigate(Screens.OnBoardingScreen.route)
                Toast.makeText(context, "Logout Success", Toast.LENGTH_LONG).show()
                coroutineScope.launch {
                    with(sharedPreferences.edit()){
                        delay(20)
                        remove("firstName")
                        remove("lastName")
                        remove("email")
                        apply()
                    }
                }
            }) {
            Text("Erase Data")
        }
    }
}