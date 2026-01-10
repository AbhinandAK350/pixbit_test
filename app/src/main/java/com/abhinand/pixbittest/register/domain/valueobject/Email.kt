package com.abhinand.pixbittest.register.domain.valueobject

@JvmInline
value class Email private constructor(val value: String) {

    companion object {
        private val REGEX =
            Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

        fun create(input: String): Result<Email> =
            if (REGEX.matches(input)) {
                Result.success(Email(input))
            } else {
                Result.failure(IllegalArgumentException("Invalid email"))
            }
    }
}
