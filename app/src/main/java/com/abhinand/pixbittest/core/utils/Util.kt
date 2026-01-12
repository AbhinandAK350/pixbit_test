package com.abhinand.pixbittest.core.utils

import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object Util {

    fun getTodayDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.now().format(formatter)
    }

    fun String.toMillisOrNull(): Long? {
        return runCatching {
            LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .atTime(12, 0)
                .atZone(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli()
        }.getOrNull()
    }

}