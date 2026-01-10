package com.abhinand.pixbittest.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Util {

    fun getTodayDate(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.now().format(formatter)
    }

}