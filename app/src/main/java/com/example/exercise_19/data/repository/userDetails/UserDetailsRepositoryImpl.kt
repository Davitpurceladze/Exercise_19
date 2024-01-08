package com.example.exercise_19.data.repository.userDetails

import com.example.exercise_19.data.common.HandleResponse
import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.data.mapper.userDetails.toDomain
import com.example.exercise_19.data.service.usersDetails.UserDetailsService
import com.example.exercise_19.domain.userDetails.UserDetailsRepository
import com.example.exercise_19.domain.userDetails.UserDetailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val userDetailsService: UserDetailsService,
    private  val handleResponse: HandleResponse
): UserDetailsRepository {
    override suspend fun getUserDetails(id: Int): Flow<Resource<UserDetailsResponse>> {
        return handleResponse.safeApiCall {
            userDetailsService.getUserDetails(id)
        }.map {
            when (it) {
                is Resource.Success -> Resource.Success(value = it.data!!.toDomain())
                is Resource.Error -> Resource.Error(error = it.errorMessage)
                is Resource.Loading -> Resource.Loading(loading = it.isLoading)
            }
        }
    }
}