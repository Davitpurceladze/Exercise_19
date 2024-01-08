package com.example.exercise_19.data.service.users

import com.example.exercise_19.data.model.users.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface UsersService {
    @GET("v3/7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")
    suspend fun getUsersList(): Response<List<UserDto>>
}