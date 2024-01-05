package com.example.exercise_19.domain.users

import com.example.exercise_19.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUsersList(): Flow<Resource<List<UsersResponse>>>
}