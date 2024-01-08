package com.example.exercise_19.data.mapper.userDetails

import com.example.exercise_19.data.model.usersDetails.UserDetailsDto
import com.example.exercise_19.domain.userDetails.UserDetailsResponse
import com.example.exercise_19.domain.users.UsersResponse

fun UserDetailsDto.toDomain(): UserDetailsResponse {
    return UserDetailsResponse(UsersResponse(this.data.avatar, this.data.email, this.data.firstName,this.data.id, this.data.lastName ))
}