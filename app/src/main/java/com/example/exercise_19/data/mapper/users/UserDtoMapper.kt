package com.example.exercise_19.data.mapper.users

import com.example.exercise_19.data.model.users.UserDto
import com.example.exercise_19.domain.users.UsersResponse

fun UserDto.toDomain(): UsersResponse {
  return UsersResponse( avatar, email, firstName, id, lastName)
}