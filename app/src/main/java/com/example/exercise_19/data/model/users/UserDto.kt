package com.example.exercise_19.data.model.users

import com.squareup.moshi.Json

data class UserDto(
    val avatar: String,
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    val id: Int,
    @Json(name = "last_name")
    val lastName: String
)
