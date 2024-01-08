package com.example.exercise_19.data.service.usersDetails

import com.example.exercise_19.data.model.usersDetails.UserDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsService {
    @GET("api/users/{id}")
    suspend fun getUserDetails(@Path("id") id:Int) :Response<UserDetailsDto>
}