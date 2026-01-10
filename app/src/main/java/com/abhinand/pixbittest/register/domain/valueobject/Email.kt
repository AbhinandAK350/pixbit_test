package com.abhinand.pixbittest.register.domain.valueobject

@JvmInline
value class Email private constructor(val value: String) {

    companion object {

        private val REGEX = Regex(
            pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )

        fun isValid(input: String): Boolean =
            input.isNotBlank() && REGEX.matches(input)

        fun create(input: String): Result<Email> =
            if (isValid(input)) {
                Result.success(Email(input))
            } else {
                Result.failure(IllegalArgumentException("Invalid email address"))
            }
    }
}
