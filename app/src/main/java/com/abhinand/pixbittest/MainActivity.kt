package com.abhinand.pixbittest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.abhinand.pixbittest.core.navigation.AppNavigation
import com.abhinand.pixbittest.core.navigation.Screen
import com.abhinand.pixbittest.core.theme.PixbitTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixbitTestTheme {
                val isLoggedIn by viewModel.isLoggedIn.collectAsState()
                isLoggedIn?.let {
                    if (it) {
                        AppNavigation(startDestination = Screen.Home)
                    } else {
                        AppNavigation(startDestination = Screen.LoginScreen)
                    }
                }
            }
        }
    }
}