package com.example.exercise_19.domain.users

data class UsersResponse(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
)