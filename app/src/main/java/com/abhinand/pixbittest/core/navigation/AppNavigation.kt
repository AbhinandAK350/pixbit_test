package com.abhinand.pixbittest.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.abhinand.pixbittest.add_employee.presentation.AddEmployeeScreen
import com.abhinand.pixbittest.home.presentation.HomeScreen
import com.abhinand.pixbittest.login.presentation.LoginScreen
import com.abhinand.pixbittest.profile_details.presentation.ProfileDetailsScreen
import com.abhinand.pixbittest.register.presentation.RegisterScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(startDestination: Screen) {

    val backStack = rememberNavBackStack(startDestination)

    fun onBackPress() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    fun handleNavigation(action: Action) {
        when (action) {
            is Action.Pop -> onBackPress()
            is Action.Push -> {
                if (action.clearStack) backStack.clear()
                if (action.popBefore) backStack.removeLastOrNull()
                backStack.add(action.screen)
            }
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = { onBackPress() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ), entryProvider = entryProvider {
            entry<Screen.LoginScreen> {
                LoginScreen(onNavigate = ::handleNavigation)
            }

            entry<Screen.Register> {
                RegisterScreen(onNavigate = ::handleNavigation)
            }

            entry<Screen.Home> {
                HomeScreen(onNavigate = ::handleNavigation)
            }

            entry<Screen.AddEmployee> {
                AddEmployeeScreen(onNavigate = ::handleNavigation)
            }

            entry<Screen.ProfileDetails> {
                ProfileDetailsScreen(onNavigate = ::handleNavigation)
            }
        }, predictivePopTransitionSpec = {
            EnterTransition.None.togetherWith(ExitTransition.None)
        }
    )

}