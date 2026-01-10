package com.abhinand.pixbittest.register.domain.valueobject

@JvmInline
value class Password private constructor(val value: String) {

    companion object {
        private val REGEX =
            Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!?_]).{5,}$")

        fun create(input: String): Result<Password> =
            if (REGEX.matches(input)) {
                Result.success(Password(input))
            } else {
                Result.failure(IllegalArgumentException("Invalid password"))
            }
    }
}
