package com.abhinand.pixbittest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.abhinand.pixbittest.core.navigation.AppNavigation
import com.abhinand.pixbittest.core.theme.PixbitTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixbitTestTheme {
                AppNavigation()
            }
        }
    }
}