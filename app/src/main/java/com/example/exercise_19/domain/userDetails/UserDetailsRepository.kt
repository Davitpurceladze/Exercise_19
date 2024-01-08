package com.example.exercise_19.domain.userDetails

import com.example.exercise_19.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    suspend fun getUserDetails(id: Int) : Flow<Resource<UserDetailsResponse>>
}