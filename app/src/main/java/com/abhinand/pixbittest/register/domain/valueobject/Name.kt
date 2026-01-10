package com.abhinand.pixbittest.register.domain.valueobject

@JvmInline
value class Name private constructor(val value: String) {

    companion object {
        private val REGEX = Regex("^[a-zA-Z ]+$")

        fun create(input: String): Result<Name> {
            val trimmed = input.trim()
            return if (trimmed.isNotEmpty() && REGEX.matches(trimmed)) {
                Result.success(Name(trimmed))
            } else {
                Result.failure(IllegalArgumentException("Invalid name"))
            }
        }
    }
}
