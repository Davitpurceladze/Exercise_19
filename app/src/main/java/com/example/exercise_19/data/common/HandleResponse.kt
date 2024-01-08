package com.example.exercise_19.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


class HandleResponse {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(true))
        try {
            val response = call()
            val body = response.body()
            if (body !=null && response.isSuccessful) {

                emit(Resource.Success(value = response.body()))
            } else {
                emit(Resource.Error(error = response.errorBody()?.string() ?: ""))
            }
        } catch (e: Throwable) {

            emit(Resource.Error(error = e.message ?: ""))
        }
        emit(Resource.Loading(false))
    }
}

