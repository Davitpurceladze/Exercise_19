package com.example.exercise_19.data.repository

import com.example.exercise_19.data.common.Resource
import com.example.exercise_19.data.mapper.toDomain
import com.example.exercise_19.data.service.UsersService
import com.example.exercise_19.domain.users.UsersRepository
import com.example.exercise_19.domain.users.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(val usersService: UsersService) : UsersRepository {
    override suspend fun getUsersList(): Flow<Resource<List<UsersResponse>>> {
        return flow {
            val response = usersService.getUsersList()
            println("this is usersRepositoryImpl file -> ${response.body()} ")
//            emit(Resource.Success(value = response.body()!!.map { }))
            val domainResponse = response.body()!!.map { user -> user.toDomain() }
            println(domainResponse)
            emit(Resource.Success(value = domainResponse))
        }
    }
}