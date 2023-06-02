package com.tarekrefaei.littlelemonstore

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.tarekrefaei.littlelemonstore.ui.screens.Destinations
import com.tarekrefaei.littlelemonstore.ui.theme.LittleLemonStoreTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedPrefs: SharedPreferences

    override fun onStart() {
        super.onStart()
        sharedPrefs = getSharedPreferences("Prefs", MODE_PRIVATE)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonStoreTheme {
                val navHostController = rememberNavController()
                val hasData: Boolean =
                    sharedPrefs.contains("firstName")
                            && sharedPrefs.contains("lastName") &&
                            sharedPrefs.contains("email")

                Destinations(
                    navController = navHostController,
                    sharedPreferences = sharedPrefs,
                    hasData = hasData
                )
            }
        }
    }
}