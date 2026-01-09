package com.abhinand.pixbittest.core.navigation

sealed class Action {

    object Pop : Action()

    data class Push(
        val screen: Screen,
        val popBefore: Boolean = false,
        val clearStack: Boolean = false,
        val inclusivePop: Boolean = false
    ) : Action()
}