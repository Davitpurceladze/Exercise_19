package com.example.exercise_19.data.repository.users

import com.example.exercise_19.data.common.HandleResponse
import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.data.mapper.users.toDomain
import com.example.exercise_19.data.service.users.UsersService
import com.example.exercise_19.domain.users.UsersRepository
import com.example.exercise_19.domain.users.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersService: UsersService,
    private val handleResponse: HandleResponse
) : UsersRepository {
    override suspend fun getUsersList(): Flow<Resource<List<UsersResponse>>> {
        return handleResponse.safeApiCall {
            usersService.getUsersList()
        }.map {
            when (it) {
                is Resource.Success -> Resource.Success(value = it.data!!.map{ userDto -> userDto.toDomain() })
                is Resource.Error -> Resource.Error(error = it.errorMessage)
                is Resource.Loading -> Resource.Loading(loading = it.isLoading)
            }
        }
    }
}