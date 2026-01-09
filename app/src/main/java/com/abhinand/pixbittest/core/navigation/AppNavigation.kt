package com.abhinand.pixbittest.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.abhinand.pixbittest.login.presentation.LoginScreen

@Composable
fun AppNavigation() {

    val backStack = rememberNavBackStack(Screen.LoginScreen)

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
                LoginScreen()
            }
        }
    )

}