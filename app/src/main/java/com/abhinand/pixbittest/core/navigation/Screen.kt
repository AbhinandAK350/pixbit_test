package com.abhinand.pixbittest.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey {

    @Serializable
    object LoginScreen : Screen()

    @Serializable
    object Register : Screen()

    @Serializable
    object Home : Screen()

    @Serializable
    object AddEmployee : Screen()

    @Serializable
    data class ProfileDetails(val employeeId: String) : Screen()

}