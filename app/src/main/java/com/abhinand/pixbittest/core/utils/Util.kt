package com.abhinand.pixbittest.core.utils

import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.random.Random

object Util {

    fun getTodayDate(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.now().format(formatter)
    }

    fun String.toMillisOrNull(): Long? {
        return runCatching {
            LocalDate.parse(this, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .atTime(12, 0)
                .atZone(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli()
        }.getOrNull()
    }

    fun generateNumericId(length: Int = 10): Int =
        (1..length)
            .map { Random.nextInt(0, 10) }
            .joinToString("").toInt()

}